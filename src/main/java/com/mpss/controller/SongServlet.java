package com.mpss.controller;

import com.mpss.dao.PlaylistDAO;
import com.mpss.dao.SongDAO;
import com.mpss.model.Playlist;
import com.mpss.model.Song;
import com.mpss.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SongServlet - Handles song management within playlists.
 * Routes: /song
 *
 * Actions:
 *   POST /song?action=add      → Add song to playlist
 *   GET  /song?action=delete&id=X&playlistId=Y → Delete song
 *
 * @author MPSS Development Team
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/song"})
public class SongServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private SongDAO songDAO;
    private PlaylistDAO playlistDAO;

    @Override
    public void init() throws ServletException {
        songDAO = new SongDAO();
        playlistDAO = new PlaylistDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            handleDelete(request, response, currentUser);
        } else {
            response.sendRedirect(request.getContextPath() + "/playlists");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            handleAdd(request, response, currentUser);
        } else {
            response.sendRedirect(request.getContextPath() + "/playlists");
        }
    }

    /**
     * Adds a song to a playlist.
     */
    private void handleAdd(HttpServletRequest request, HttpServletResponse response,
                            User currentUser) throws ServletException, IOException {

        int playlistId = parseId(request.getParameter("playlistId"));
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String album = request.getParameter("album");
        String duration = request.getParameter("duration");

        // Verify playlist ownership
        Playlist playlist = playlistDAO.findById(playlistId);
        if (playlist == null || (playlist.getUserId() != currentUser.getId() && !currentUser.isAdmin())) {
            response.sendRedirect(request.getContextPath() + "/playlists");
            return;
        }

        // Validate input
        StringBuilder errors = new StringBuilder();
        if (title == null || title.trim().isEmpty()) {
            errors.append("Song title is required.<br>");
        }
        if (artist == null || artist.trim().isEmpty()) {
            errors.append("Artist name is required.<br>");
        }
        if (duration == null || duration.trim().isEmpty()) {
            errors.append("Duration is required.<br>");
        } else if (!duration.trim().matches("^\\d{1,3}:\\d{2}$")) {
            errors.append("Duration must be in format M:SS (e.g., 3:45).<br>");
        }

        if (errors.length() > 0) {
            response.sendRedirect(request.getContextPath() +
                "/playlist?action=view&id=" + playlistId + "&error=" +
                java.net.URLEncoder.encode("Validation failed: All song fields are required.", "UTF-8"));
            return;
        }

        Song song = new Song();
        song.setTitle(title.trim());
        song.setArtist(artist.trim());
        song.setAlbum(album != null ? album.trim() : "");
        song.setDuration(duration.trim());
        song.setPlaylistId(playlistId);

        boolean added = songDAO.addSong(song);
        if (added) {
            response.sendRedirect(request.getContextPath() +
                "/playlist?action=view&id=" + playlistId + "&message=songadded");
        } else {
            response.sendRedirect(request.getContextPath() +
                "/playlist?action=view&id=" + playlistId + "&error=Failed+to+add+song");
        }
    }

    /**
     * Deletes a song from a playlist.
     */
    private void handleDelete(HttpServletRequest request, HttpServletResponse response,
                               User currentUser) throws IOException {

        int songId = parseId(request.getParameter("id"));
        int playlistId = parseId(request.getParameter("playlistId"));

        // Verify playlist ownership
        Playlist playlist = playlistDAO.findById(playlistId);
        if (playlist != null &&
            (playlist.getUserId() == currentUser.getId() || currentUser.isAdmin())) {
            songDAO.delete(songId);
        }

        response.sendRedirect(request.getContextPath() +
            "/playlist?action=view&id=" + playlistId + "&message=songdeleted");
    }

    /**
     * Safely parses an integer from a string.
     */
    private int parseId(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
