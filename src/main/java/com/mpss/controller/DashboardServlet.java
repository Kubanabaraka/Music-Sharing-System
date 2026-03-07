package com.mpss.controller;

import com.mpss.dao.PlaylistDAO;
import com.mpss.dao.SongDAO;
import com.mpss.model.Playlist;
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
 * DashboardServlet - Handles the user dashboard page.
 * Shows user's playlists overview and recent public playlists.
 *
 * @author MPSS Development Team
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Get user's playlists
        List<Playlist> myPlaylists = playlistDAO.findByUserId(currentUser.getId());
        request.setAttribute("myPlaylists", myPlaylists);
        request.setAttribute("myPlaylistCount", myPlaylists.size());

        // Get recent public playlists
        List<Playlist> recentPublic = playlistDAO.findRecentPublic(5);
        request.setAttribute("recentPublic", recentPublic);

        // Get user's total song count
        int totalSongs = 0;
        for (Playlist p : myPlaylists) {
            totalSongs += p.getSongCount();
        }
        request.setAttribute("mySongCount", totalSongs);

        // Count public playlists
        int publicCount = 0;
        for (Playlist p : myPlaylists) {
            if (p.isPublic()) publicCount++;
        }
        request.setAttribute("myPublicCount", publicCount);

        request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
    }
}
