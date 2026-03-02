# Music Playlist Sharing System — System Design Document

---

## 1. MVC Architecture Overview

The Music Playlist Sharing System follows the **Model-View-Controller (MVC)** architectural pattern, ensuring a clean separation of concerns:

```
┌─────────────────────────────────────────────────────────────┐
│                      CLIENT (Browser)                       │
│                  HTML / CSS / Bootstrap / JS                 │
└─────────────────────┬───────────────────────────────────────┘
                      │ HTTP Request
                      ▼
┌─────────────────────────────────────────────────────────────┐
│                   CONTROLLER LAYER                          │
│              (Java Servlets - com.mpss.controller)           │
│                                                             │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────────┐    │
│  │ AuthServlet   │ │PlaylistServlet│ │  SongServlet     │    │
│  └──────────────┘ └──────────────┘ └──────────────────┘    │
│  ┌──────────────┐ ┌──────────────┐ ┌──────────────────┐    │
│  │ AdminServlet  │ │DashboardServ │ │  ExploreServlet  │    │
│  └──────────────┘ └──────────────┘ └──────────────────┘    │
└─────────────────────┬───────────────────────────────────────┘
                      │ Method calls
                      ▼
┌─────────────────────────────────────────────────────────────┐
│                     MODEL LAYER                             │
│                                                             │
│  ┌─────────────────────────────────────────────────────┐    │
│  │          JavaBeans (com.mpss.model)                  │    │
│  │   User.java | Playlist.java | Song.java             │    │
│  └─────────────────────────────────────────────────────┘    │
│  ┌─────────────────────────────────────────────────────┐    │
│  │          DAO Classes (com.mpss.dao)                  │    │
│  │   UserDAO.java | PlaylistDAO.java | SongDAO.java    │    │
│  └─────────────────────┬───────────────────────────────┘    │
│                        │ JDBC                               │
└────────────────────────┼────────────────────────────────────┘
                         ▼
┌─────────────────────────────────────────────────────────────┐
│                  DATABASE LAYER                              │
│              PostgreSQL (mpss_db)                            │
│                                                             │
│   users │ playlists │ songs │ playlist_songs                │
└─────────────────────────────────────────────────────────────┘

                      ▲ Forward to JSP
                      │
┌─────────────────────────────────────────────────────────────┐
│                      VIEW LAYER                             │
│              (JSP Pages - /webapp/jsp/)                      │
│                                                             │
│  ┌────────────┐ ┌────────────┐ ┌────────────────────┐      │
│  │ login.jsp  │ │register.jsp│ │  dashboard.jsp     │      │
│  └────────────┘ └────────────┘ └────────────────────┘      │
│  ┌────────────┐ ┌────────────┐ ┌────────────────────┐      │
│  │playlists   │ │playlist    │ │  explore.jsp       │      │
│  │ .jsp       │ │_form.jsp   │ │                    │      │
│  └────────────┘ └────────────┘ └────────────────────┘      │
│  ┌────────────┐ ┌────────────┐ ┌────────────────────┐      │
│  │admin_      │ │admin_      │ │  error.jsp         │      │
│  │dashboard   │ │users.jsp   │ │                    │      │
│  └────────────┘ └────────────┘ └────────────────────┘      │
└─────────────────────────────────────────────────────────────┘
```

### Flow:
1. **Browser** sends HTTP request to a **Servlet** (Controller).
2. **Servlet** processes business logic, calls **DAO** classes to interact with the database.
3. **DAO** uses **JDBC** with **PreparedStatements** to query/update **PostgreSQL**.
4. **Servlet** sets attributes and forwards to a **JSP** (View).
5. **JSP** renders the HTML response and sends it back to the browser.

---

## 2. Use Case Diagram

```
                        ┌──────────────────────────────┐
                        │   Music Playlist Sharing     │
                        │         System               │
                        │                              │
    ┌─────┐             │  ┌───────────────────────┐   │
    │     │────────────────▶  Register             │   │
    │     │             │  └───────────────────────┘   │
    │     │             │  ┌───────────────────────┐   │
    │     │────────────────▶  Login                │   │
    │     │             │  └───────────────────────┘   │
    │     │             │  ┌───────────────────────┐   │
    │     │────────────────▶  Create Playlist      │   │
    │ U   │             │  └───────────────────────┘   │
    │ S   │             │  ┌───────────────────────┐   │
    │ E   │────────────────▶  Edit Playlist        │   │
    │ R   │             │  └───────────────────────┘   │
    │     │             │  ┌───────────────────────┐   │
    │     │────────────────▶  Delete Playlist      │   │
    │     │             │  └───────────────────────┘   │
    │     │             │  ┌───────────────────────┐   │
    │     │────────────────▶  Add/Remove Songs     │   │
    │     │             │  └───────────────────────┘   │
    │     │             │  ┌───────────────────────┐   │
    │     │────────────────▶  Browse Playlists     │   │
    │     │             │  └───────────────────────┘   │
    │     │             │  ┌───────────────────────┐   │
    │     │────────────────▶  Search Playlists     │   │
    └─────┘             │  └───────────────────────┘   │
                        │                              │
    ┌─────┐             │  ┌───────────────────────┐   │
    │  A  │────────────────▶  Manage Users         │   │
    │  D  │             │  └───────────────────────┘   │
    │  M  │             │  ┌───────────────────────┐   │
    │  I  │────────────────▶  View Statistics      │   │
    │  N  │             │  └───────────────────────┘   │
    │     │             │  ┌───────────────────────┐   │
    │     │────────────────▶  Delete Any Playlist  │   │
    └─────┘             │  └───────────────────────┘   │
                        │                              │
                        └──────────────────────────────┘
```

---

## 3. Class Diagram

```
┌──────────────────────────────┐
│           User               │
├──────────────────────────────┤
│ - id: int                    │
│ - fullName: String           │
│ - username: String           │
│ - email: String              │
│ - password: String           │
│ - role: String               │
│ - active: boolean            │
│ - createdAt: Timestamp       │
├──────────────────────────────┤
│ + getters/setters            │
└──────────────────────────────┘
              │ 1
              │
              │ owns *
              ▼
┌──────────────────────────────┐
│         Playlist             │
├──────────────────────────────┤
│ - id: int                    │
│ - userId: int                │
│ - title: String              │
│ - description: String        │
│ - genre: String              │
│ - isPublic: boolean          │
│ - createdAt: Timestamp       │
│ - updatedAt: Timestamp       │
│ - ownerUsername: String       │
├──────────────────────────────┤
│ + getters/setters            │
└──────────────────────────────┘
              │ 1
              │
              │ contains *
              ▼
┌──────────────────────────────┐
│           Song               │
├──────────────────────────────┤
│ - id: int                    │
│ - title: String              │
│ - artist: String             │
│ - album: String              │
│ - duration: String           │
│ - playlistId: int            │
├──────────────────────────────┤
│ + getters/setters            │
└──────────────────────────────┘

┌──────────────────────────────┐       ┌────────────────────────────┐
│         UserDAO              │       │       DBConnection         │
├──────────────────────────────┤       ├────────────────────────────┤
│ + register(User): boolean    │──────▶│ + getConnection(): Conn    │
│ + login(u, p): User          │       └────────────────────────────┘
│ + findById(id): User         │
│ + findAll(): List<User>      │
│ + updateStatus(id, s): bool  │
│ + delete(id): boolean        │
│ + countAll(): int            │
└──────────────────────────────┘

┌──────────────────────────────┐
│       PlaylistDAO            │
├──────────────────────────────┤
│ + create(Playlist): boolean  │
│ + findById(id): Playlist     │
│ + findByUser(uid): List      │
│ + findPublic(): List         │
│ + update(Playlist): boolean  │
│ + delete(id): boolean        │
│ + search(query): List        │
│ + countAll(): int            │
└──────────────────────────────┘

┌──────────────────────────────┐
│         SongDAO              │
├──────────────────────────────┤
│ + addSong(Song): boolean     │
│ + findByPlaylist(pid): List  │
│ + delete(id): boolean        │
│ + countAll(): int            │
└──────────────────────────────┘
```

---

## 4. Sequence Diagram — User Creates Playlist

```
  Browser          AuthFilter       PlaylistServlet    PlaylistDAO      PostgreSQL
    │                  │                  │                │                │
    │  POST /playlist  │                  │                │                │
    │─────────────────▶│                  │                │                │
    │                  │  check session   │                │                │
    │                  │─────────────────▶│                │                │
    │                  │                  │  validate()    │                │
    │                  │                  │───────┐        │                │
    │                  │                  │◀──────┘        │                │
    │                  │                  │                │                │
    │                  │                  │ create(pl)     │                │
    │                  │                  │───────────────▶│                │
    │                  │                  │                │  INSERT INTO   │
    │                  │                  │                │───────────────▶│
    │                  │                  │                │   OK           │
    │                  │                  │                │◀───────────────│
    │                  │                  │  true          │                │
    │                  │                  │◀───────────────│                │
    │                  │                  │                │                │
    │                  │  redirect        │                │                │
    │◀─────────────────│──────────────────│                │                │
    │   302 /playlists │                  │                │                │
```

---

## 5. Database ER Diagram

```
┌──────────────────┐       ┌──────────────────────┐       ┌──────────────────┐
│      users       │       │      playlists       │       │      songs       │
├──────────────────┤       ├──────────────────────┤       ├──────────────────┤
│ PK id            │       │ PK id                │       │ PK id            │
│    full_name     │       │ FK user_id ──────────│───┐   │    title         │
│    username (UQ) │       │    title             │   │   │    artist        │
│    email (UQ)    │───┐   │    description       │   │   │    album         │
│    password      │   │   │    genre             │   │   │    duration      │
│    role          │   │   │    is_public         │   │   │ FK playlist_id───│──┐
│    active        │   │   │    created_at        │   │   │    created_at    │  │
│    created_at    │   │   │    updated_at        │   │   └──────────────────┘  │
└──────────────────┘   │   └──────────────────────┘   │                         │
                       │              ▲                │                         │
                       │              │                │                         │
                       └──────────────┘                │                         │
                        user_id references             │                         │
                        users(id)                      │  playlist_id references │
                                                       │  playlists(id)          │
                                                       └─────────────────────────┘

    Relationships:
    ─────────────
    users (1) ────── (*) playlists    : One user can have many playlists
    playlists (1) ── (*) songs        : One playlist can have many songs
```

---

## 6. Package Structure

```
com.mpss/
├── model/
│   ├── User.java
│   ├── Playlist.java
│   └── Song.java
├── dao/
│   ├── UserDAO.java
│   ├── PlaylistDAO.java
│   └── SongDAO.java
├── controller/
│   ├── AuthServlet.java
│   ├── DashboardServlet.java
│   ├── PlaylistServlet.java
│   ├── SongServlet.java
│   ├── ExploreServlet.java
│   └── AdminServlet.java
├── util/
│   └── DBConnection.java
└── filter/
    └── AuthFilter.java
```

---

*Document Version: 1.0*
*Date: February 2026*
