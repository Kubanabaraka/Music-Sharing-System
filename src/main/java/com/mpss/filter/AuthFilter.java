package com.mpss.filter;

import com.mpss.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthFilter - Servlet filter for authentication and authorization.
 * Protects pages that require login and enforces role-based access.
 *
 * @author MPSS Development Team
 * @version 1.0
 */
@WebFilter(urlPatterns = {"/dashboard", "/playlist", "/playlists", "/song",
                          "/explore", "/admin/*", "/profile"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter initialization
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        User currentUser = null;

        if (session != null) {
            currentUser = (User) session.getAttribute("currentUser");
        }

        // Check if user is logged in
        if (currentUser == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        // Check admin pages access
        String requestURI = httpRequest.getRequestURI();
        if (requestURI.contains("/admin") && !currentUser.isAdmin()) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/dashboard");
            return;
        }

        // User is authenticated (and authorized if admin page), continue
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Filter cleanup
    }
}
