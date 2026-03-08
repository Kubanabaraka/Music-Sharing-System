package com.mpss.dao;

import com.mpss.model.User;
import com.mpss.util.DBConnection;
import com.mpss.util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAO - Data Access Object for User operations.
 * Handles all database interactions for the users table.
 *
 * @author MPSS Development Team
 * @version 1.0
 */
public class UserDAO {

    /**
     * Registers a new user in the database.
     * Password is hashed before storage.
     *
     * @param user the User object to register
     * @return true if registration successful
     */
    public boolean register(User user) {
        String sql = "INSERT INTO users (full_name, username, email, password, role, active) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, PasswordUtil.hashPassword(user.getPassword()));
            ps.setString(5, user.getRole() != null ? user.getRole() : "USER");
            ps.setBoolean(6, true);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("[UserDAO] Registration error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Authenticates a user by username and password.
     *
     * @param username the username
     * @param password the plaintext password
     * @return User object if authenticated, null otherwise
     */
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, PasswordUtil.hashPassword(password));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] Login error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Finds a user by their ID.
     *
     * @param id the user ID
     * @return User object or null
     */
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] findById error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Finds a user by username.
     *
     * @param username the username
     * @return User object or null
     */
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] findByUsername error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Checks if a username already exists.
     *
     * @param username the username to check
     * @return true if username exists
     */
    public boolean usernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] usernameExists error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Checks if an email already exists.
     *
     * @param email the email to check
     * @return true if email exists
     */
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] emailExists error: " + e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves all users (for admin panel).
     *
     * @return list of all users
     */
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] findAll error: " + e.getMessage());
        }
        return users;
    }

    /**
     * Updates user active status (activate/deactivate).
     *
     * @param userId the user ID
     * @param active the new status
     * @return true if update successful
     */
    public boolean updateStatus(int userId, boolean active) {
        String sql = "UPDATE users SET active = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, active);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("[UserDAO] updateStatus error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a user by ID.
     *
     * @param userId the user ID
     * @return true if deletion successful
     */
    public boolean delete(int userId) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("[UserDAO] delete error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Counts total number of users.
     *
     * @return total user count
     */
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[UserDAO] countAll error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Maps a ResultSet row to a User object.
     */
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setFullName(rs.getString("full_name"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        user.setActive(rs.getBoolean("active"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        return user;
    }
}
