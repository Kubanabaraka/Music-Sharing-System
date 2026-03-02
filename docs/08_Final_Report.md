# MUSIC PLAYLIST SHARING SYSTEM (MPSS)

## Final Academic Project Report

---

**Institution:** [Your Institution Name]
**Department:** [Department of Computer Science / IT]
**Academic Year:** 2025/2026
**Submission Date:** February 2026

**Developed By:** [Student Name(s)]
**Supervisor:** [Supervisor Name]

---

## Table of Contents

1. Abstract
2. Introduction
   - 2.1 Background
   - 2.2 Problem Statement
   - 2.3 Objectives
   - 2.4 Scope
   - 2.5 Significance
3. Literature Review
4. Methodology
   - 4.1 Development Approach
   - 4.2 MVC Architecture
   - 4.3 Technologies Used
   - 4.4 Tools Used
5. System Analysis
   - 5.1 Functional Requirements
   - 5.2 Non-Functional Requirements
   - 5.3 User Roles
   - 5.4 Use Cases
6. System Design
   - 6.1 Architecture Diagram
   - 6.2 Use Case Diagram
   - 6.3 Class Diagram
   - 6.4 Sequence Diagrams
   - 6.5 Database Design (ER Diagram)
   - 6.6 Database Schema
7. Implementation
   - 7.1 Project Structure
   - 7.2 Database Implementation
   - 7.3 Model Layer
   - 7.4 Data Access Layer (DAO)
   - 7.5 Controller Layer (Servlets)
   - 7.6 View Layer (JSP)
   - 7.7 Security Implementation
   - 7.8 UI Design
8. Testing & Results
   - 8.1 Testing Strategy
   - 8.2 Test Cases
   - 8.3 Results Summary
   - 8.4 Screenshots
9. Conclusion
10. Future Enhancements
11. References
12. Appendices

---

## 1. Abstract

The Music Playlist Sharing System (MPSS) is a web-based application designed to enable users to create, manage, and share music playlists in a collaborative online environment. The system addresses the growing need for a platform-independent tool that allows music enthusiasts to organize their favorite tracks and discover playlists curated by others.

Built using Java Enterprise technologies — specifically Java Servlets, JavaServer Pages (JSP), and JDBC — the system follows the Model-View-Controller (MVC) architectural pattern for clean separation of concerns. PostgreSQL serves as the relational database management system, ensuring data integrity through normalized schemas with proper constraints.

Key features include user registration and authentication with SHA-256 password hashing, full CRUD operations for playlists and songs, role-based access control (Regular User and Administrator), public/private playlist visibility, playlist search and discovery, and a responsive user interface built with Bootstrap 5.

The system demonstrates practical application of web development concepts including session management, input validation, SQL injection prevention through PreparedStatements, and enterprise-level code organization. Testing confirms that all functional and non-functional requirements are met, producing a reliable and user-friendly application suitable for deployment on Apache Tomcat.

**Keywords:** Music Playlist, Web Application, Java Servlets, JSP, MVC Architecture, PostgreSQL, CRUD Operations

---

## 2. Introduction

### 2.1 Background

Music consumption has evolved significantly in the digital age. With the rise of streaming services like Spotify, Apple Music, and YouTube Music, users have access to millions of songs. A key feature of these platforms is the ability to create playlists — curated collections of songs organized by theme, mood, genre, or personal preference.

However, sharing playlists across different platforms remains limited. Users often want to share their music curation with friends, study groups, or communities without being locked into a specific service. Furthermore, educational institutions and community groups may need a lightweight, self-hosted solution for organizing and sharing music recommendations.

### 2.2 Problem Statement

Despite the abundance of music streaming platforms, there is a lack of platform-independent, self-hosted web applications that allow users to:
- Create and manage music playlists without platform lock-in
- Share playlists publicly or keep them private
- Discover playlists created by other users
- Manage the system through an administrative interface

Existing solutions are tightly coupled to specific streaming services and do not offer a simple, deployable web application that can be used within organizations or communities independently.

### 2.3 Objectives

#### General Objective
To design, develop, and deploy a web-based Music Playlist Sharing System that enables collaborative music playlist management with proper authentication and access control.

#### Specific Objectives
1. To implement a secure user registration and authentication system with role-based access control.
2. To develop full CRUD (Create, Read, Update, Delete) functionality for playlists and songs.
3. To enable playlist sharing through public/private visibility settings.
4. To provide a search and discovery feature for public playlists.
5. To implement an administrative interface for user and content management.
6. To design a responsive, professional user interface following modern UI/UX principles.
7. To apply the MVC architectural pattern for maintainable code organization.
8. To use PostgreSQL as the database with proper normalization and constraints.

### 2.4 Scope

**In Scope:**
- User authentication (registration, login, logout, session management)
- Playlist CRUD operations with genre categorization
- Song management within playlists
- Public/private playlist visibility control
- Playlist browsing and search functionality
- Admin dashboard with statistics and user management
- Responsive web interface with custom branding
- Server-side input validation and error handling

**Out of Scope:**
- Audio file upload or music streaming
- Integration with third-party music APIs
- Real-time collaborative editing
- Mobile native applications
- Payment or subscription systems

### 2.5 Significance

This project demonstrates the practical application of Java Enterprise web development concepts in building a real-world system. It provides students with hands-on experience in:
- Implementing the MVC pattern in a Java web environment
- Working with relational databases using JDBC
- Building secure authentication systems
- Creating responsive UIs with Bootstrap
- Deploying web applications on Apache Tomcat

---

## 3. Literature Review

### 3.1 Web Application Architecture

Web applications have evolved from simple CGI scripts to sophisticated multi-tier architectures. The most prevalent pattern is the three-tier architecture consisting of presentation, business logic, and data access layers (Fowler, 2002). The Model-View-Controller (MVC) pattern, originally described by Trygve Reenskaug in 1979, has become the preferred approach for organizing web applications due to its clean separation of concerns (Gamma et al., 1994).

### 3.2 Java Web Technologies

Java Servlets, introduced by Sun Microsystems in 1997, provide a server-side programming model for handling HTTP requests and responses. Combined with JavaServer Pages (JSP), which allow embedding Java code in HTML templates, they form the foundation of Java web development (Oracle, 2023).

The Java Database Connectivity (JDBC) API provides a standard interface for connecting Java applications to relational databases. Using PreparedStatements for parameterized queries prevents SQL injection attacks, one of the most common web security vulnerabilities (OWASP, 2023).

### 3.3 Database Management

PostgreSQL is an advanced, open-source relational database management system known for its reliability, data integrity, and extensibility. It supports ACID transactions, foreign keys, views, triggers, and stored procedures, making it suitable for enterprise applications (PostgreSQL Documentation, 2024).

Database normalization, particularly achieving Third Normal Form (3NF), ensures data integrity by eliminating redundancy and dependency anomalies (Codd, 1970). This project's database schema adheres to 3NF principles.

### 3.4 User Interface Design

Modern web UI design emphasizes responsiveness, accessibility, and visual consistency. Bootstrap, originally developed at Twitter, provides a comprehensive CSS framework for building responsive layouts (Bootstrap Documentation, 2023). Custom theming through CSS variables allows for branded, professional interfaces.

### 3.5 Authentication and Security

Web application security encompasses authentication (verifying user identity), authorization (controlling access to resources), and data protection (OWASP, 2023). Hash-based password storage using algorithms such as SHA-256 prevents plaintext password exposure in case of data breaches.

---

## 4. Methodology

### 4.1 Development Approach

This project follows the **Waterfall Development Model** with the following phases:

1. **Requirements Gathering:** Identifying system requirements through analysis of user needs
2. **System Design:** Creating architecture, class, and database diagrams
3. **Implementation:** Coding the system in Java with JSP/Servlet technology
4. **Testing:** Executing functional, validation, and security test cases
5. **Deployment:** Configuring and deploying on Apache Tomcat
6. **Documentation:** Preparing academic documentation

### 4.2 MVC Architecture

The system is structured using the **Model-View-Controller (MVC)** pattern:

```
┌─────────────────────────────────────────────────────┐
│                  BROWSER (Client)                    │
└─────────────────┬───────────────────────────────────┘
                  │ HTTP Request
                  ▼
┌─────────────────────────────────────────────────────┐
│              CONTROLLER (Servlets)                   │
│  AuthServlet | PlaylistServlet | SongServlet         │
│  DashboardServlet | ExploreServlet | AdminServlet    │
└────────┬─────────────────────────────┬──────────────┘
         │ Call DAO                     │ Forward to JSP
         ▼                             ▼
┌────────────────────┐    ┌───────────────────────────┐
│   MODEL (Beans+DAO)│    │      VIEW (JSP Pages)     │
│  User | Playlist   │    │  login.jsp | dashboard.jsp│
│  Song | UserDAO    │    │  playlists.jsp | etc.     │
│  PlaylistDAO       │    │  + Bootstrap 5 CSS        │
│  SongDAO           │    │  + Custom style.css       │
└────────┬───────────┘    └───────────────────────────┘
         │ JDBC
         ▼
┌─────────────────────────────────────────────────────┐
│               PostgreSQL Database                    │
│            users | playlists | songs                 │
└─────────────────────────────────────────────────────┘
```

**Model:** JavaBeans (User, Playlist, Song) and DAO classes handle data representation and database operations.

**View:** JSP pages render the user interface with Bootstrap 5 and custom CSS styling.

**Controller:** Servlets process HTTP requests, apply business logic, and route to appropriate views.

### 4.3 Technologies Used

| Layer        | Technology              | Purpose                       |
|--------------|------------------------|-------------------------------|
| Language     | Java (JDK 8+)          | Core programming language     |
| Web          | Servlets 4.0           | HTTP request handling         |
| View         | JSP 2.3                | Dynamic HTML generation       |
| Database     | PostgreSQL 12+         | Relational data storage       |
| DB Access    | JDBC                   | Database connectivity         |
| Frontend     | Bootstrap 5.3          | Responsive UI framework       |
| Icons        | Bootstrap Icons        | UI iconography                |
| Server       | Apache Tomcat 9+       | Web application server        |

### 4.4 Tools Used

| Tool            | Purpose                              |
|-----------------|--------------------------------------|
| VS Code         | Code editing                         |
| psql            | PostgreSQL client                    |
| javac           | Java compiler                        |
| Apache Ant      | Build automation (optional)          |
| Git             | Version control                      |
| Chrome DevTools | Frontend debugging                   |

---

## 5. System Analysis

### 5.1 Functional Requirements

| ID    | Requirement                                    | Priority |
|-------|------------------------------------------------|----------|
| FR-01 | User registration with validation              | High     |
| FR-02 | User login/logout with session management       | High     |
| FR-03 | CRUD operations for playlists                   | High     |
| FR-04 | Add/remove songs from playlists                 | High     |
| FR-05 | Public/private playlist visibility              | High     |
| FR-06 | Browse and search public playlists              | Medium   |
| FR-07 | Admin user management (activate/deactivate)     | Medium   |
| FR-08 | Admin view all playlists                        | Medium   |
| FR-09 | Dashboard with statistics                       | Medium   |
| FR-10 | Role-based access control (User/Admin)          | High     |

### 5.2 Non-Functional Requirements

| ID     | Requirement                                   | Metric           |
|--------|-----------------------------------------------|------------------|
| NFR-01 | Page load time under normal conditions        | < 3 seconds      |
| NFR-02 | Password storage security                      | SHA-256 hashing  |
| NFR-03 | SQL injection prevention                       | PreparedStatements|
| NFR-04 | Session timeout                                | 30 minutes       |
| NFR-05 | Responsive design                              | Desktop + Tablet |
| NFR-06 | Browser compatibility                          | Chrome, Firefox  |
| NFR-07 | Code maintainability                           | MVC pattern      |

### 5.3 User Roles

**Regular User:**
- Register and manage account
- Create, view, edit, delete own playlists
- Add/remove songs from own playlists
- Browse and search public playlists
- View details of any public playlist

**Administrator:**
- All Regular User capabilities
- View all users and their statuses
- Activate/deactivate user accounts
- Delete any user (except self)
- View and delete any playlist
- Access system statistics dashboard

### 5.4 Use Cases

*(Detailed use case descriptions are provided in the System Analysis document — docs/02_System_Analysis.md)*

Key Use Cases:
1. UC-01: User Registration
2. UC-02: User Login
3. UC-03: Create Playlist
4. UC-04: Add Song to Playlist
5. UC-05: Browse Public Playlists
6. UC-06: Admin Manages Users

---

## 6. System Design

### 6.1 Architecture Diagram

The system follows a standard three-tier web architecture:

```
┌──────────────┐     HTTP      ┌──────────────┐     JDBC     ┌──────────────┐
│   Browser    │ ◄───────────► │   Tomcat     │ ◄──────────► │  PostgreSQL  │
│  (Client)    │               │  (Server)    │              │  (Database)  │
│              │               │              │              │              │
│ HTML/CSS/JS  │               │ Servlets     │              │ mpss_db      │
│ Bootstrap 5  │               │ JSP Pages    │              │ - users      │
│              │               │ Java Classes │              │ - playlists  │
│              │               │ AuthFilter   │              │ - songs      │
└──────────────┘               └──────────────┘              └──────────────┘
```

### 6.2 Use Case Diagram

*(Full ASCII use case diagram provided in docs/03_System_Design.md)*

### 6.3 Class Diagram

The application consists of the following key classes organized by package:

**com.mpss.model** (JavaBeans):
- `User` — id, fullName, username, email, password, role, active, createdAt
- `Playlist` — id, userId, title, description, genre, isPublic, timestamps
- `Song` — id, title, artist, album, duration, playlistId

**com.mpss.dao** (Data Access Objects):
- `UserDAO` — register, login, findById, findAll, updateStatus, delete, countAll
- `PlaylistDAO` — create, findById, findByUserId, findPublic, update, delete, search
- `SongDAO` — addSong, findByPlaylistId, delete, countAll

**com.mpss.controller** (Servlets):
- `AuthServlet` — /login, /register, /logout
- `DashboardServlet` — /dashboard
- `PlaylistServlet` — /playlist, /playlists
- `SongServlet` — /song
- `ExploreServlet` — /explore
- `AdminServlet` — /admin/dashboard, /admin/users, /admin/playlists

**com.mpss.util** (Utilities):
- `DBConnection` — JDBC connection factory
- `PasswordUtil` — SHA-256 password hashing

**com.mpss.filter** (Filters):
- `AuthFilter` — Authentication and authorization filter

### 6.4 Sequence Diagrams

**User Login Sequence:**
```
Browser → AuthServlet: POST /login (username, password)
AuthServlet → UserDAO: login(username, password)
UserDAO → PasswordUtil: hashPassword(password)
UserDAO → DBConnection: getConnection()
DBConnection → PostgreSQL: SELECT * FROM users WHERE...
PostgreSQL → UserDAO: ResultSet
UserDAO → AuthServlet: User object (or null)
AuthServlet → Session: setAttribute("currentUser", user)
AuthServlet → Browser: redirect /dashboard
```

**Create Playlist Sequence:**
```
Browser → AuthFilter: POST /playlist?action=create
AuthFilter → PlaylistServlet: (session valid)
PlaylistServlet → validate(): check title, genre
PlaylistServlet → PlaylistDAO: create(playlist)
PlaylistDAO → DBConnection: getConnection()
DBConnection → PostgreSQL: INSERT INTO playlists...
PostgreSQL → PlaylistDAO: success
PlaylistDAO → PlaylistServlet: true
PlaylistServlet → Browser: redirect /playlists?message=created
```

### 6.5 Database Design (ER Diagram)

```
┌──────────────────┐       ┌──────────────────────┐       ┌──────────────────┐
│      users       │       │      playlists       │       │      songs       │
├──────────────────┤       ├──────────────────────┤       ├──────────────────┤
│ PK id (SERIAL)   │──┐    │ PK id (SERIAL)       │──┐    │ PK id (SERIAL)   │
│    full_name     │  │    │ FK user_id           │  │    │    title         │
│    username (UQ) │  └───►│    title             │  │    │    artist        │
│    email (UQ)    │       │    description       │  └───►│    album         │
│    password      │       │    genre             │       │    duration      │
│    role (CHECK)  │       │    is_public         │       │ FK playlist_id   │
│    active        │       │    created_at        │       │    created_at    │
│    created_at    │       │    updated_at        │       └──────────────────┘
└──────────────────┘       └──────────────────────┘

Relationships:
  users (1) ──── (*) playlists : One user owns many playlists
  playlists (1) ── (*) songs   : One playlist contains many songs
  ON DELETE CASCADE on both foreign keys
```

### 6.6 Database Schema

The database is normalized to **Third Normal Form (3NF)**:

- **1NF:** All columns contain atomic values; no repeating groups.
- **2NF:** All non-key attributes depend on the entire primary key (no partial dependencies since we use single-column PKs).
- **3NF:** No transitive dependencies — all non-key attributes depend only on the primary key.

Tables:
1. `users` — 8 columns, PK: id, UNIQUE: username, email
2. `playlists` — 8 columns, PK: id, FK: user_id → users(id)
3. `songs` — 7 columns, PK: id, FK: playlist_id → playlists(id)

---

## 7. Implementation

### 7.1 Project Structure

```
Music Playlist Sharing System/
├── docs/                          # Documentation
│   ├── 01_Project_Proposal.md
│   ├── 02_System_Analysis.md
│   ├── 03_System_Design.md
│   ├── 06_Testing_Documentation.md
│   ├── 07_Deployment_Guide.md
│   └── 08_Final_Report.md
├── db/
│   └── schema.sql                 # Database creation script
├── src/main/
│   ├── java/com/mpss/
│   │   ├── model/                 # JavaBeans (Model)
│   │   │   ├── User.java
│   │   │   ├── Playlist.java
│   │   │   └── Song.java
│   │   ├── dao/                   # Data Access Objects
│   │   │   ├── UserDAO.java
│   │   │   ├── PlaylistDAO.java
│   │   │   └── SongDAO.java
│   │   ├── controller/            # Servlets (Controller)
│   │   │   ├── AuthServlet.java
│   │   │   ├── DashboardServlet.java
│   │   │   ├── PlaylistServlet.java
│   │   │   ├── SongServlet.java
│   │   │   ├── ExploreServlet.java
│   │   │   ├── AdminServlet.java
│   │   │   └── HomeServlet.java
│   │   ├── util/                  # Utilities
│   │   │   ├── DBConnection.java
│   │   │   └── PasswordUtil.java
│   │   └── filter/                # Servlet Filters
│   │       └── AuthFilter.java
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml
│       ├── css/
│       │   └── style.css          # Custom CSS (color palette)
│       ├── jsp/
│       │   ├── includes/
│       │   │   ├── header.jsp
│       │   │   ├── navbar.jsp
│       │   │   └── footer.jsp
│       │   ├── landing.jsp
│       │   ├── login.jsp
│       │   ├── register.jsp
│       │   ├── dashboard.jsp
│       │   ├── playlists.jsp
│       │   ├── playlist_form.jsp
│       │   ├── playlist_view.jsp
│       │   ├── explore.jsp
│       │   ├── admin_dashboard.jsp
│       │   ├── admin_users.jsp
│       │   ├── admin_playlists.jsp
│       │   └── error.jsp
│       └── index.jsp
├── lib/                           # JAR dependencies
├── build.xml                      # Ant build script
├── build.sh                       # Shell build script
└── README.md
```

### 7.2 Database Implementation

The database was implemented in PostgreSQL using the script in `db/schema.sql`. Key design decisions:

- **SERIAL** primary keys for auto-incrementing IDs
- **CASCADE DELETE** on foreign keys to maintain referential integrity
- **CHECK** constraint on user role to enforce valid values
- **UNIQUE** constraints on username and email to prevent duplicates
- **Indexes** on frequently queried columns (user_id, is_public, genre, playlist_id)

### 7.3 Model Layer

Three JavaBeans represent the domain entities:

- **User.java**: Implements `Serializable`, includes utility method `isAdmin()` for role checking
- **Playlist.java**: Includes transient fields `ownerUsername` and `songCount` for display purposes
- **Song.java**: Simple bean mapping directly to the songs table

### 7.4 Data Access Layer (DAO)

Each DAO class follows these practices:
- **Try-with-resources** for automatic connection/statement/resultset closing
- **PreparedStatements** exclusively for SQL injection prevention
- **Centralized DB connection** through `DBConnection.getConnection()`
- **Proper error handling** with stderr logging and graceful fallbacks

### 7.5 Controller Layer (Servlets)

Servlets use `@WebServlet` annotation-based mapping. Key patterns:
- **Front Controller** approach with action parameters for CRUD routing
- **PRG Pattern** (Post-Redirect-Get) to prevent duplicate form submissions
- **Authorization checks** in every mutating operation
- **Input validation** before any database operation

### 7.6 View Layer (JSP)

JSP pages use JSTL (`<c:if>`, `<c:forEach>`, `<c:choose>`) for clean, scriptlet-free templates. Common elements (header, navbar, footer) are extracted as include files.

### 7.7 Security Implementation

| Security Feature            | Implementation                              |
|-----------------------------|---------------------------------------------|
| Password Storage            | SHA-256 hashing via `PasswordUtil`          |
| SQL Injection Prevention    | `PreparedStatement` with parameter binding  |
| Session Management          | HTTP session with 30-minute timeout         |
| Authentication Filter       | `AuthFilter` protects all non-public pages  |
| Authorization               | Role checking in filter and servlets        |
| CSRF Protection             | POST-only for state-changing operations     |
| Input Validation            | Server-side validation in all servlets      |

### 7.8 UI Design

The UI follows a cohesive color palette:

| Color Name       | Hex Code  | Usage                              |
|------------------|-----------|------------------------------------|
| Primary          | #213885   | Navbar, buttons, table headers     |
| Dark Accent      | #081849   | Page headers, gradients            |
| Secondary Cream  | #ECDFD2   | Genre tags, accent backgrounds     |
| Neutral Gray     | #CCCACC   | Muted text, subtle backgrounds     |
| Highlight Purple | #5F3475   | Stat cards, gradients              |
| Accent Magenta   | #893172   | CTA buttons, badges, highlights    |

Custom CSS uses CSS Variables for consistent theming, gradients for visual depth, and smooth transitions for interactive elements.

---

## 8. Testing & Results

### 8.1 Testing Strategy

Testing was conducted using:
- **Unit-level testing** of DAO methods with sample data
- **Functional testing** of all user-facing features
- **Validation testing** of all input forms
- **Security testing** of authentication and authorization
- **Boundary testing** of edge cases

### 8.2 Test Cases Summary

| Category          | Test Cases | Passed | Failed |
|-------------------|-----------|--------|--------|
| Authentication    | 7         | 7      | 0      |
| Playlist CRUD     | 4         | 4      | 0      |
| Song Management   | 3         | 3      | 0      |
| Search/Browse     | 2         | 2      | 0      |
| Admin Functions   | 2         | 2      | 0      |
| Security/Access   | 4         | 4      | 0      |
| Session           | 2         | 2      | 0      |
| **Total**         | **24**    | **24** | **0**  |

*(Detailed test cases available in docs/06_Testing_Documentation.md)*

### 8.3 Results Summary

All 24 test cases passed successfully. The system correctly:
- Registers and authenticates users
- Enforces role-based access control
- Performs all CRUD operations on playlists and songs
- Validates all user input with meaningful error messages
- Handles session management and timeout
- Prevents unauthorized access to protected resources

### 8.4 Screenshots

*(See Section 6 of Testing Documentation for detailed screenshot descriptions)*

---

## 9. Conclusion

The Music Playlist Sharing System was successfully designed, developed, and tested as a complete web application meeting all specified requirements. The project demonstrates the effective application of:

1. **MVC Architecture** — Clear separation between data (Model), presentation (View), and logic (Controller), resulting in maintainable and extensible code.

2. **Java Enterprise Technologies** — Practical use of Servlets, JSP, JSTL, JDBC, and Servlet Filters in building a real-world web application.

3. **Database Design** — A properly normalized PostgreSQL schema with referential integrity, constraints, and indexes for performance.

4. **Security Best Practices** — Password hashing, SQL injection prevention, session management, and role-based access control.

5. **Professional UI Design** — A responsive, visually appealing interface using Bootstrap 5 with a custom color palette.

6. **Software Engineering Principles** — Requirements analysis, system design with UML-style diagrams, structured testing, and comprehensive documentation.

The system is production-ready and can be deployed on any environment running Apache Tomcat and PostgreSQL. It provides a solid foundation for future enhancements and can serve as a reference implementation for Java web development projects.

---

## 10. Future Enhancements

1. **Music API Integration** — Connect to Spotify or YouTube Music APIs to pre-populate song metadata.
2. **Social Features** — Allow users to follow each other and receive notifications on new playlists.
3. **Collaborative Playlists** — Enable multiple users to contribute to a shared playlist.
4. **Rating and Reviews** — Allow users to rate and comment on public playlists.
5. **Audio Preview** — Integrate audio snippets using embedded players.
6. **RESTful API** — Expose a REST API for mobile application development.
7. **Advanced Search** — Full-text search with filters for genre, popularity, and recency.
8. **Email Verification** — Verify user email addresses during registration.
9. **Pagination** — Implement pagination for large datasets.
10. **Caching** — Add connection pooling (HikariCP) and result caching for improved performance.
11. **Docker Deployment** — Containerize the application for easier deployment.
12. **Password Reset** — Implement forgot-password functionality via email.

---

## 11. References

1. Fowler, M. (2002). *Patterns of Enterprise Application Architecture*. Addison-Wesley.
2. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.
3. Codd, E. F. (1970). "A Relational Model of Data for Large Shared Data Banks." *Communications of the ACM*, 13(6), 377-387.
4. Oracle. (2023). *Java Servlet Specification 4.0*. https://javaee.github.io/servlet-spec/
5. Oracle. (2023). *JDBC API Specification*. https://docs.oracle.com/javase/tutorial/jdbc/
6. PostgreSQL Global Development Group. (2024). *PostgreSQL 16 Documentation*. https://www.postgresql.org/docs/
7. Bootstrap Team. (2023). *Bootstrap 5.3 Documentation*. https://getbootstrap.com/docs/5.3/
8. OWASP Foundation. (2023). *OWASP Top Ten Web Application Security Risks*. https://owasp.org/www-project-top-ten/
9. Murach, J. & Urban, M. (2020). *Murach's Java Servlets and JSP*. Mike Murach & Associates.
10. Bauer, C. & King, G. (2015). *Java Persistence with Hibernate*. Manning Publications.

---

## 12. Appendices

### Appendix A: Database Schema SQL
*(See db/schema.sql)*

### Appendix B: Full Source Code Listing
*(Source code is organized under src/main/java/com/mpss/)*

### Appendix C: Build and Deployment Instructions
*(See docs/07_Deployment_Guide.md)*

### Appendix D: Test Cases Detail
*(See docs/06_Testing_Documentation.md)*

### Appendix E: Color Palette Reference

```
Primary Blue:      ████████  #213885
Dark Accent:       ████████  #081849
Secondary Cream:   ████████  #ECDFD2
Neutral Gray:      ████████  #CCCACC
Highlight Purple:  ████████  #5F3475
Accent Magenta:    ████████  #893172
```

---

*End of Report*
