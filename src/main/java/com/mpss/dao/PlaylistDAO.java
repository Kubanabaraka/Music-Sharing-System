package com.mpss.dao;

import com.mpss.model.Playlist;
import com.mpss.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PlaylistDAO - Data Access Object for Playlist operations.
 * Handles all database interactions for the playlists table.
 *
 * @author MPSS Development Team
 * @version 1.0
 */
public class PlaylistDAO {

    /**
     * Creates a new playlist.
     *
     * @param playlist the Playlist object to create
     * @return true if creation successful
     */
    public boolean create(Playlist playlist) {
        String sql = "INSERT INTO playlists (user_id, title, description, genre, is_public) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlist.getUserId());
            ps.setString(2, playlist.getTitle());
            ps.setString(3, playlist.getDescription());
            ps.setString(4, playlist.getGenre());
            ps.setBoolean(5, playlist.isPublic());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] create error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Finds a playlist by its ID.
     *
     * @param id the playlist ID
     * @return Playlist object or null
     */
    public Playlist findById(int id) {
        String sql = "SELECT p.*, u.username AS owner_username, " +
                     "(SELECT COUNT(*) FROM songs s WHERE s.playlist_id = p.id) AS song_count " +
                     "FROM playlists p JOIN users u ON p.user_id = u.id WHERE p.id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToPlaylist(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] findById error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Finds all playlists belonging to a specific user.
     *
     * @param userId the user ID
     * @return list of playlists for that user
     */
    public List<Playlist> findByUserId(int userId) {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT p.*, u.username AS owner_username, " +
                     "(SELECT COUNT(*) FROM songs s WHERE s.playlist_id = p.id) AS song_count " +
                     "FROM playlists p JOIN users u ON p.user_id = u.id " +
                     "WHERE p.user_id = ? ORDER BY p.updated_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    playlists.add(mapResultSetToPlaylist(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] findByUserId error: " + e.getMessage());
        }
        return playlists;
    }

    /**
     * Finds all public playlists (for Explore page).
     *
     * @return list of public playlists
     */
    public List<Playlist> findPublic() {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT p.*, u.username AS owner_username, " +
                     "(SELECT COUNT(*) FROM songs s WHERE s.playlist_id = p.id) AS song_count " +
                     "FROM playlists p JOIN users u ON p.user_id = u.id " +
                     "WHERE p.is_public = TRUE ORDER BY p.created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                playlists.add(mapResultSetToPlaylist(rs));
            }
        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] findPublic error: " + e.getMessage());
        }
        return playlists;
    }

    /**
     * Finds all playlists (for admin panel).
     *
     * @return list of all playlists
     */
    public List<Playlist> findAll() {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT p.*, u.username AS owner_username, " +
                     "(SELECT COUNT(*) FROM songs s WHERE s.playlist_id = p.id) AS song_count " +
                     "FROM playlists p JOIN users u ON p.user_id = u.id " +
                     "ORDER BY p.created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                playlists.add(mapResultSetToPlaylist(rs));
            }
        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] findAll error: " + e.getMessage());
        }
        return playlists;
    }

    /**
     * Searches public playlists by title or genre.
     *
     * @param query the search query
     * @return list of matching public playlists
     */
    public List<Playlist> search(String query) {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT p.*, u.username AS owner_username, " +
                     "(SELECT COUNT(*) FROM songs s WHERE s.playlist_id = p.id) AS song_count " +
                     "FROM playlists p JOIN users u ON p.user_id = u.id " +
                     "WHERE p.is_public = TRUE AND (LOWER(p.title) LIKE ? OR LOWER(p.genre) LIKE ?) " +
                     "ORDER BY p.created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchTerm = "%" + query.toLowerCase() + "%";
            ps.setString(1, searchTerm);
            ps.setString(2, searchTerm);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    playlists.add(mapResultSetToPlaylist(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] search error: " + e.getMessage());
        }
        return playlists;
    }

    /**
     * Updates an existing playlist.
     *
     * @param playlist the Playlist with updated fields
     * @return true if update successful
     */
    public boolean update(Playlist playlist) {
        String sql = "UPDATE playlists SET title = ?, description = ?, genre = ?, is_public = ?, " +
                     "updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, playlist.getTitle());
            ps.setString(2, playlist.getDescription());
            ps.setString(3, playlist.getGenre());
            ps.setBoolean(4, playlist.isPublic());
            ps.setInt(5, playlist.getId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] update error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a playlist by ID.
     *
     * @param id the playlist ID
     * @return true if deletion successful
     */
    public boolean delete(int id) {
        String sql = "DELETE FROM playlists WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] delete error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Counts total number of playlists.
     *
     * @return total playlist count
     */
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM playlists";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] countAll error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Counts playlists for a specific user.
     *
     * @param userId the user ID
     * @return playlist count for that user
     */
    public int countByUser(int userId) {
        String sql = "SELECT COUNT(*) FROM playlists WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] countByUser error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Gets recent public playlists (limited).
     *
     * @param limit maximum number to retrieve
     * @return list of recent public playlists
     */
    public List<Playlist> findRecentPublic(int limit) {
        List<Playlist> playlists = new ArrayList<>();
        String sql = "SELECT p.*, u.username AS owner_username, " +
                     "(SELECT COUNT(*) FROM songs s WHERE s.playlist_id = p.id) AS song_count " +
                     "FROM playlists p JOIN users u ON p.user_id = u.id " +
                     "WHERE p.is_public = TRUE ORDER BY p.created_at DESC LIMIT ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    playlists.add(mapResultSetToPlaylist(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("[PlaylistDAO] findRecentPublic error: " + e.getMessage());
        }
        return playlists;
    }

    /**
     * Maps a ResultSet row to a Playlist object.
     */
    private Playlist mapResultSetToPlaylist(ResultSet rs) throws SQLException {
        Playlist playlist = new Playlist();
        playlist.setId(rs.getInt("id"));
        playlist.setUserId(rs.getInt("user_id"));
        playlist.setTitle(rs.getString("title"));
        playlist.setDescription(rs.getString("description"));
        playlist.setGenre(rs.getString("genre"));
        playlist.setPublic(rs.getBoolean("is_public"));
        playlist.setCreatedAt(rs.getTimestamp("created_at"));
        playlist.setUpdatedAt(rs.getTimestamp("updated_at"));
        playlist.setOwnerUsername(rs.getString("owner_username"));
        playlist.setSongCount(rs.getInt("song_count"));
        return playlist;
    }
}
