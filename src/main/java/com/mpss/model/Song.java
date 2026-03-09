package com.mpss.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Song JavaBean - Represents a song within a playlist.
 * Maps to the 'songs' table in PostgreSQL.
 *
 * @author MPSS Development Team
 * @version 1.0
 */
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String title;
    private String artist;
    private String album;
    private String duration;
    private int playlistId;
    private Timestamp createdAt;

    // ---- Constructors ----

    public Song() {
    }

    public Song(String title, String artist, String album, String duration, int playlistId) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.playlistId = playlistId;
    }

    // ---- Getters and Setters ----

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Song{id=" + id + ", title='" + title + "', artist='" + artist + "'}";
    }
}
