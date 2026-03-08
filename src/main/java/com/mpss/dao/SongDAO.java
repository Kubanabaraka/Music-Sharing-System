package com.mpss.dao;

import com.mpss.model.Song;
import com.mpss.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SongDAO - Data Access Object for Song operations.
 * Handles all database interactions for the songs table.
 *
 * @author MPSS Development Team
 * @version 1.0
 */
public class SongDAO {

    /**
     * Adds a new song to a playlist.
     *
     * @param song the Song object to add
     * @return true if addition successful
     */
    public boolean addSong(Song song) {
        String sql = "INSERT INTO songs (title, artist, album, duration, playlist_id) " +
                     "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, song.getTitle());
            ps.setString(2, song.getArtist());
            ps.setString(3, song.getAlbum());
            ps.setString(4, song.getDuration());
            ps.setInt(5, song.getPlaylistId());

            int rows = ps.executeUpdate();

            // Update playlist's updated_at timestamp
            if (rows > 0) {
                updatePlaylistTimestamp(song.getPlaylistId());
            }

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("[SongDAO] addSong error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Finds all songs in a specific playlist.
     *
     * @param playlistId the playlist ID
     * @return list of songs in the playlist
     */
    public List<Song> findByPlaylistId(int playlistId) {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT * FROM songs WHERE playlist_id = ? ORDER BY created_at ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songs.add(mapResultSetToSong(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("[SongDAO] findByPlaylistId error: " + e.getMessage());
        }
        return songs;
    }

    /**
     * Finds a song by its ID.
     *
     * @param id the song ID
     * @return Song object or null
     */
    public Song findById(int id) {
        String sql = "SELECT * FROM songs WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToSong(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("[SongDAO] findById error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Deletes a song by ID.
     *
     * @param id the song ID
     * @return true if deletion successful
     */
    public boolean delete(int id) {
        // Get playlist ID before deletion (to update timestamp)
        Song song = findById(id);
        String sql = "DELETE FROM songs WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0 && song != null) {
                updatePlaylistTimestamp(song.getPlaylistId());
            }

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("[SongDAO] delete error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Counts total number of songs in the system.
     *
     * @return total song count
     */
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM songs";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("[SongDAO] countAll error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Counts songs in a specific playlist.
     *
     * @param playlistId the playlist ID
     * @return song count for that playlist
     */
    public int countByPlaylist(int playlistId) {
        String sql = "SELECT COUNT(*) FROM songs WHERE playlist_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("[SongDAO] countByPlaylist error: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Updates the playlist's updated_at timestamp when songs are modified.
     */
    private void updatePlaylistTimestamp(int playlistId) {
        String sql = "UPDATE playlists SET updated_at = CURRENT_TIMESTAMP WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, playlistId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("[SongDAO] updatePlaylistTimestamp error: " + e.getMessage());
        }
    }

    /**
     * Maps a ResultSet row to a Song object.
     */
    private Song mapResultSetToSong(ResultSet rs) throws SQLException {
        Song song = new Song();
        song.setId(rs.getInt("id"));
        song.setTitle(rs.getString("title"));
        song.setArtist(rs.getString("artist"));
        song.setAlbum(rs.getString("album"));
        song.setDuration(rs.getString("duration"));
        song.setPlaylistId(rs.getInt("playlist_id"));
        song.setCreatedAt(rs.getTimestamp("created_at"));
        return song;
    }
}
