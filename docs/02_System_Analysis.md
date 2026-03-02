# Music Playlist Sharing System — System Analysis Document

---

## 1. Functional Requirements

### FR-01: User Registration
- The system SHALL allow new users to register with username, email, password, and full name.
- The system SHALL validate that email and username are unique.
- The system SHALL enforce password minimum length of 6 characters.

### FR-02: User Authentication
- The system SHALL allow registered users to log in with username and password.
- The system SHALL maintain user sessions using HTTP sessions.
- The system SHALL provide logout functionality that invalidates the session.

### FR-03: Playlist Management
- Users SHALL be able to create new playlists with a title, description, and genre.
- Users SHALL be able to view, edit, and delete their own playlists.
- Users SHALL be able to set playlists as Public or Private.
- Users SHALL be able to browse all public playlists.

### FR-04: Song Management
- Users SHALL be able to add songs to their playlists.
- Each song SHALL have a title, artist, album (optional), and duration.
- Users SHALL be able to remove songs from their playlists.

### FR-05: Playlist Sharing
- Public playlists SHALL be visible to all authenticated users.
- Private playlists SHALL only be visible to their owner.
- Users SHALL be able to view details of any public playlist.

### FR-06: Admin Management
- Admins SHALL be able to view all users in the system.
- Admins SHALL be able to activate/deactivate user accounts.
- Admins SHALL be able to view all playlists (public and private).
- Admins SHALL be able to delete any playlist or user.

### FR-07: Dashboard
- Users SHALL see a dashboard with their playlists summary and recent public playlists.
- Admins SHALL see statistics including total users, playlists, and songs.

### FR-08: Search
- Users SHALL be able to search public playlists by title or genre.

---

## 2. Non-Functional Requirements

### NFR-01: Performance
- Pages SHALL load within 3 seconds under normal conditions.
- The system SHALL support at least 50 concurrent users.

### NFR-02: Security
- Passwords SHALL be stored as hashed values (SHA-256).
- The system SHALL prevent SQL injection via PreparedStatements.
- Session timeout SHALL be set to 30 minutes.

### NFR-03: Usability
- The UI SHALL be responsive and work on desktop and tablet screens.
- Error messages SHALL be clear and user-friendly.
- Navigation SHALL be intuitive with no more than 3 clicks to any feature.

### NFR-04: Reliability
- The system SHALL handle database connection failures gracefully.
- The system SHALL provide proper error pages for 404 and 500 errors.

### NFR-05: Maintainability
- Code SHALL follow MVC architecture with clear separation of concerns.
- All classes SHALL be properly commented.
- Configuration SHALL be externalized where possible.

---

## 3. User Roles

### Role 1: Regular User
- Register and login
- Create, edit, delete own playlists
- Add/remove songs from own playlists
- Browse public playlists
- Search playlists
- View own profile

### Role 2: Administrator
- All Regular User capabilities
- View all users
- Activate/deactivate users
- Delete any playlist
- View system statistics
- Access admin dashboard

---

## 4. Use Case Descriptions

### UC-01: User Registration
| Field          | Description |
|----------------|-------------|
| **Actor**      | Unregistered User |
| **Precondition** | User is not logged in |
| **Main Flow**  | 1. User navigates to registration page. 2. User fills in full name, username, email, and password. 3. System validates input. 4. System creates user account. 5. System redirects to login page with success message. |
| **Alt Flow**   | 3a. Validation fails → System displays error messages. |
| **Postcondition** | New user account exists in the database. |

### UC-02: User Login
| Field          | Description |
|----------------|-------------|
| **Actor**      | Registered User |
| **Precondition** | User has a valid account |
| **Main Flow**  | 1. User navigates to login page. 2. User enters username and password. 3. System authenticates credentials. 4. System creates session and redirects to dashboard. |
| **Alt Flow**   | 3a. Invalid credentials → System displays error message. |
| **Postcondition** | User session is active. |

### UC-03: Create Playlist
| Field          | Description |
|----------------|-------------|
| **Actor**      | Authenticated User |
| **Precondition** | User is logged in |
| **Main Flow**  | 1. User clicks "Create Playlist". 2. User enters title, description, genre, and visibility. 3. System validates input. 4. System saves playlist. 5. System redirects to playlist list. |
| **Alt Flow**   | 3a. Validation fails → System shows errors. |
| **Postcondition** | New playlist exists in the database. |

### UC-04: Add Song to Playlist
| Field          | Description |
|----------------|-------------|
| **Actor**      | Playlist Owner |
| **Precondition** | Playlist exists and user is the owner |
| **Main Flow**  | 1. User opens their playlist. 2. User clicks "Add Song". 3. User enters song title, artist, album, duration. 4. System validates and saves. |
| **Postcondition** | Song is linked to the playlist. |

### UC-05: Browse Public Playlists
| Field          | Description |
|----------------|-------------|
| **Actor**      | Authenticated User |
| **Precondition** | User is logged in |
| **Main Flow**  | 1. User navigates to "Explore" page. 2. System displays all public playlists. 3. User can click on any playlist to view details. |
| **Postcondition** | None (read-only). |

### UC-06: Admin Manages Users
| Field          | Description |
|----------------|-------------|
| **Actor**      | Administrator |
| **Precondition** | Admin is logged in |
| **Main Flow**  | 1. Admin navigates to User Management. 2. System displays all users. 3. Admin can activate/deactivate or delete users. |
| **Postcondition** | User status updated in database. |

---

## 5. System Context Diagram (Text)

```
+-------------------+         HTTP          +---------------------------+
|                   | --------------------> |                           |
|   Web Browser     |                       |  Music Playlist Sharing   |
|   (User/Admin)    | <-------------------- |  System (Tomcat)          |
|                   |      HTML/CSS/JS      |                           |
+-------------------+                       +---------------------------+
                                                       |
                                                       | JDBC
                                                       v
                                            +---------------------------+
                                            |                           |
                                            |    PostgreSQL Database    |
                                            |    (mpss_db)             |
                                            |                           |
                                            +---------------------------+
```

---

*Document Version: 1.0*
*Date: February 2026*
