# Music Playlist Sharing System — Testing Documentation

---

## 1. Test Plan Overview

This document presents the test cases, expected results, and sample testing evidence for the Music Playlist Sharing System (MPSS). Testing covers functional, validation, security, and usability aspects.

---

## 2. Test Environment

| Component     | Details                        |
|---------------|--------------------------------|
| Browser       | Google Chrome 120+ / Firefox   |
| Server        | Apache Tomcat 9.0              |
| Database      | PostgreSQL 15                  |
| Java Version  | JDK 8+                         |
| OS            | Ubuntu Linux / Windows 10+     |

---

## 3. Functional Test Cases

### TC-01: User Registration (Valid Input)

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-01                                    |
| **Module**       | Authentication                           |
| **Description**  | Register a new user with valid data      |
| **Pre-condition**| User is not registered                   |
| **Test Data**    | Full Name: "John Smith", Username: "john", Email: "john@test.com", Password: "pass123", Confirm: "pass123" |
| **Steps**        | 1. Navigate to /register 2. Fill all fields 3. Click "Create Account" |
| **Expected Result** | Redirect to login page with success message "Registration successful!" |
| **Status**       | PASS                                     |

### TC-02: User Registration (Duplicate Username)

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-02                                    |
| **Module**       | Authentication - Validation              |
| **Description**  | Attempt registration with existing username |
| **Pre-condition**| Username "alice" already exists           |
| **Test Data**    | Username: "alice"                        |
| **Steps**        | 1. Navigate to /register 2. Enter "alice" as username 3. Submit |
| **Expected Result** | Error: "Username already taken."       |
| **Status**       | PASS                                     |

### TC-03: User Registration (Password Mismatch)

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-03                                    |
| **Module**       | Authentication - Validation              |
| **Description**  | Attempt registration with mismatched passwords |
| **Test Data**    | Password: "pass123", Confirm: "pass456"  |
| **Steps**        | 1. Enter different passwords 2. Submit   |
| **Expected Result** | Error: "Passwords do not match."       |
| **Status**       | PASS                                     |

### TC-04: User Registration (Short Password)

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-04                                    |
| **Module**       | Authentication - Validation              |
| **Description**  | Attempt registration with password < 6 chars |
| **Test Data**    | Password: "abc"                          |
| **Expected Result** | Error: "Password must be at least 6 characters." |
| **Status**       | PASS                                     |

### TC-05: User Login (Valid Credentials)

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-05                                    |
| **Module**       | Authentication                           |
| **Description**  | Login with valid credentials             |
| **Test Data**    | Username: "alice", Password: "user123"   |
| **Steps**        | 1. Navigate to /login 2. Enter credentials 3. Click "Sign In" |
| **Expected Result** | Redirect to /dashboard, user name displayed in navbar |
| **Status**       | PASS                                     |

### TC-06: User Login (Invalid Credentials)

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-06                                    |
| **Module**       | Authentication                           |
| **Description**  | Login with wrong password                |
| **Test Data**    | Username: "alice", Password: "wrongpass" |
| **Expected Result** | Error: "Invalid username or password." |
| **Status**       | PASS                                     |

### TC-07: User Login (Deactivated Account)

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-07                                    |
| **Module**       | Authentication                           |
| **Description**  | Login with deactivated account           |
| **Test Data**    | Username: "david", Password: "user123"   |
| **Expected Result** | Login fails (david is inactive in sample data) |
| **Status**       | PASS                                     |

### TC-08: Create Playlist

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-08                                    |
| **Module**       | Playlist Management                      |
| **Description**  | Create a new playlist with valid data    |
| **Pre-condition**| User is logged in                        |
| **Test Data**    | Title: "My Jazz Mix", Genre: "Jazz", Visibility: Public |
| **Steps**        | 1. Click "Create Playlist" 2. Fill form 3. Submit |
| **Expected Result** | Redirect to playlists page with "Playlist created successfully!" |
| **Status**       | PASS                                     |

### TC-09: Create Playlist (Missing Title)

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-09                                    |
| **Module**       | Playlist - Validation                    |
| **Description**  | Create playlist without title            |
| **Test Data**    | Title: (empty)                           |
| **Expected Result** | Error: "Playlist title is required."   |
| **Status**       | PASS                                     |

### TC-10: Edit Playlist

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-10                                    |
| **Module**       | Playlist Management                      |
| **Description**  | Edit an existing playlist                |
| **Pre-condition**| Playlist exists and user is the owner    |
| **Steps**        | 1. Click "Edit" on a playlist 2. Change title 3. Save |
| **Expected Result** | Playlist updated, redirect to view page with success message |
| **Status**       | PASS                                     |

### TC-11: Delete Playlist

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-11                                    |
| **Module**       | Playlist Management                      |
| **Description**  | Delete a playlist (with confirmation)    |
| **Steps**        | 1. Click "Delete" 2. Confirm dialog 3. Accept |
| **Expected Result** | Playlist and all songs removed, success message shown |
| **Status**       | PASS                                     |

### TC-12: Add Song to Playlist

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-12                                    |
| **Module**       | Song Management                          |
| **Description**  | Add a song to an owned playlist          |
| **Test Data**    | Title: "Test Song", Artist: "Test Artist", Duration: "3:30" |
| **Steps**        | 1. Open playlist view 2. Fill song form 3. Click "Add Song" |
| **Expected Result** | Song appears in the playlist table     |
| **Status**       | PASS                                     |

### TC-13: Add Song (Invalid Duration)

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-13                                    |
| **Module**       | Song - Validation                        |
| **Description**  | Add song with invalid duration format    |
| **Test Data**    | Duration: "abc"                          |
| **Expected Result** | HTML5 pattern validation prevents submit (format: M:SS) |
| **Status**       | PASS                                     |

### TC-14: Remove Song from Playlist

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-14                                    |
| **Module**       | Song Management                          |
| **Description**  | Remove a song from a playlist            |
| **Steps**        | 1. Click trash icon next to song 2. Confirm |
| **Expected Result** | Song removed, success message shown    |
| **Status**       | PASS                                     |

### TC-15: Browse Public Playlists

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-15                                    |
| **Module**       | Explore                                  |
| **Description**  | View all public playlists                |
| **Steps**        | 1. Click "Explore" in navbar             |
| **Expected Result** | List of all public playlists displayed |
| **Status**       | PASS                                     |

### TC-16: Search Playlists

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-16                                    |
| **Module**       | Explore - Search                         |
| **Description**  | Search playlists by genre                |
| **Test Data**    | Search: "jazz"                           |
| **Expected Result** | Only Jazz playlists shown              |
| **Status**       | PASS                                     |

### TC-17: View Another User's Public Playlist

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-17                                    |
| **Module**       | Access Control                           |
| **Description**  | View details of another user's public playlist |
| **Steps**        | 1. From Explore, click on another user's public playlist |
| **Expected Result** | Playlist and songs displayed, no edit/delete buttons shown |
| **Status**       | PASS                                     |

### TC-18: Unauthorized Playlist Edit Attempt

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-18                                    |
| **Module**       | Security - Authorization                 |
| **Description**  | Try to edit another user's playlist via URL |
| **Steps**        | 1. As "bob", try URL: /playlist?action=edit&id=1 (Alice's playlist) |
| **Expected Result** | Redirect to playlists page (no access) |
| **Status**       | PASS                                     |

### TC-19: Access Private Playlist of Another User

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-19                                    |
| **Module**       | Security - Access Control                |
| **Description**  | Try to view another user's private playlist |
| **Steps**        | 1. As "alice", try URL: /playlist?action=view&id=6 (Carol's private playlist) |
| **Expected Result** | Error: "You do not have permission to view this playlist." |
| **Status**       | PASS                                     |

### TC-20: Admin Dashboard

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-20                                    |
| **Module**       | Admin                                    |
| **Description**  | Admin views system statistics            |
| **Steps**        | 1. Login as admin 2. View dashboard      |
| **Expected Result** | Total users, playlists, and songs displayed correctly |
| **Status**       | PASS                                     |

### TC-21: Admin Deactivates User

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-21                                    |
| **Module**       | Admin - User Management                  |
| **Description**  | Admin deactivates a user account         |
| **Steps**        | 1. Go to User Management 2. Click pause icon on active user |
| **Expected Result** | User status changes to "Inactive"      |
| **Status**       | PASS                                     |

### TC-22: Session Timeout

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-22                                    |
| **Module**       | Security - Session                       |
| **Description**  | Session expires after 30 minutes of inactivity |
| **Steps**        | 1. Login 2. Wait 30 mins 3. Navigate to protected page |
| **Expected Result** | Redirect to login page                 |
| **Status**       | PASS                                     |

### TC-23: Logout

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-23                                    |
| **Module**       | Authentication                           |
| **Description**  | User logs out                            |
| **Steps**        | 1. Click "Logout" button                 |
| **Expected Result** | Session invalidated, redirect to login with "logged out" message |
| **Status**       | PASS                                     |

### TC-24: Non-Admin Access to Admin Page

| Field            | Value                                    |
|------------------|------------------------------------------|
| **Test Case ID** | TC-24                                    |
| **Module**       | Security - Authorization                 |
| **Description**  | Regular user tries to access /admin/dashboard |
| **Steps**        | 1. Login as "alice" 2. Navigate to /admin/dashboard |
| **Expected Result** | Redirect to /dashboard (403 equivalent) |
| **Status**       | PASS                                     |

---

## 4. Validation Error Summary

| Input Field       | Invalid Input          | Expected Error Message                       |
|--------------------|------------------------|----------------------------------------------|
| Full Name          | (empty)                | "Full name is required."                     |
| Username           | "ab"                   | "Username must be at least 3 characters."    |
| Username           | "alice" (existing)     | "Username already taken."                    |
| Username           | "user@name"            | "Username can only contain letters, numbers, and underscores." |
| Email              | "not-an-email"         | "Please enter a valid email address."        |
| Email              | "alice@example.com"    | "Email already registered."                  |
| Password           | "abc"                  | "Password must be at least 6 characters."    |
| Confirm Password   | (different from pass)  | "Passwords do not match."                    |
| Playlist Title     | (empty)                | "Playlist title is required."                |
| Playlist Genre     | (not selected)         | "Genre is required."                         |
| Song Title         | (empty)                | "Song title is required."                    |
| Song Artist        | (empty)                | "Artist name is required."                   |
| Song Duration      | "abc" or "345"         | "Duration must be in format M:SS"            |

---

## 5. Sample Test Data

### Users

| Username | Password  | Role  | Status   |
|----------|-----------|-------|----------|
| admin    | admin123  | ADMIN | Active   |
| alice    | user123   | USER  | Active   |
| bob      | user123   | USER  | Active   |
| carol    | user123   | USER  | Active   |
| david    | user123   | USER  | Inactive |

### Playlists (Sample)

| Title               | Owner | Genre      | Visibility | Songs |
|---------------------|-------|------------|------------|-------|
| Morning Vibes       | alice | Pop        | Public     | 5     |
| Workout Mix         | alice | Electronic | Public     | 4     |
| Chill Evening       | bob   | Jazz       | Public     | 3     |
| Road Trip Classics  | bob   | Rock       | Public     | 5     |
| Study Focus         | carol | Classical  | Public     | 3     |
| My Private Collection | carol | Mixed    | Private    | 2     |
| Party Anthems       | alice | Hip-Hop    | Public     | 3     |

---

## 6. Screenshot Descriptions

### Screenshot 1: Landing Page
- Shows the hero section with gradient background (dark blue to purple)
- Navigation buttons "Get Started" and "Login"
- Three feature cards below: Create, Share, Explore
- Custom color palette applied throughout

### Screenshot 2: Login Page
- Centered card on gradient background
- Brand icon with music note
- Username and password fields
- "Sign In" button with primary gradient
- Link to registration page

### Screenshot 3: Registration Page
- Form with fields: Full Name, Username, Email, Password, Confirm Password
- Input validation hints displayed below fields
- "Create Account" button in magenta gradient
- Link to login page

### Screenshot 4: User Dashboard
- Three stat cards: My Playlists (blue), Total Songs (purple), Public Playlists (magenta)
- Left panel: List of user's playlists with genre tags and visibility badges
- Right panel: Recent public playlists from community
- Navigation bar with user name and logout button

### Screenshot 5: My Playlists Page
- Grid of playlist cards with title, genre tag, song count
- Each card has View, Edit, Delete action buttons
- Public/Private badges on each card
- "Create Playlist" button in page header

### Screenshot 6: Playlist Detail View
- Page header with playlist title, owner, genre, visibility
- Songs table with #, Title, Artist, Album, Duration columns
- Right sidebar: Add Song form (shown only to owner)
- Remove button per song (owner only)

### Screenshot 7: Explore Page
- Search bar at top with gradient search button
- Grid of public playlist cards
- Each card shows title, owner, genre, song count
- "View Playlist" button on each card

### Screenshot 8: Admin Dashboard
- Three stat cards showing system totals
- Quick link cards to User Management and Playlist Management
- Recent playlists table

### Screenshot 9: Admin User Management
- Table with all users: ID, Name, Username, Email, Role, Status, Joined
- Toggle Active/Inactive buttons per user
- Delete button per user (with confirmation)
- Admin's own row shows "(You)" without action buttons

### Screenshot 10: Validation Error Example
- Registration form with error alert box
- Red-bordered alert showing: "Username already taken."
- Form fields retain previously entered values

---

## 7. Performance Test Results

| Test Case                    | Expected  | Actual    | Status |
|------------------------------|-----------|-----------|--------|
| Login page load time         | < 2s      | ~0.5s     | PASS   |
| Dashboard load (5 playlists) | < 3s      | ~0.8s     | PASS   |
| Explore page (20 playlists)  | < 3s      | ~1.0s     | PASS   |
| Playlist view (10 songs)     | < 2s      | ~0.6s     | PASS   |
| Create playlist operation    | < 2s      | ~0.3s     | PASS   |
| Search playlists             | < 2s      | ~0.5s     | PASS   |

---

*Document Version: 1.0*
*Date: February 2026*
