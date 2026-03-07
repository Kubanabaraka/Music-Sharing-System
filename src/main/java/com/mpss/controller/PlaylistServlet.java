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
import java.util.List;

/**
 * PlaylistServlet - Handles CRUD operations for playlists.
 * Routes: /playlists, /playlist
 *
 * Actions via parameter:
 *   GET  /playlists             → List user's playlists
 *   GET  /playlist?action=new   → Show create form
 *   GET  /playlist?action=edit&id=X → Show edit form
 *   GET  /playlist?action=view&id=X → View playlist details
 *   GET  /playlist?action=delete&id=X → Delete playlist
 *   POST /playlist?action=create → Create playlist
 *   POST /playlist?action=update → Update playlist
 *
 * @author MPSS Development Team
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/playlists", "/playlist"})
public class PlaylistServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PlaylistDAO playlistDAO;
    private SongDAO songDAO;

    @Override
    public void init() throws ServletException {
        playlistDAO = new PlaylistDAO();
        songDAO = new SongDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if ("/playlists".equals(path)) {
            // List all playlists for the current user
            List<Playlist> playlists = playlistDAO.findByUserId(currentUser.getId());
            request.setAttribute("playlists", playlists);
            request.getRequestDispatcher("/jsp/playlists.jsp").forward(request, response);
            return;
        }

        // Handle /playlist with action parameter
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                request.getRequestDispatcher("/jsp/playlist_form.jsp").forward(request, response);
                break;

            case "edit":
                handleEditForm(request, response, currentUser);
                break;

            case "view":
                handleView(request, response, currentUser);
                break;

            case "delete":
                handleDelete(request, response, currentUser);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/playlists");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/playlists");
            return;
        }

        switch (action) {
            case "create":
                handleCreate(request, response, currentUser);
                break;

            case "update":
                handleUpdate(request, response, currentUser);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/playlists");
        }
    }

    /**
     * Shows the edit form pre-populated with playlist data.
     */
    private void handleEditForm(HttpServletRequest request, HttpServletResponse response,
                                 User currentUser) throws ServletException, IOException {
        int id = parseId(request.getParameter("id"));
        if (id <= 0) {
            response.sendRedirect(request.getContextPath() + "/playlists");
            return;
        }

        Playlist playlist = playlistDAO.findById(id);
        if (playlist == null) {
            request.setAttribute("error", "Playlist not found.");
            response.sendRedirect(request.getContextPath() + "/playlists");
            return;
        }

        // Only owner or admin can edit
        if (playlist.getUserId() != currentUser.getId() && !currentUser.isAdmin()) {
            response.sendRedirect(request.getContextPath() + "/playlists");
            return;
        }

        request.setAttribute("playlist", playlist);
        request.setAttribute("editMode", true);
        request.getRequestDispatcher("/jsp/playlist_form.jsp").forward(request, response);
    }

    /**
     * Displays full playlist details with songs.
     */
    private void handleView(HttpServletRequest request, HttpServletResponse response,
                             User currentUser) throws ServletException, IOException {
        int id = parseId(request.getParameter("id"));
        if (id <= 0) {
            response.sendRedirect(request.getContextPath() + "/playlists");
            return;
        }

        Playlist playlist = playlistDAO.findById(id);
        if (playlist == null) {
            request.setAttribute("error", "Playlist not found.");
            request.getRequestDispatcher("/jsp/playlists.jsp").forward(request, response);
            return;
        }

        // Check access: owner, admin, or public playlist
        boolean isOwner = playlist.getUserId() == currentUser.getId();
        boolean isAdmin = currentUser.isAdmin();
        if (!isOwner && !isAdmin && !playlist.isPublic()) {
            request.setAttribute("error", "You do not have permission to view this playlist.");
            response.sendRedirect(request.getContextPath() + "/playlists");
            return;
        }

        List<Song> songs = songDAO.findByPlaylistId(id);
        request.setAttribute("playlist", playlist);
        request.setAttribute("songs", songs);
        request.setAttribute("isOwner", isOwner);
        request.getRequestDispatcher("/jsp/playlist_view.jsp").forward(request, response);
    }

    /**
     * Creates a new playlist.
     */
    private void handleCreate(HttpServletRequest request, HttpServletResponse response,
                               User currentUser) throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String genre = request.getParameter("genre");
        String visibility = request.getParameter("visibility");

        // Validate
        StringBuilder errors = new StringBuilder();
        if (title == null || title.trim().isEmpty()) {
            errors.append("Playlist title is required.<br>");
        } else if (title.trim().length() > 150) {
            errors.append("Title must be 150 characters or fewer.<br>");
        }
        if (genre == null || genre.trim().isEmpty()) {
            errors.append("Genre is required.<br>");
        }

        if (errors.length() > 0) {
            request.setAttribute("error", errors.toString());
            request.setAttribute("title", title);
            request.setAttribute("description", description);
            request.setAttribute("genre", genre);
            request.setAttribute("visibility", visibility);
            request.getRequestDispatcher("/jsp/playlist_form.jsp").forward(request, response);
            return;
        }

        Playlist playlist = new Playlist();
        playlist.setUserId(currentUser.getId());
        playlist.setTitle(title.trim());
        playlist.setDescription(description != null ? description.trim() : "");
        playlist.setGenre(genre.trim());
        playlist.setPublic("public".equalsIgnoreCase(visibility));

        boolean created = playlistDAO.create(playlist);
        if (created) {
            response.sendRedirect(request.getContextPath() + "/playlists?message=created");
        } else {
            request.setAttribute("error", "Failed to create playlist. Please try again.");
            request.getRequestDispatcher("/jsp/playlist_form.jsp").forward(request, response);
        }
    }

    /**
     * Updates an existing playlist.
     */
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response,
                               User currentUser) throws ServletException, IOException {

        int id = parseId(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String genre = request.getParameter("genre");
        String visibility = request.getParameter("visibility");

        // Verify ownership
        Playlist existing = playlistDAO.findById(id);
        if (existing == null || (existing.getUserId() != currentUser.getId() && !currentUser.isAdmin())) {
            response.sendRedirect(request.getContextPath() + "/playlists");
            return;
        }

        // Validate
        StringBuilder errors = new StringBuilder();
        if (title == null || title.trim().isEmpty()) {
            errors.append("Playlist title is required.<br>");
        }
        if (genre == null || genre.trim().isEmpty()) {
            errors.append("Genre is required.<br>");
        }

        if (errors.length() > 0) {
            request.setAttribute("error", errors.toString());
            request.setAttribute("playlist", existing);
            request.setAttribute("editMode", true);
            request.getRequestDispatcher("/jsp/playlist_form.jsp").forward(request, response);
            return;
        }

        existing.setTitle(title.trim());
        existing.setDescription(description != null ? description.trim() : "");
        existing.setGenre(genre.trim());
        existing.setPublic("public".equalsIgnoreCase(visibility));

        boolean updated = playlistDAO.update(existing);
        if (updated) {
            response.sendRedirect(request.getContextPath() + "/playlist?action=view&id=" + id + "&message=updated");
        } else {
            request.setAttribute("error", "Failed to update playlist.");
            request.setAttribute("playlist", existing);
            request.setAttribute("editMode", true);
            request.getRequestDispatcher("/jsp/playlist_form.jsp").forward(request, response);
        }
    }

    /**
     * Deletes a playlist.
     */
    private void handleDelete(HttpServletRequest request, HttpServletResponse response,
                               User currentUser) throws IOException {

        int id = parseId(request.getParameter("id"));
        Playlist playlist = playlistDAO.findById(id);

        // Only owner or admin can delete
        if (playlist != null &&
            (playlist.getUserId() == currentUser.getId() || currentUser.isAdmin())) {
            playlistDAO.delete(id);
        }

        response.sendRedirect(request.getContextPath() + "/playlists?message=deleted");
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
