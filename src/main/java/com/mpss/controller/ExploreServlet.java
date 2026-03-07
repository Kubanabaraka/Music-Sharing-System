package com.mpss.controller;

import com.mpss.dao.PlaylistDAO;
import com.mpss.model.Playlist;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * ExploreServlet - Handles browsing and searching public playlists.
 * Route: /explore
 *
 * @author MPSS Development Team
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/explore"})
public class ExploreServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PlaylistDAO playlistDAO;

    @Override
    public void init() throws ServletException {
        playlistDAO = new PlaylistDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String search = request.getParameter("search");
        List<Playlist> playlists;

        if (search != null && !search.trim().isEmpty()) {
            playlists = playlistDAO.search(search.trim());
            request.setAttribute("searchQuery", search.trim());
        } else {
            playlists = playlistDAO.findPublic();
        }

        request.setAttribute("playlists", playlists);
        request.getRequestDispatcher("/jsp/explore.jsp").forward(request, response);
    }
}
