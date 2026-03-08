package com.mpss.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Playlist JavaBean - Represents a music playlist.
 * Maps to the 'playlists' table in PostgreSQL.
 *
 * @author MPSS Development Team
 * @version 1.0
 */
public class Playlist implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int userId;
    private String title;
    private String description;
    private String genre;
    private boolean isPublic;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Transient display field (not a DB column)
    private String ownerUsername;
    private int songCount;

    // ---- Constructors ----

    public Playlist() {
    }

    public Playlist(int userId, String title, String description, String genre, boolean isPublic) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.isPublic = isPublic;
    }

    // ---- Getters and Setters ----

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isPublic() {
        return isPublic;
    }

    /**
     * Getter for EL expression ${playlist.isPublic}.
     * EL resolves 'isPublic' property by looking for getIsPublic().
     */
    public boolean getIsPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }

    @Override
    public String toString() {
        return "Playlist{id=" + id + ", title='" + title + "', genre='" + genre + "'}";
    }
}
