package com.mpss.controller;

import com.mpss.dao.UserDAO;
import com.mpss.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthServlet - Handles user authentication operations.
 * Routes: /login, /register, /logout
 *
 * @author MPSS Development Team
 * @version 1.0
 */
@WebServlet(urlPatterns = {"/login", "/register", "/logout"})
public class AuthServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/login":
                // If already logged in, redirect to dashboard
                HttpSession existingSession = request.getSession(false);
                if (existingSession != null && existingSession.getAttribute("currentUser") != null) {
                    response.sendRedirect(request.getContextPath() + "/dashboard");
                    return;
                }
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                break;

            case "/register":
                request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
                break;

            case "/logout":
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                response.sendRedirect(request.getContextPath() + "/login?message=loggedout");
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/login":
                handleLogin(request, response);
                break;

            case "/register":
                handleRegister(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    /**
     * Handles user login.
     */
    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Username and password are required.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return;
        }

        username = username.trim();
        password = password.trim();

        // Attempt login
        User user = userDAO.login(username, password);

        if (user != null) {
            // Check if user is active
            if (!user.isActive()) {
                request.setAttribute("error", "Your account has been deactivated. Contact admin.");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
                return;
            }

            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            // Redirect based on role
            if (user.isAdmin()) {
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }
        } else {
            request.setAttribute("error", "Invalid username or password.");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    /**
     * Handles user registration.
     */
    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fullName = request.getParameter("fullName");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Validate required fields
        StringBuilder errors = new StringBuilder();

        if (fullName == null || fullName.trim().isEmpty()) {
            errors.append("Full name is required.<br>");
        }
        if (username == null || username.trim().isEmpty()) {
            errors.append("Username is required.<br>");
        } else if (username.trim().length() < 3) {
            errors.append("Username must be at least 3 characters.<br>");
        } else if (!username.trim().matches("^[a-zA-Z0-9_]+$")) {
            errors.append("Username can only contain letters, numbers, and underscores.<br>");
        }
        if (email == null || email.trim().isEmpty()) {
            errors.append("Email is required.<br>");
        } else if (!email.trim().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            errors.append("Please enter a valid email address.<br>");
        }
        if (password == null || password.trim().isEmpty()) {
            errors.append("Password is required.<br>");
        } else if (password.trim().length() < 6) {
            errors.append("Password must be at least 6 characters.<br>");
        }
        if (confirmPassword == null || !confirmPassword.equals(password)) {
            errors.append("Passwords do not match.<br>");
        }

        // Check if username or email already exists
        if (errors.length() == 0) {
            if (userDAO.usernameExists(username.trim())) {
                errors.append("Username already taken.<br>");
            }
            if (userDAO.emailExists(email.trim())) {
                errors.append("Email already registered.<br>");
            }
        }

        // If errors exist, redisplay form
        if (errors.length() > 0) {
            request.setAttribute("error", errors.toString());
            request.setAttribute("fullName", fullName);
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            return;
        }

        // Create user
        User user = new User(fullName.trim(), username.trim(), email.trim(), password.trim());
        boolean registered = userDAO.register(user);

        if (registered) {
            response.sendRedirect(request.getContextPath() + "/login?message=registered");
        } else {
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        }
    }
}
