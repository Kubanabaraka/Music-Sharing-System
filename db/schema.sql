-- ============================================================
-- Music Playlist Sharing System (MPSS)
-- PostgreSQL Database Schema
-- Version: 1.0
-- Date: February 2026
-- ============================================================

-- 1. Create Database
-- Run this command from psql as superuser:
-- CREATE DATABASE mpss_db;

-- Connect to the database:
-- \c mpss_db

-- ============================================================
-- 2. Drop existing tables (for clean re-creation)
-- ============================================================

DROP TABLE IF EXISTS songs CASCADE;
DROP TABLE IF EXISTS playlists CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- ============================================================
-- 3. Create Tables
-- ============================================================

-- ----- USERS TABLE -----
CREATE TABLE users (
    id            SERIAL PRIMARY KEY,
    full_name     VARCHAR(100)  NOT NULL,
    username      VARCHAR(50)   NOT NULL UNIQUE,
    email         VARCHAR(100)  NOT NULL UNIQUE,
    password      VARCHAR(256)  NOT NULL,
    role          VARCHAR(20)   NOT NULL DEFAULT 'USER' CHECK (role IN ('USER', 'ADMIN')),
    active        BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ----- PLAYLISTS TABLE -----
CREATE TABLE playlists (
    id            SERIAL PRIMARY KEY,
    user_id       INTEGER       NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    title         VARCHAR(150)  NOT NULL,
    description   TEXT,
    genre         VARCHAR(50),
    is_public     BOOLEAN       NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ----- SONGS TABLE -----
CREATE TABLE songs (
    id            SERIAL PRIMARY KEY,
    title         VARCHAR(200)  NOT NULL,
    artist        VARCHAR(150)  NOT NULL,
    album         VARCHAR(150),
    duration      VARCHAR(10)   NOT NULL DEFAULT '0:00',
    playlist_id   INTEGER       NOT NULL REFERENCES playlists(id) ON DELETE CASCADE,
    created_at    TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- 4. Create Indexes for Performance
-- ============================================================

CREATE INDEX idx_playlists_user_id ON playlists(user_id);
CREATE INDEX idx_playlists_is_public ON playlists(is_public);
CREATE INDEX idx_playlists_genre ON playlists(genre);
CREATE INDEX idx_songs_playlist_id ON songs(playlist_id);

-- ============================================================
-- 5. Insert Sample Data
-- ============================================================

-- Passwords are SHA-256 hashes of the plaintext shown in comments.

-- Admin user (password: admin123)
INSERT INTO users (full_name, username, email, password, role, active) VALUES
('System Administrator', 'admin', 'admin@mpss.com',
 '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9',
 'ADMIN', TRUE);

-- Regular users (password: user123)
INSERT INTO users (full_name, username, email, password, role, active) VALUES
('Alice Johnson', 'alice', 'alice@example.com',
 'e606e38b0d8c19b24cf0ee3808183162ea7cd63ff7912dbb22b5e803286b4446',
 'USER', TRUE),
('Bob Williams', 'bob', 'bob@example.com',
 'e606e38b0d8c19b24cf0ee3808183162ea7cd63ff7912dbb22b5e803286b4446',
 'USER', TRUE),
('Carol Davis', 'carol', 'carol@example.com',
 'e606e38b0d8c19b24cf0ee3808183162ea7cd63ff7912dbb22b5e803286b4446',
 'USER', TRUE),
('David Martinez', 'david', 'david@example.com',
 'e606e38b0d8c19b24cf0ee3808183162ea7cd63ff7912dbb22b5e803286b4446',
 'USER', FALSE);

-- Sample Playlists
INSERT INTO playlists (user_id, title, description, genre, is_public) VALUES
(2, 'Morning Vibes', 'Perfect songs to start your day with positive energy.', 'Pop', TRUE),
(2, 'Workout Mix', 'High energy tracks for the gym.', 'Electronic', TRUE),
(3, 'Chill Evening', 'Relaxing tunes for winding down.', 'Jazz', TRUE),
(3, 'Road Trip Classics', 'Best songs for a long drive.', 'Rock', TRUE),
(4, 'Study Focus', 'Instrumental music for concentration.', 'Classical', TRUE),
(4, 'My Private Collection', 'Personal favorites - not shared.', 'Mixed', FALSE),
(2, 'Party Anthems', 'Ultimate party playlist!', 'Hip-Hop', TRUE);

-- Sample Songs
-- Morning Vibes playlist (id=1)
INSERT INTO songs (title, artist, album, duration, playlist_id) VALUES
('Here Comes the Sun', 'The Beatles', 'Abbey Road', '3:05', 1),
('Walking on Sunshine', 'Katrina and the Waves', 'Walking on Sunshine', '3:58', 1),
('Good Morning', 'Kanye West', 'Graduation', '3:15', 1),
('Beautiful Day', 'U2', 'All That You Cant Leave Behind', '4:06', 1),
('Happy', 'Pharrell Williams', 'G I R L', '3:53', 1);

-- Workout Mix playlist (id=2)
INSERT INTO songs (title, artist, album, duration, playlist_id) VALUES
('Eye of the Tiger', 'Survivor', 'Eye of the Tiger', '4:05', 2),
('Stronger', 'Kanye West', 'Graduation', '5:11', 2),
('Lose Yourself', 'Eminem', '8 Mile OST', '5:26', 2),
('Titanium', 'David Guetta ft. Sia', 'Nothing but the Beat', '4:05', 2);

-- Chill Evening playlist (id=3)
INSERT INTO songs (title, artist, album, duration, playlist_id) VALUES
('Fly Me to the Moon', 'Frank Sinatra', 'It Might as Well Be Swing', '2:30', 3),
('So What', 'Miles Davis', 'Kind of Blue', '9:22', 3),
('Feeling Good', 'Nina Simone', 'I Put a Spell on You', '2:55', 3);

-- Road Trip Classics playlist (id=4)
INSERT INTO songs (title, artist, album, duration, playlist_id) VALUES
('Born to Run', 'Bruce Springsteen', 'Born to Run', '4:30', 4),
('Hotel California', 'Eagles', 'Hotel California', '6:30', 4),
('Bohemian Rhapsody', 'Queen', 'A Night at the Opera', '5:55', 4),
('Sweet Home Alabama', 'Lynyrd Skynyrd', 'Second Helping', '4:43', 4),
('Take It Easy', 'Eagles', 'Eagles', '3:33', 4);

-- Study Focus playlist (id=5)
INSERT INTO songs (title, artist, album, duration, playlist_id) VALUES
('Clair de Lune', 'Claude Debussy', 'Suite Bergamasque', '5:00', 5),
('Gymnopédie No.1', 'Erik Satie', 'Gymnopédies', '3:00', 5),
('Canon in D', 'Johann Pachelbel', 'Canon and Gigue', '5:30', 5);

-- Private Collection playlist (id=6)
INSERT INTO songs (title, artist, album, duration, playlist_id) VALUES
('Secret Song', 'Private Artist', 'Hidden Album', '4:00', 6),
('My Jam', 'Underground DJ', 'Unreleased', '3:30', 6);

-- Party Anthems playlist (id=7)
INSERT INTO songs (title, artist, album, duration, playlist_id) VALUES
('Uptown Funk', 'Bruno Mars', 'Uptown Special', '4:30', 7),
('Shake It Off', 'Taylor Swift', '1989', '3:39', 7),
('I Gotta Feeling', 'Black Eyed Peas', 'The E.N.D.', '4:49', 7);

-- ============================================================
-- 6. Verify Data
-- ============================================================

-- SELECT COUNT(*) AS total_users FROM users;
-- SELECT COUNT(*) AS total_playlists FROM playlists;
-- SELECT COUNT(*) AS total_songs FROM songs;

-- ============================================================
-- End of Schema Script
-- ============================================================
