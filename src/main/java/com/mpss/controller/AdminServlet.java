package com.mpss.controller;

import com.mpss.dao.PlaylistDAO;
import com.mpss.dao.SongDAO;
import com.mpss.dao.UserDAO;
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
 * AdminServlet - Handles admin dashboard and user management.
 * Routes: /admin/dashboard, /admin/users, /admin/playlists
 *
 * @author MPSS Development Team
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/admin/dashboard", "/admin/users", "/admin/playlists"})
public class AdminServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private PlaylistDAO playlistDAO;
    private SongDAO songDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        playlistDAO = new PlaylistDAO();
        songDAO = new SongDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath() + 
                      (request.getPathInfo() != null ? request.getPathInfo() : "");

        switch (path) {
            case "/admin/dashboard":
                handleDashboard(request, response);
                break;

            case "/admin/users":
                handleUsers(request, response);
                break;

            case "/admin/playlists":
                handlePlaylists(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }

        switch (action) {
            case "toggleUser":
                handleToggleUser(request, response);
                break;

            case "deleteUser":
                handleDeleteUser(request, response);
                break;

            case "deletePlaylist":
                handleDeletePlaylist(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }

    /**
     * Shows admin dashboard with system statistics.
     */
    private void handleDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int totalUsers = userDAO.countAll();
        int totalPlaylists = playlistDAO.countAll();
        int totalSongs = songDAO.countAll();

        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalPlaylists", totalPlaylists);
        request.setAttribute("totalSongs", totalSongs);

        // Recent playlists for overview
        List<Playlist> recentPlaylists = playlistDAO.findRecentPublic(10);
        request.setAttribute("recentPlaylists", recentPlaylists);

        request.getRequestDispatcher("/jsp/admin_dashboard.jsp").forward(request, response);
    }

    /**
     * Shows user management page.
     */
    private void handleUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> users = userDAO.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsp/admin_users.jsp").forward(request, response);
    }

    /**
     * Shows all playlists (admin view).
     */
    private void handlePlaylists(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Playlist> playlists = playlistDAO.findAll();
        request.setAttribute("playlists", playlists);
        request.getRequestDispatcher("/jsp/admin_playlists.jsp").forward(request, response);
    }

    /**
     * Toggles user active/inactive status.
     */
    private void handleToggleUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int userId = parseId(request.getParameter("userId"));
        String currentStatus = request.getParameter("currentStatus");

        if (userId > 0) {
            boolean newStatus = !"true".equals(currentStatus);
            userDAO.updateStatus(userId, newStatus);
        }

        response.sendRedirect(request.getContextPath() + "/admin/users?message=updated");
    }

    /**
     * Deletes a user.
     */
    private void handleDeleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int userId = parseId(request.getParameter("userId"));
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Don't allow admin to delete themselves
        if (userId > 0 && userId != currentUser.getId()) {
            userDAO.delete(userId);
        }

        response.sendRedirect(request.getContextPath() + "/admin/users?message=deleted");
    }

    /**
     * Deletes any playlist (admin privilege).
     */
    private void handleDeletePlaylist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int playlistId = parseId(request.getParameter("playlistId"));
        if (playlistId > 0) {
            playlistDAO.delete(playlistId);
        }

        response.sendRedirect(request.getContextPath() + "/admin/playlists?message=deleted");
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
