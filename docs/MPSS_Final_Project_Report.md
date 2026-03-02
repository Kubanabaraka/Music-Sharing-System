# MUSIC PLAYLIST SHARING SYSTEM (MPSS)

## Final Project Report

---

**Project Title:** Music Playlist Sharing System (MPSS)

**Technology Stack:** Java (JSP, Servlets), MVC Architecture, JDBC, PostgreSQL, Apache Tomcat

**Date:** February 2026

---

## Table of Contents

1. [Abstract](#1-abstract)
2. [Introduction](#2-introduction)
3. [Literature Review](#3-literature-review)
4. [Methodology](#4-methodology)
5. [System Analysis and Design](#5-system-analysis-and-design)
6. [Implementation Details](#6-implementation-details)
7. [Testing and Results](#7-testing-and-results)
8. [Conclusion](#8-conclusion)
9. [Future Enhancements](#9-future-enhancements)
10. [References](#10-references)
11. [Appendices](#11-appendices)

---

## 1. Abstract

The Music Playlist Sharing System (MPSS) is a web-based application designed to address the growing need for a platform where users can create, manage, and share music playlists in a collaborative online environment. In an era where digital music consumption has become the dominant mode of listening, the ability to organise and exchange personalised collections of songs holds significant social and cultural value. This project presents a fully functional system built upon enterprise-grade Java web technologies, adhering to the Model-View-Controller (MVC) architectural pattern to ensure a clean separation of concerns, maintainability, and scalability.

The system was developed using Java Servlets and JavaServer Pages (JSP) for the server-side presentation and control logic, Java Database Connectivity (JDBC) for persistent data access, and PostgreSQL as the relational database management system. Apache Tomcat 10.1 serves as the application server, running under the Jakarta EE namespace. The front-end interface employs Bootstrap 5.3 for responsive, mobile-friendly design, while Jakarta Standard Tag Library (JSTL) is utilised within JSP pages for dynamic content rendering.

MPSS supports two distinct user roles — regular users and administrators — each with tailored functionality. Regular users may register, log in, create and manage personal playlists, add and remove songs, control playlist visibility (public or private), and explore publicly shared playlists created by other users. Administrators are granted elevated privileges including system-wide statistics monitoring, user account management (activation, deactivation, and deletion), and oversight of all playlists across the platform.

Key outcomes of this project include the successful implementation of a secure authentication and session management system with SHA-256 password hashing, a complete CRUD (Create, Read, Update, Delete) lifecycle for users, playlists, and songs, role-based access control enforced through servlet filters, comprehensive input validation, and a normalised relational database schema designed to Third Normal Form (3NF). The system demonstrates how a well-structured Java web application can effectively serve as a collaborative music sharing platform while maintaining code quality, security, and extensibility.

---

## 2. Introduction

### 2.1 Background of the Problem

The digital transformation of the music industry over the past two decades has fundamentally altered how individuals discover, consume, and share music. The transition from physical media to digital streaming has created an enormous ecosystem of online music platforms, each competing to offer users the most compelling tools for managing their listening experiences. Central to this experience is the concept of the playlist — a curated collection of songs arranged according to personal preference, mood, activity, or genre.

Despite the prevalence of commercial music streaming platforms, there exists a clear educational and practical need for understanding how such systems are architecturally designed and technically implemented. From a software engineering perspective, music playlist management systems present a rich domain for exploring fundamental concepts including relational database design, user authentication and authorisation, the MVC architectural pattern, CRUD operations, and responsive web interface design.

Furthermore, many existing commercial platforms operate as closed ecosystems, limiting the ability of users to share playlists across platforms or to customise the sharing experience. A bespoke, open playlist sharing system provides an opportunity to explore these concepts in a controlled, well-understood environment.

### 2.2 Importance of Playlist Sharing Systems

Playlist sharing systems occupy a significant role in the social dimension of music consumption. Research in the field of music information retrieval has consistently demonstrated that social features — such as the ability to share, discover, and collaboratively curate playlists — significantly enhance user engagement and satisfaction with music platforms. The act of sharing a playlist is fundamentally a form of self-expression and social communication, enabling users to convey moods, tastes, and cultural affiliations through their musical selections.

From a technical standpoint, playlist sharing systems serve as exemplary case studies for database-driven web applications. They require the management of interrelated entities (users, playlists, and songs), the enforcement of business rules (ownership, visibility, permissions), and the implementation of both public and private data access patterns — all within a responsive, user-friendly interface.

### 2.3 Purpose of the Project

The primary purpose of this project is to design, implement, and deploy a fully functional Music Playlist Sharing System that demonstrates mastery of enterprise Java web development technologies and software engineering principles. Specifically, the project aims to:

1. Implement a secure, multi-user web application following the Model-View-Controller architectural pattern using Java Servlets, JSP, and JDBC.
2. Design and implement a normalised relational database schema using PostgreSQL to persistently store user accounts, playlists, and song data.
3. Demonstrate proficiency in user authentication, session management, input validation, and role-based access control within a Java web application context.
4. Deliver a responsive, aesthetically polished user interface using Bootstrap 5 that provides intuitive navigation and a professional user experience.
5. Apply software engineering best practices including separation of concerns, the Data Access Object pattern, defensive programming, and comprehensive error handling.

### 2.4 Overview of the System

The Music Playlist Sharing System is a three-tier web application comprising a presentation layer (JSP views with Bootstrap 5), a business logic layer (Java Servlets acting as controllers with DAO classes for data access), and a data persistence layer (PostgreSQL relational database). Users interact with the system through a web browser, with Apache Tomcat 10.1 serving as the Jakarta EE-compliant application server.

The system provides distinct interfaces for two user roles. Regular users access a personalised dashboard displaying their playlists and recent public playlists from the community, a playlist management interface for creating, editing, and deleting playlists, a detailed playlist view for adding and managing songs, and an explore page for discovering publicly shared playlists. Administrators access a separate administration panel featuring system-wide statistics, user account management, and oversight of all playlists on the platform.

---

## 3. Literature Review

### 3.1 Overview of Existing Music Playlist Systems

The landscape of music playlist management is dominated by several major commercial platforms, each offering distinct approaches to playlist creation and sharing.

**Spotify**, launched in 2008, has established itself as the market leader in music streaming with over 600 million users globally. Spotify's playlist ecosystem includes user-generated playlists, collaborative playlists that multiple users can edit simultaneously, and algorithmically generated playlists such as Discover Weekly and Release Radar. Spotify's technical architecture relies on a microservices approach, with playlist data distributed across multiple backend services (Spotify Engineering, 2023). While Spotify provides rich sharing features including social feeds and collaborative editing, its proprietary nature means that playlist data remains locked within the Spotify ecosystem.

**Apple Music**, introduced in 2015, offers a similar playlist management paradigm with an emphasis on editorial curation. Apple Music's "For You" section leverages machine learning to recommend playlists based on listening history, while users can create and share personal playlists through iMessage and social media integration. The system architecture follows Apple's broader iCloud infrastructure, with playlist metadata synchronised across devices through Apple's proprietary synchronisation protocols (Apple Developer Documentation, 2024).

**YouTube Music** represents a distinct approach, leveraging YouTube's existing video infrastructure to provide a music streaming service. Playlists in YouTube Music can contain both audio tracks and music videos, creating a multimedia playlist experience. The system inherits YouTube's social features including comments, likes, and sharing, while adding music-specific curation tools (Google Developers, 2024).

These commercial systems, while feature-rich, share common limitations from an educational and customisation perspective: they are closed-source, operate as walled gardens, and do not expose their architectural decisions for study. The MPSS project addresses this gap by providing a transparent, well-documented implementation of fundamental playlist management concepts.

### 3.2 Web-Based System Architectures

Web-based application architectures have evolved significantly since the early days of static HTML pages served by basic web servers. The contemporary landscape of server-side web development encompasses several architectural paradigms.

The traditional server-side rendering model, employed in this project, generates complete HTML pages on the server in response to each client request. This approach, implemented through technologies such as Java Servlets and JSP, remains widely used in enterprise environments due to its simplicity, reliability, and the maturity of supporting tooling. In this model, the web server receives HTTP requests, processes them through server-side logic, queries the database as necessary, and returns fully rendered HTML responses to the client (Oracle, 2023).

The three-tier architecture — consisting of a presentation tier, application tier, and data tier — provides a logical organisation for enterprise web applications. This architectural model aligns naturally with the technologies employed in this project: JSP pages constitute the presentation tier, Servlets and DAO classes form the application tier, and the PostgreSQL database represents the data tier. This separation enables independent modification of each tier, facilitating maintainability and team-based development (Fowler, 2002).

### 3.3 The Model-View-Controller (MVC) Architectural Pattern

The Model-View-Controller pattern, originally conceived by Trygve Reenskaug at Xerox PARC in 1979, has become the predominant architectural pattern for web application development. MVC divides an application into three interconnected components, each with a distinct responsibility (Gamma et al., 1994).

The **Model** represents the application's data and business rules. In the context of a Java web application, models are typically implemented as JavaBeans — plain Java objects with private fields, public getter and setter methods, and a no-argument constructor. The model encapsulates the state of the application and provides methods for accessing and modifying that state. In the MPSS project, the model layer comprises three JavaBeans (User, Playlist, and Song) complemented by three Data Access Object classes that mediate between the models and the database.

The **View** is responsible for presenting data to the user. In a Java web application employing JSP, the view layer consists of JSP pages that receive data from the controller and render it as HTML. Views should contain minimal logic — ideally limited to conditional rendering and iteration — with all complex processing delegated to the controller or model layers. The MPSS project employs JSTL tags within JSP pages to achieve this separation, avoiding scriptlet code in favour of declarative tag-based rendering.

The **Controller** acts as an intermediary between the model and the view. Controllers receive user input (typically as HTTP requests), invoke the appropriate model operations, and select the correct view for rendering the response. In the Java Servlet specification, HttpServlet classes serve as controllers, with the doGet() and doPost() methods handling HTTP GET and POST requests respectively. The MPSS project implements seven servlet controllers, each handling a logical group of related operations.

The adoption of MVC in this project is justified by several factors: it enforces a clear separation of concerns that improves code organisation and maintainability; it enables parallel development of different layers; it facilitates unit testing by isolating business logic from presentation; and it aligns with industry-standard practices for Java web development.

### 3.4 Database-Driven Web Applications

Database-driven web applications rely on a relational database management system to store and retrieve structured data persistently. The interaction between a Java web application and a relational database is mediated by Java Database Connectivity (JDBC), a standard API that provides a uniform interface for connecting to and querying relational databases from Java code (Oracle, 2023).

PostgreSQL, the database chosen for this project, is an advanced open-source object-relational database system with a strong reputation for reliability, feature robustness, and standards compliance. PostgreSQL supports advanced features including full ACID compliance, complex queries with joins and subqueries, user-defined types, foreign key constraints with cascading operations, and sophisticated indexing strategies (PostgreSQL Global Development Group, 2024). These features make PostgreSQL particularly well-suited for web applications requiring data integrity and complex querying capabilities.

The Data Access Object (DAO) pattern, a core creational pattern in Java enterprise development, provides an abstraction layer between the application's business logic and the database. By encapsulating all data access logic within dedicated DAO classes, the pattern isolates the rest of the application from the specifics of the data storage mechanism. This isolation means that changes to the database schema or even the database technology itself require modifications only within the DAO layer, rather than throughout the entire application (Sun Microsystems, 2002).

---

## 4. Methodology

### 4.1 Development Approach

The development of the Music Playlist Sharing System followed an iterative, incremental methodology adapted from the Agile software development framework. This approach was selected for its flexibility, its suitability for individual developer projects, and its emphasis on producing working software in regular increments. The development process was organised into the following phases:

**Phase 1 — Requirements Gathering and Analysis.** The initial phase involved defining the system's functional and non-functional requirements, identifying user roles and their associated permissions, and establishing the scope of the project. Requirements were documented in the form of use case descriptions and a functional specification.

**Phase 2 — System Design.** The design phase encompassed the creation of the relational database schema, the definition of the MVC component structure, and the planning of the user interface layout. The database was designed following normalisation principles up to Third Normal Form (3NF) to eliminate data redundancy and ensure integrity.

**Phase 3 — Implementation.** The core development phase proceeded iteratively, with each iteration focusing on a specific functional area: database setup and DAO layer, authentication and user management, playlist CRUD operations, song management, the explore/discovery feature, and the administration panel. Each iteration produced a testable increment of functionality.

**Phase 4 — Testing and Debugging.** Testing was conducted continuously throughout development, with focused testing sessions at the completion of each iteration. Testing encompassed functional testing of all CRUD operations, authentication testing, authorisation testing, input validation testing, and integration testing of the complete system.

**Phase 5 — Deployment and Documentation.** The final phase involved deploying the application to Apache Tomcat 10.1, conducting end-to-end system testing, and preparing this project report.

### 4.2 The MVC Architecture in MPSS

The MVC pattern was implemented in the MPSS project through the following component mapping:

**Model Layer.** The model layer consists of three JavaBean classes — User, Playlist, and Song — each representing a table in the PostgreSQL database. These classes implement the Serializable interface for session storage compatibility and follow the JavaBeans convention with private fields and public accessor methods. Complementing the JavaBeans are three Data Access Object (DAO) classes — UserDAO, PlaylistDAO, and SongDAO — that encapsulate all SQL operations for their respective entities. Two utility classes — DBConnection (for database connection management) and PasswordUtil (for SHA-256 password hashing) — provide cross-cutting support services.

**View Layer.** The view layer is implemented as fourteen JSP pages organised into a logical hierarchy. A common header and navigation bar are shared across pages through JSP includes, ensuring visual consistency. JSTL core tags are used for conditional rendering and iteration, and Expression Language (EL) is used to access JavaBean properties. Bootstrap 5.3 provides the CSS framework for responsive layout, and Bootstrap Icons supply the iconography.

**Controller Layer.** Seven HttpServlet classes serve as controllers, each annotated with @WebServlet to define their URL patterns. An AuthFilter (annotated with @WebFilter) intercepts requests to protected URLs, enforcing authentication and role-based access control before requests reach the controllers. Controllers extract request parameters, invoke DAO methods, set request or session attributes, and forward to the appropriate JSP view or issue HTTP redirects.

### 4.3 Justification for Technology Choices

The selection of each technology in the MPSS stack was driven by specific technical and educational considerations.

**Java Servlets and JSP** were chosen as the server-side technologies because they represent the foundational standard for Java web development. Understanding Servlets and JSP provides a conceptual basis for comprehending higher-level frameworks such as Spring MVC and Jakarta Faces, which build upon the same Servlet specification. The use of annotations (@WebServlet, @WebFilter) for configuration eliminates the need for extensive XML configuration while remaining within the standard specification.

**PostgreSQL** was selected over alternatives such as MySQL or SQLite for its superior support for complex constraints, its strict adherence to SQL standards, its robust support for BOOLEAN data types (used extensively in the MPSS schema), and its SERIAL auto-increment facility for primary key generation.

**Apache Tomcat 10.1** was chosen as the application server due to its Jakarta EE compatibility, lightweight footprint, ease of deployment, and widespread adoption in both educational and production environments. Tomcat 10.1 implements the Jakarta Servlet 6.0 and Jakarta Pages 3.1 specifications under the jakarta.servlet namespace.

**Bootstrap 5.3** was employed for front-end styling to deliver a professional, responsive interface without requiring extensive custom CSS development. Bootstrap's grid system, component library, and utility classes enable rapid construction of consistent, mobile-friendly user interfaces.

---

## 5. System Analysis and Design

### 5.1 Functional Requirements

The functional requirements of the Music Playlist Sharing System specify the behaviours and capabilities that the system must provide to its users.

**FR-01: User Registration.** The system shall allow new users to create an account by providing a full name, a unique username, a unique email address, and a password. The system shall validate all registration inputs and reject duplicate usernames or email addresses. Passwords shall be stored as SHA-256 cryptographic hashes, never in plaintext.

**FR-02: User Authentication.** The system shall provide a login mechanism where users authenticate using their username and password. Upon successful authentication, the system shall create an HTTP session and redirect the user to the appropriate dashboard based on their role (user or administrator).

**FR-03: User Session Management.** Authenticated users shall maintain an active session for up to 30 minutes of inactivity. The system shall provide a logout mechanism that invalidates the session and redirects to the login page.

**FR-04: Playlist Creation.** Authenticated users shall be able to create new playlists by specifying a title, optional description, genre (selected from a predefined list), and visibility setting (public or private).

**FR-05: Playlist Management.** Users shall be able to view, edit, and delete their own playlists. Editing shall allow modification of the title, description, genre, and visibility. Deletion shall cascade to all songs within the playlist.

**FR-06: Song Management.** Users shall be able to add songs to their playlists by specifying a title, artist, optional album name, and duration in M:SS format. Users shall be able to remove individual songs from their playlists.

**FR-07: Playlist Exploration.** The system shall provide an explore page listing all public playlists, enabling users to discover playlists shared by other users. A search function shall allow filtering playlists by title or genre.

**FR-08: Admin Dashboard.** Administrators shall have access to a dashboard displaying system-wide statistics including total users, total playlists, and total songs, along with a list of recent public playlists.

**FR-09: User Management (Admin).** Administrators shall be able to view all registered users, activate or deactivate user accounts, and delete user accounts (with the constraint that administrators cannot delete their own account).

**FR-10: Playlist Oversight (Admin).** Administrators shall be able to view and delete any playlist on the platform, regardless of ownership.

### 5.2 Non-Functional Requirements

**NFR-01: Security.** All passwords shall be hashed using SHA-256 before storage. Protected pages shall be accessible only to authenticated users. Administrative pages shall be accessible only to users with the ADMIN role. The system shall implement servlet filters for centralised access control.

**NFR-02: Usability.** The user interface shall be responsive, adapting gracefully to desktop and mobile screen sizes. Navigation shall be intuitive, with clear visual feedback for errors, successes, and empty states.

**NFR-03: Reliability.** The system shall handle errors gracefully, displaying user-friendly error pages for HTTP 404, HTTP 500, and uncaught Java exceptions. Database operations shall not expose stack traces to end users.

**NFR-04: Maintainability.** The codebase shall adhere to the MVC pattern with a clear separation of concerns. All Java classes shall include Javadoc comments. JSP pages shall not contain scriptlet code, using JSTL and EL exclusively.

**NFR-05: Performance.** Database queries shall use indexed columns for frequent lookups. Database connections shall be opened and closed per-request using try-with-resources to prevent connection leaks.

### 5.3 User Roles

The system defines two distinct user roles, each with specific permissions.

**Regular User (role: USER).** A regular user may register a new account, log in and log out, create playlists, edit and delete their own playlists, add and remove songs within their playlists, set playlist visibility to public or private, view other users' public playlists through the explore page, and search for playlists by title or genre.

**Administrator (role: ADMIN).** An administrator inherits all regular user capabilities and additionally may access the admin dashboard with system statistics, view and manage all user accounts, activate or deactivate user accounts, delete user accounts (except their own), and view and delete any playlist on the platform.

### 5.4 Use Case Descriptions

**Use Case 1: Register Account**
- Actor: Unregistered Visitor
- Precondition: Visitor has accessed the registration page.
- Main Flow: The visitor enters a full name, username, email, password, and password confirmation. The system validates all fields, checks for duplicate username and email, hashes the password, creates the user record, and redirects to the login page with a success message.
- Alternate Flow: If validation fails or duplicates are detected, the system redisplays the form with error messages and preserved input values.

**Use Case 2: Authenticate User**
- Actor: Registered User
- Precondition: User has a valid account.
- Main Flow: The user enters their username and password. The system verifies the credentials against the database, checks account active status, creates an HTTP session, and redirects to the appropriate dashboard.
- Alternate Flow: If credentials are invalid, the system displays an error. If the account is deactivated, a specific deactivation message is shown.

**Use Case 3: Create Playlist**
- Actor: Authenticated User
- Precondition: User is logged in.
- Main Flow: The user navigates to the create playlist form, enters a title, optional description, selects a genre and visibility setting, and submits the form. The system creates the playlist and redirects to the playlists page with a success message.
- Alternate Flow: If required fields are missing, the form is redisplayed with validation errors and preserved inputs.

**Use Case 4: Add Song to Playlist**
- Actor: Playlist Owner
- Precondition: User owns the target playlist.
- Main Flow: The user views a playlist and uses the add song form to enter a title, artist, optional album, and duration. The system validates the input, creates the song record, and refreshes the playlist view.
- Alternate Flow: If validation fails, the user is redirected back with an error message.

**Use Case 5: Explore Public Playlists**
- Actor: Authenticated User
- Precondition: User is logged in.
- Main Flow: The user navigates to the explore page. The system retrieves all public playlists and displays them with title, owner, genre, and song count. The user may optionally enter a search query to filter results.

**Use Case 6: Manage Users (Admin)**
- Actor: Administrator
- Precondition: User is logged in with ADMIN role.
- Main Flow: The administrator navigates to the user management page. The system displays all registered users with their status. The administrator may activate or deactivate accounts, or delete user accounts.

### 5.5 System Architecture

The Music Playlist Sharing System follows a three-tier architecture implemented through the Model-View-Controller pattern. The architecture is organised as follows:

**Presentation Tier (View).** The presentation tier comprises fourteen JSP pages and associated static resources (CSS, images). JSP pages receive data as request attributes or session attributes and render the HTML response using JSTL tags and EL expressions. Shared layout elements — the HTML head section, navigation bar, and footer — are factored into JSP include files to eliminate duplication. Bootstrap 5.3 CDN provides the CSS framework and JavaScript for interactive components.

**Application Tier (Controller + Model).** The application tier contains two logical sub-layers. The Controller sub-layer consists of seven HttpServlet classes that receive HTTP requests, extract and validate parameters, invoke business operations through DAO classes, and pass results to the view layer. An AuthFilter provides cross-cutting authentication and authorisation enforcement. The Model sub-layer consists of three JavaBean classes representing the domain entities and three DAO classes encapsulating SQL operations. Two utility classes provide database connection management and password hashing.

**Data Tier.** The data tier is a PostgreSQL 14 relational database containing three tables (users, playlists, songs) with foreign key relationships and cascading delete rules. Performance indexes are defined on frequently queried columns.

The request lifecycle follows this sequence: (1) The client's browser sends an HTTP request to Tomcat. (2) The AuthFilter intercepts the request and verifies the user's session and role. (3) Tomcat dispatches the request to the appropriate Servlet based on URL mapping. (4) The Servlet extracts parameters, calls DAO methods to interact with the database, and sets request attributes. (5) The Servlet forwards to a JSP page or issues a redirect. (6) The JSP page renders the HTML response using the data provided. (7) Tomcat sends the HTTP response to the client.

### 5.6 Database Design

The database for the Music Playlist Sharing System consists of three interrelated tables designed to store user accounts, playlists, and songs.

**Users Table.** The users table stores registered user accounts. Each user record contains a system-generated serial primary key (id), a full name, a unique username, a unique email address, a SHA-256 hashed password, a role indicator (constrained to 'USER' or 'ADMIN' via a CHECK constraint), an active status flag, and a creation timestamp. The username and email columns are constrained with UNIQUE indexes to prevent duplicate registrations.

**Playlists Table.** The playlists table stores playlist metadata. Each record contains a serial primary key (id), a foreign key reference to the owning user (user_id), a title, an optional text description, a genre classification, a boolean visibility flag (is_public), a creation timestamp, and an update timestamp. The user_id foreign key references users(id) with ON DELETE CASCADE, ensuring that all playlists belonging to a deleted user are automatically removed.

**Songs Table.** The songs table stores individual song records within playlists. Each record contains a serial primary key (id), a song title, an artist name, an optional album name, a duration string in M:SS format, a foreign key reference to the containing playlist (playlist_id), and a creation timestamp. The playlist_id foreign key references playlists(id) with ON DELETE CASCADE, ensuring that all songs are removed when their parent playlist is deleted.

### 5.7 Entity-Relationship Diagram Explanation

The entity-relationship model for the MPSS database consists of three entities with two relationships.

The **User** entity possesses attributes id (primary key), full_name, username, email, password, role, active, and created_at. The **Playlist** entity possesses attributes id (primary key), user_id (foreign key), title, description, genre, is_public, created_at, and updated_at. The **Song** entity possesses attributes id (primary key), title, artist, album, duration, playlist_id (foreign key), and created_at.

The relationship between User and Playlist is one-to-many: one user may own zero or more playlists, and each playlist belongs to exactly one user. This relationship is implemented through the user_id foreign key in the playlists table.

The relationship between Playlist and Song is one-to-many: one playlist may contain zero or more songs, and each song belongs to exactly one playlist. This relationship is implemented through the playlist_id foreign key in the songs table.

Both relationships employ cascading deletes: deleting a user cascades to delete all their playlists, and deleting a playlist cascades to delete all its songs. This ensures referential integrity is maintained without orphaned records.

### 5.8 Normalisation

The database schema has been designed in accordance with relational normalisation theory and satisfies the requirements of Third Normal Form (3NF).

**First Normal Form (1NF).** All tables satisfy 1NF because every column contains atomic (indivisible) values, each column has a distinct name within its table, every row is uniquely identifiable by its primary key, and there are no repeating groups or multi-valued attributes. For example, each song is stored as a separate row in the songs table rather than as a comma-separated list within the playlists table.

**Second Normal Form (2NF).** All tables satisfy 2NF because they are already in 1NF and, since each table uses a single-column primary key (the serial id), there can be no partial dependencies on a composite key. Every non-key attribute in each table is fully functionally dependent on the entire primary key.

**Third Normal Form (3NF).** All tables satisfy 3NF because they are in 2NF and no non-key attribute is transitively dependent on the primary key through another non-key attribute. In the users table, all attributes (full_name, username, email, password, role, active, created_at) depend directly on id. In the playlists table, all attributes depend directly on id; the user_id is a foreign key, and the ownerUsername field is a transient, computed attribute not stored in the database. In the songs table, all attributes depend directly on id; the playlist_id is a foreign key establishing the relationship to the parent playlist.

This normalisation eliminates data redundancy, prevents update anomalies, and ensures that each fact is stored in exactly one place within the database.

---

## 6. Implementation Details

### 6.1 Project Structure

The MPSS project follows a standard Java web application directory structure, organised according to the Maven convention for source layout.

```
Music Playlist Sharing System/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/mpss/
│       │       ├── model/           # JavaBeans (Data Model)
│       │       │   ├── User.java
│       │       │   ├── Playlist.java
│       │       │   └── Song.java
│       │       ├── dao/             # Data Access Objects
│       │       │   ├── UserDAO.java
│       │       │   ├── PlaylistDAO.java
│       │       │   └── SongDAO.java
│       │       ├── controller/      # Servlet Controllers
│       │       │   ├── HomeServlet.java
│       │       │   ├── AuthServlet.java
│       │       │   ├── DashboardServlet.java
│       │       │   ├── PlaylistServlet.java
│       │       │   ├── SongServlet.java
│       │       │   ├── ExploreServlet.java
│       │       │   └── AdminServlet.java
│       │       ├── filter/          # Servlet Filters
│       │       │   └── AuthFilter.java
│       │       └── util/            # Utility Classes
│       │           ├── DBConnection.java
│       │           └── PasswordUtil.java
│       └── webapp/
│           ├── WEB-INF/
│           │   └── web.xml          # Deployment Descriptor
│           ├── jsp/                  # JSP View Pages
│           │   ├── includes/
│           │   │   ├── header.jsp
│           │   │   ├── navbar.jsp
│           │   │   └── footer.jsp
│           │   ├── landing.jsp
│           │   ├── login.jsp
│           │   ├── register.jsp
│           │   ├── dashboard.jsp
│           │   ├── playlists.jsp
│           │   ├── playlist_form.jsp
│           │   ├── playlist_view.jsp
│           │   ├── explore.jsp
│           │   ├── admin_dashboard.jsp
│           │   ├── admin_users.jsp
│           │   ├── admin_playlists.jsp
│           │   └── error.jsp
│           ├── css/
│           │   └── style.css         # Custom Stylesheet
│           └── index.jsp             # Welcome File
├── db/
│   └── schema.sql                    # Database Schema Script
├── lib/                              # External Libraries
│   ├── servlet-api.jar
│   ├── jsp-api.jar
│   ├── postgresql-42.7.3.jar
│   ├── taglibs-standard-impl-1.2.5-migrated-0.0.1.jar
│   └── taglibs-standard-spec-1.2.5-migrated-0.0.1.jar
└── docs/                             # Documentation
```

This structure ensures a clear separation between source code, web resources, configuration, database scripts, and documentation.

### 6.2 JavaBeans (Model Layer)

The model layer comprises three JavaBean classes that represent the core domain entities of the system.

**User.java** models a registered user of the system. It implements the Serializable interface, enabling User objects to be stored in HTTP sessions. The class contains eight fields mapping directly to columns in the users table: id (int), fullName (String), username (String), email (String), password (String), role (String), active (boolean), and createdAt (Timestamp). A utility method isAdmin() returns true when the role field equals "ADMIN", providing a convenient check for role-based logic in controllers and views. The class includes both a no-argument constructor (required by the JavaBeans specification) and a parameterised constructor for convenient object creation during registration.

**Playlist.java** models a music playlist. In addition to the seven fields mapping to database columns (id, userId, title, description, genre, isPublic, createdAt, updatedAt), the class includes two transient display fields — ownerUsername (String) and songCount (int) — that are populated by DAO queries using SQL JOINs and subqueries but are not stored as separate database columns. The isPublic boolean field requires special handling for Jakarta Expression Language (EL): because EL resolves the property name "isPublic" by searching for a method named getIsPublic() rather than isPublic(), the class provides both the standard isPublic() method (for use in Java code) and a dedicated getIsPublic() method (for use in JSP EL expressions).

**Song.java** models an individual song within a playlist. The class contains seven fields: id (int), title (String), artist (String), album (String), duration (String), playlistId (int), and createdAt (Timestamp). The duration is stored as a formatted string (e.g., "3:45") to preserve the human-readable format used in the user interface.

### 6.3 Data Access Object (DAO) Layer

The DAO layer provides an abstraction between the business logic and the PostgreSQL database, encapsulating all SQL operations within dedicated classes.

**UserDAO.java** provides methods for user authentication, registration, and account management. The register() method inserts a new user record, hashing the password through PasswordUtil before storage. The login() method accepts a username and plaintext password, hashes the password, and queries the database for a matching record. Additional methods include findById(), findByUsername(), usernameExists(), emailExists(), findAll(), updateStatus(), delete(), and countAll(). Each method employs try-with-resources blocks to ensure that database connections, PreparedStatements, and ResultSets are closed automatically, preventing resource leaks.

**PlaylistDAO.java** handles all playlist-related database operations. The class provides create(), findById(), findByUserId(), findPublic(), findAll(), search(), update(), delete(), countAll(), countByUser(), and findRecentPublic() methods. Query methods employ SQL JOINs with the users table to populate the ownerUsername field and subqueries against the songs table to compute the songCount, eliminating the need for multiple queries or N+1 query patterns. The search() method implements case-insensitive search using PostgreSQL's LOWER() function and LIKE pattern matching with wildcard parameters.

**SongDAO.java** manages song records within playlists. The addSong() method creates a new song record and subsequently updates the parent playlist's updated_at timestamp to reflect the modification. The findByPlaylistId() method returns all songs in a playlist ordered by creation date. Additional methods include findById(), delete(), and countAll().

All DAO methods use PreparedStatement with parameterised queries to prevent SQL injection attacks. Error handling is implemented through try-catch blocks that log error messages to the server console while returning appropriate failure indicators (null for finders, false for mutators) to the calling code.

### 6.4 Servlets (Controller Layer)

The controller layer consists of seven HttpServlet classes, each handling a specific area of functionality.

**HomeServlet.java** (URL: /home) serves as the entry point for unauthenticated visitors, forwarding requests to the landing page that displays information about the system and provides links to the login and registration pages.

**AuthServlet.java** (URLs: /login, /register, /logout) manages the complete authentication lifecycle. The doGet() method dispatches to the appropriate JSP form or performs logout by invalidating the session. The doPost() method processes login and registration form submissions. Login processing includes input validation, credential verification via UserDAO, active status checking, session creation with a 30-minute timeout, and role-based redirection (regular users to /dashboard, administrators to /admin/dashboard). Registration processing performs comprehensive validation including field presence, username format (alphanumeric and underscores, minimum 3 characters), email format (regex validation), password length (minimum 6 characters), password confirmation matching, and uniqueness checks for username and email.

**DashboardServlet.java** (URL: /dashboard) prepares the data for the user's personalised dashboard. The servlet queries the database for the user's playlists, recent public playlists, and aggregate statistics (total songs, public playlist count), then forwards to dashboard.jsp with these data sets as request attributes.

**PlaylistServlet.java** (URLs: /playlists, /playlist) handles all CRUD operations for playlists. The doGet() method dispatches to list (user's playlists), new (blank creation form), edit (pre-populated edit form), view (detailed playlist with songs), or delete operations based on the "action" request parameter. The doPost() method handles create and update operations. Ownership verification is enforced: only the playlist owner or an administrator may edit or delete a playlist. View access is controlled by the playlist's visibility setting — public playlists are viewable by any authenticated user, while private playlists are restricted to their owner and administrators.

**SongServlet.java** (URL: /song) manages song addition and deletion within playlists. Adding a song requires validation of the title, artist, and duration (with regex pattern matching for the M:SS format). Playlist ownership is verified before any modification is permitted.

**ExploreServlet.java** (URL: /explore) implements the public playlist discovery feature. When no search query is provided, all public playlists are retrieved. When a search query is present, the results are filtered by title or genre using case-insensitive pattern matching.

**AdminServlet.java** (URLs: /admin/dashboard, /admin/users, /admin/playlists) provides the administrative interface. The admin dashboard displays aggregate system statistics and recent playlists. The user management page lists all registered users with options to toggle activation status or delete accounts. The playlist management page lists all playlists with an option to delete any playlist. A safeguard prevents administrators from deleting their own account.

### 6.5 JSP (View Layer)

The view layer comprises fourteen JSP pages that render the HTML interface, organised for maximum code reuse through JSP includes.

**Include Files.** Three include files provide shared layout elements. header.jsp outputs the HTML DOCTYPE, head element with meta tags, Bootstrap CSS CDN links, the custom stylesheet link, and the opening body tag. The page title is received as a JSP parameter. navbar.jsp renders the responsive navigation bar, which adapts its menu items based on the user's authentication state and role: unauthenticated visitors see Home, Login, and Register links; authenticated users see Dashboard, My Playlists, and Explore links; administrators additionally see Admin Panel links. footer.jsp closes the HTML body, includes the Bootstrap JavaScript bundle from CDN, and provides a copyright notice.

**Authentication Pages.** login.jsp presents a centred login card with username and password fields, error message display, and links to registration. register.jsp provides a comprehensive registration form with fields for full name, username, email, password, and password confirmation, all with input validation attributes and error feedback.

**User Pages.** dashboard.jsp displays a personalised welcome message, summary statistics cards (total playlists, total songs, public playlists), a table of the user's recent playlists, and a section showing recently shared public playlists from the community. playlists.jsp presents the complete list of the user's playlists in a card grid layout, with each card showing the playlist title, visibility badge, description excerpt, genre, and song count, along with view, edit, and delete action buttons. playlist_form.jsp serves dual purpose as both the create and edit form, dynamically adjusting its title, button labels, and pre-populated values based on the editMode flag. playlist_view.jsp displays a detailed playlist view including all metadata, a list of songs with their details, and forms for adding new songs and managing existing songs. explore.jsp presents publicly shared playlists with a search bar for filtering by title or genre.

**Admin Pages.** admin_dashboard.jsp displays system-wide statistics and recent public playlists. admin_users.jsp presents a table of all registered users with columns for name, username, email, role, status, creation date, and action buttons for toggling status and deleting accounts. admin_playlists.jsp presents a table of all playlists with columns for title, owner, genre, song count, visibility, creation date, and a delete action button.

**Error Page.** error.jsp provides a user-friendly error display for HTTP 404, HTTP 500, and uncaught exceptions, with navigation links back to the dashboard or login page.

### 6.6 Authentication and Session Management

Authentication in the MPSS system is implemented through a combination of the AuthServlet and the AuthFilter, providing a comprehensive security layer.

When a user submits login credentials, the AuthServlet retrieves the corresponding user record from the database by querying with the username and the SHA-256 hash of the provided password. If a matching record is found, the servlet checks whether the account is active. For active accounts, an HttpSession is created and the User object is stored as a session attribute named "currentUser". The session's maximum inactive interval is set to 1800 seconds (30 minutes), after which the session automatically expires.

The AuthFilter, annotated with @WebFilter, intercepts all requests to protected URLs (/dashboard, /playlist, /playlists, /song, /explore, /admin/*). For each intercepted request, the filter checks whether an HttpSession exists and contains a "currentUser" attribute. If the user is not authenticated, the filter redirects to the login page. For requests to administrative URLs (containing "/admin"), the filter additionally verifies that the authenticated user has the ADMIN role, redirecting non-administrators to the regular dashboard.

Logout is handled by invalidating the session and redirecting to the login page. The session invalidation ensures that all session data is cleared and the session ID is retired.

### 6.7 CRUD Implementation

The system implements complete CRUD operations for all three domain entities.

**User CRUD.** Users are created through the registration process (Create), displayed on the admin user management page (Read), have their active status toggled by administrators (Update), and can be deleted by administrators (Delete). User creation involves comprehensive validation including format checks, uniqueness verification, password hashing, and database insertion.

**Playlist CRUD.** Playlists are created through the playlist creation form (Create), displayed on the user's playlist page and the explore page (Read), edited through the playlist edit form (Update), and deleted through action links with ownership verification (Delete). The playlist CRUD lifecycle includes ownership enforcement, visibility control, and cascading effects on contained songs.

**Song CRUD.** Songs are created through the add song form within the playlist view (Create), displayed within the playlist detail view (Read), and deleted through individual remove actions (Delete). Song creation triggers an update to the parent playlist's updated_at timestamp, maintaining accurate modification tracking.

### 6.8 PostgreSQL Database Integration

The integration between the Java application and the PostgreSQL database is managed through the DBConnection utility class and the JDBC API. The DBConnection class encapsulates the connection parameters — JDBC URL (jdbc:postgresql://localhost:5432/mpss_db), username (postgres), and password — and provides a static getConnection() method that returns a new Connection object for each request. The PostgreSQL JDBC driver (postgresql-42.7.3.jar) is loaded via Class.forName() in a static initialiser block, ensuring the driver is registered with the JDBC DriverManager exactly once during the application's lifecycle.

All database operations use JDBC PreparedStatement objects with parameterised queries, which provide two critical benefits: prevention of SQL injection attacks through proper parameter binding, and potential performance improvement through statement pre-compilation. Result sets are mapped to JavaBean objects through private mapResultSetToXxx() methods within each DAO class, providing a consistent and maintainable mapping strategy.

### 6.9 Business Logic Enforcement

The MPSS system enforces several business rules throughout its operation.

**Ownership Verification.** Before any modification to a playlist or its songs, the system verifies that the requesting user is either the playlist's owner or an administrator. This check occurs at the servlet level, preventing unauthorised modifications even if a user crafts a direct HTTP request with another user's playlist ID.

**Visibility Control.** Playlists marked as private are visible only to their owner and to administrators. The explore page queries only for public playlists, and direct access to a private playlist by a non-owner, non-admin user results in a redirect with an error message.

**Admin Self-Protection.** The AdminServlet prevents an administrator from deleting their own account, ensuring that the system always retains at least one administrative account.

**Input Validation.** All user inputs are validated at the servlet level before being passed to DAO methods. Validation includes null and empty string checks, format validation (email regex, duration regex, username character restrictions), length constraints, and business rule validation (password confirmation matching, uniqueness checks).

**Cascading Integrity.** The database schema enforces cascading deletes through foreign key constraints. Deleting a user automatically deletes all their playlists, and deleting a playlist automatically deletes all its songs. This ensures referential integrity is maintained at the database level.

### 6.10 Error Handling

The MPSS system implements error handling at multiple levels.

**Servlet-Level Validation Errors.** When user input fails validation, the servlet sets an "error" request attribute containing a descriptive message and forwards back to the form JSP, which displays the error in a styled Bootstrap alert. Previously entered values are preserved as request attributes to avoid requiring the user to re-enter all data.

**DAO-Level Exception Handling.** All DAO methods catch SQLException and log error details to the server console via System.err. Methods that return objects (find operations) return null on failure, while methods that perform modifications (create, update, delete) return false. This approach presents a clean interface to the servlet layer while preserving diagnostic information in the server logs.

**HTTP Error Pages.** The web.xml deployment descriptor configures custom error pages for HTTP 404 (Not Found), HTTP 500 (Internal Server Error), and unhandled Java exceptions. All three conditions are mapped to error.jsp, which displays a user-friendly message with navigation options.

**Web Application Error Page.** The error.jsp page provides a visually appealing error display that avoids exposing technical details (stack traces, SQL errors) to end users, instead showing a generic message with links to navigate back to the dashboard or login page.

### 6.11 Screenshots

This section provides descriptions of key system interfaces along with placeholders for the corresponding screenshots.

**6.11.1 Login Page**

The login page presents a centred authentication card against the system's branded background. The card contains the MPSS logo with a music note icon, a heading reading "Welcome Back", two form fields for username and password with appropriate input icons, a "Sign In" button styled in the primary colour (#213885), and links to the registration page and home page. When incorrect credentials are provided, a red alert banner appears at the top of the form displaying the error message.

[Insert Screenshot Here – Login page showing the authentication form with username and password fields, MPSS branding, and Sign In button]

**6.11.2 Registration Page**

The registration page displays a comprehensive account creation form within a centred card. The form includes five fields: Full Name, Username, Email Address, Password, and Confirm Password. Each field includes placeholder text and validation attributes. Below the fields, a "Create Account" button is styled in the primary colour. A link to the login page is provided for users who already have an account. When validation errors occur (such as a duplicate username), a red alert banner appears listing all validation failures.

[Insert Screenshot Here – Registration page showing the five-field registration form with MPSS branding and Create Account button]

**6.11.3 User Dashboard**

The user dashboard provides a personalised overview for the logged-in user. The page begins with a welcome message addressing the user by their full name. Below the greeting, three summary statistic cards display the user's total playlists, total songs across all playlists, and the count of public playlists, each with an appropriate icon. A "Quick Actions" section provides buttons for creating a new playlist and exploring public playlists. The lower section of the page displays two areas: "My Recent Playlists" showing the user's latest playlists in card format, and "Recently Shared" showing recent public playlists from other users.

[Insert Screenshot Here – User dashboard showing welcome message, statistics cards, quick action buttons, and recent playlists sections]

**6.11.4 My Playlists Page**

The playlists management page displays all playlists belonging to the current user in a responsive card grid layout. Each playlist card shows the playlist title, a visibility badge (green "Public" or grey "Private"), a truncated description, the genre label, and the song count. Three action buttons appear at the bottom of each card: "View" (to see the full playlist with songs), "Edit" (to modify the playlist details), and "Delete" (to remove the playlist). When no playlists exist, an empty state illustration is shown with a prompt to create the first playlist.

[Insert Screenshot Here – My Playlists page showing a grid of playlist cards with titles, visibility badges, genres, and action buttons]

**6.11.5 Create/Edit Playlist Form**

The playlist form page presents a clean form for creating or editing a playlist. The form contains four fields: Playlist Title (text input, required), Description (textarea, optional), Genre (dropdown select with options including Pop, Rock, Jazz, Classical, Hip-Hop, R&B, Electronic, Country, Reggae, Blues, Metal, Folk, Latin, Afrobeats, Bongo Flava, and Mixed), and Visibility (radio buttons for Public and Private, with icons). Create and Cancel buttons appear at the bottom. When editing, the form is pre-populated with the existing playlist's values and the heading changes to "Edit Playlist".

[Insert Screenshot Here – Create Playlist form showing title, description, genre dropdown, visibility radio buttons, and submit button]

**6.11.6 Playlist Detail View**

The playlist detail view displays comprehensive information about a single playlist. The header section shows the playlist title, owner username, genre badge, and visibility status. The description text appears below the header. The song list is presented in a table format with columns for number, title, artist, album, duration, and a delete action. Below the song list, an "Add Song" form allows the owner to add new songs with fields for title, artist, album, and duration. If the viewing user is not the owner, the add and delete controls are hidden.

[Insert Screenshot Here – Playlist detail view showing playlist metadata, song list table, and Add Song form]

**6.11.7 Explore Page**

The explore page features a search bar at the top, allowing users to filter playlists by title or genre. Below the search bar, public playlists are displayed in card format, each showing the playlist title, owner's username, genre, song count, and a brief description. A "View" button on each card links to the full playlist detail page. When a search query is active, the results update to show only matching playlists.

[Insert Screenshot Here – Explore page showing the search bar and grid of public playlist cards from various users]

**6.11.8 Admin Dashboard**

The admin dashboard displays three large statistic cards at the top: Total Users, Total Playlists, and Total Songs, each with a count and a distinctive colour. Below the statistics, a table lists the most recent public playlists with columns for title, owner, genre, song count, and creation date. Navigation links in the admin sidebar provide access to User Management and Playlist Management pages.

[Insert Screenshot Here – Admin dashboard showing system statistics cards and recent playlists table]

**6.11.9 Admin User Management**

The user management page presents a comprehensive table of all registered users. Columns include Full Name, Username, Email, Role (with a badge distinguishing ADMIN and USER), Status (active shown in green, inactive in red), Registration Date, and Actions. The actions column provides a toggle button to activate or deactivate the account and a delete button. The current administrator's row shows a "Current" badge instead of action buttons, preventing self-deletion.

[Insert Screenshot Here – Admin user management page showing the users table with role badges, status indicators, and action buttons]

**6.11.10 Database Tables in PostgreSQL**

The PostgreSQL database view shows the three tables — users, playlists, and songs — with their structure and sample data. The users table displays five records including the admin and four regular users with their hashed passwords. The playlists table shows seven sample playlists spanning various genres. The songs table contains twenty-five song records distributed across the playlists.

[Insert Screenshot Here – PostgreSQL client showing the three database tables (users, playlists, songs) with sample data and table structures]

**6.11.11 Successful CRUD Operation**

After successfully creating a new playlist, the user is redirected to the My Playlists page where a green success alert banner appears at the top of the page reading "Playlist created successfully!" The newly created playlist appears in the grid alongside existing playlists, confirming that the database insertion was executed correctly.

[Insert Screenshot Here – My Playlists page showing the green success message after creating a new playlist, with the new playlist visible in the grid]

**6.11.12 Validation Error Example**

When a user attempts to register with an already-taken username or submits a form with missing required fields, a red error alert banner appears at the top of the form. The banner displays all validation failure messages in a list format. The form fields retain the previously entered values, allowing the user to correct only the problematic fields without re-entering all data.

[Insert Screenshot Here – Registration or playlist form showing a red validation error alert with multiple error messages and preserved form values]

---

## 7. Testing and Results

### 7.1 Testing Approach

Testing of the Music Playlist Sharing System was conducted through a systematic process of functional testing, validation testing, authentication testing, database testing, and integration testing. Each test case was designed to verify a specific requirement or behaviour of the system. Tests were executed manually through a web browser and through automated HTTP requests using the curl command-line tool to verify response codes and content.

### 7.2 Test Cases

The following table presents the comprehensive test cases executed for the MPSS system, along with their expected and actual results.

| Test Case ID | Description | Expected Result | Actual Result | Status |
|:---:|---|---|---|:---:|
| TC-01 | Access home page (/) without authentication | Landing page displayed with login/register links | Landing page displayed correctly | PASS |
| TC-02 | Access /login page | Login form displayed with username and password fields | Login form displayed correctly | PASS |
| TC-03 | Access /register page | Registration form displayed with all required fields | Registration form displayed correctly | PASS |
| TC-04 | Login with valid admin credentials (admin/admin123) | Redirect to /admin/dashboard | Redirected to admin dashboard (HTTP 302) | PASS |
| TC-05 | Login with valid user credentials (alice/user123) | Redirect to /dashboard | Redirected to user dashboard (HTTP 302) | PASS |
| TC-06 | Login with invalid credentials | Error message "Invalid username or password" displayed | Error message displayed on login form | PASS |
| TC-07 | Login with empty username field | Error message "Username and password are required" displayed | Validation error displayed | PASS |
| TC-08 | Login with deactivated account | Error message "Your account has been deactivated" displayed | Deactivation message displayed correctly | PASS |
| TC-09 | Register with valid details | Redirect to login page with success message | Registration succeeded, redirected to login | PASS |
| TC-10 | Register with existing username | Error message "Username already taken" displayed | Duplicate username error displayed | PASS |
| TC-11 | Register with existing email | Error message "Email already registered" displayed | Duplicate email error displayed | PASS |
| TC-12 | Register with password less than 6 characters | Error message about password length | Password length error displayed | PASS |
| TC-13 | Register with non-matching passwords | Error message "Passwords do not match" | Password mismatch error displayed | PASS |
| TC-14 | Register with invalid email format | Error message about invalid email | Email format error displayed | PASS |
| TC-15 | Access /dashboard without authentication | Redirect to /login page | Redirected to login (AuthFilter) | PASS |
| TC-16 | Access /admin/dashboard as regular user | Redirect to /dashboard | Redirected to user dashboard (AuthFilter) | PASS |
| TC-17 | View user dashboard after login | Dashboard with statistics and playlists displayed | Dashboard displayed with correct data | PASS |
| TC-18 | View My Playlists page | List of user's playlists displayed | Playlists displayed in card grid | PASS |
| TC-19 | Create new playlist with valid data | Playlist created, redirect to playlists with success message | Playlist created, success alert shown (HTTP 302) | PASS |
| TC-20 | Create playlist with empty title | Validation error "Playlist title is required" | Error message displayed, form preserved | PASS |
| TC-21 | Create playlist with empty genre | Validation error "Genre is required" | Error message displayed | PASS |
| TC-22 | Edit existing playlist | Edit form pre-populated with current values | Form displayed with existing values | PASS |
| TC-23 | Update playlist with valid changes | Playlist updated, redirect to playlist view | Playlist updated successfully | PASS |
| TC-24 | Delete own playlist | Playlist removed, redirect to playlists with success message | Playlist deleted, success message shown | PASS |
| TC-25 | View playlist details | Playlist metadata and song list displayed | Full playlist view rendered correctly | PASS |
| TC-26 | Add song to playlist with valid data | Song added, playlist view refreshed with new song | Song added, page refreshed with new entry | PASS |
| TC-27 | Add song with missing title | Validation error displayed | Validation error message shown | PASS |
| TC-28 | Add song with invalid duration format | Validation error about duration format | Format error displayed | PASS |
| TC-29 | Delete song from playlist | Song removed, playlist view refreshed | Song deleted from playlist | PASS |
| TC-30 | Access Explore page | All public playlists displayed | Public playlists displayed in card grid | PASS |
| TC-31 | Search playlists on Explore page | Filtered results matching search query | Matching playlists displayed | PASS |
| TC-32 | Attempt to edit another user's playlist | Redirect to own playlists page | Redirected, no unauthorised edit | PASS |
| TC-33 | Attempt to delete another user's playlist | Redirect to own playlists page | Redirected, playlist not deleted | PASS |
| TC-34 | View private playlist as non-owner | Access denied, redirect with error | Redirected, private playlist not shown | PASS |
| TC-35 | Admin view all users | Users table with all accounts displayed | All users displayed with correct details | PASS |
| TC-36 | Admin toggle user active status | User status toggled (active to inactive or vice versa) | Status toggled correctly | PASS |
| TC-37 | Admin delete user account | User and cascaded data removed | User deleted, playlists and songs cascaded | PASS |
| TC-38 | Admin attempt to delete own account | Self-deletion prevented, redirect | Admin account preserved | PASS |
| TC-39 | Admin view all playlists | All playlists displayed regardless of visibility | All playlists listed | PASS |
| TC-40 | Admin delete any playlist | Playlist removed regardless of ownership | Playlist deleted | PASS |
| TC-41 | Logout | Session invalidated, redirect to login | Logged out, redirected to login page | PASS |
| TC-42 | Session timeout after 30 minutes inactivity | Session expired, redirect to login on next request | Session expired correctly | PASS |
| TC-43 | Access non-existent page (HTTP 404) | Custom error page displayed | Styled error page shown | PASS |
| TC-44 | Create public playlist and verify on Explore | Playlist appears on Explore for other users | Public playlist visible to all authenticated users | PASS |
| TC-45 | Create private playlist and verify on Explore | Playlist does not appear on Explore page | Private playlist hidden from Explore | PASS |

### 7.3 Validation Testing

Validation testing verified that all user input fields enforce their specified constraints correctly. The registration form was tested with various invalid inputs including empty fields, usernames shorter than three characters, usernames containing special characters, improperly formatted email addresses, passwords shorter than six characters, and non-matching password confirmation. In all cases, the system correctly identified the validation failure, displayed an appropriate error message, and preserved the user's previously entered valid values. The playlist creation form was tested with empty required fields (title and genre), and the song addition form was tested with empty required fields and improperly formatted duration values (e.g., "abc" instead of "3:45"). All validation rules were enforced correctly at the server side.

[Insert Screenshot Here – Validation error on the registration form showing multiple error messages for invalid inputs]

### 7.4 Authentication Testing

Authentication testing confirmed that the system's security mechanisms operate correctly across all scenarios. Successful login with both user and admin credentials was verified, with correct role-based redirection occurring in each case. Invalid credential combinations were tested, including correct username with wrong password, non-existent username, and empty credentials, all producing the expected error messages. The AuthFilter was verified by directly accessing protected URLs (/dashboard, /playlists, /playlist, /song, /explore, /admin/dashboard, /admin/users, /admin/playlists) without authentication, confirming that all resulted in a redirect to the login page. Admin-only URL access by a regular user was confirmed to redirect to the user dashboard rather than allowing access to admin pages. Session expiration was verified by waiting beyond the 30-minute timeout and confirming that subsequent requests redirected to the login page.

[Insert Screenshot Here – Login page showing the "Invalid username or password" error message after an unsuccessful login attempt]

### 7.5 Database Testing

Database testing verified the integrity and correctness of all database operations. Insert operations were tested by creating new users, playlists, and songs, then querying the database directly through the PostgreSQL command-line client to confirm the records existed with correct values. Update operations were tested by modifying playlist details and user status, with database verification confirming the changes. Delete operations were tested for all three entities, with particular attention to cascading deletes: deleting a user was confirmed to cascade to their playlists and the songs within those playlists, and deleting a playlist was confirmed to cascade to its songs. The SHA-256 password hashing was verified by comparing the stored hash values in the database against independently computed hashes of the original plaintext passwords. Foreign key constraints were verified by attempting to insert a playlist with a non-existent user_id and a song with a non-existent playlist_id, confirming that the database rejected both operations with appropriate constraint violation errors.

[Insert Screenshot Here – PostgreSQL terminal showing query results confirming successful data insertion and cascading delete verification]

### 7.6 System Performance Notes

The system demonstrated responsive performance throughout testing. Page load times were consistently under one second for all operations during local testing against a development database with sample data. Database queries were optimised through the use of indexes on frequently queried columns (playlists.user_id, playlists.is_public, playlists.genre, songs.playlist_id), reducing query execution times for filtered and joined queries. The use of PreparedStatements contributes to potential performance benefits through statement caching at the database driver level. Resource management through try-with-resources blocks ensures that database connections are released promptly, preventing connection pool exhaustion under concurrent access.

---

## 8. Conclusion

### 8.1 Summary of Achievements

The Music Playlist Sharing System has been successfully designed, implemented, tested, and deployed as a fully functional web-based application. The project achieves its stated objectives of demonstrating mastery of enterprise Java web development technologies, the MVC architectural pattern, relational database design, and software engineering best practices.

The completed system provides a comprehensive platform for music playlist management and sharing, supporting two distinct user roles with appropriate functionality and access controls. The implementation encompasses secure user authentication with SHA-256 password hashing, complete CRUD operations for users, playlists, and songs, role-based access control enforced through servlet filters, responsive web design through Bootstrap 5, and a normalised PostgreSQL database schema.

### 8.2 System Capabilities

The delivered system provides the following capabilities:

The registration and authentication subsystem allows new users to create accounts with comprehensive input validation, authenticate securely through hashed password comparison, maintain sessions with configurable timeouts, and log out cleanly with session invalidation.

The playlist management subsystem enables users to create playlists with titles, descriptions, genres, and visibility settings, edit and delete their own playlists with ownership verification, view detailed playlist information with song listings, and control whether their playlists are publicly discoverable.

The song management subsystem allows users to add songs with titles, artists, albums, and durations to their playlists, remove individual songs from playlists, and view songs in a structured table format within playlist detail views.

The exploration subsystem provides a public playlist discovery page, enables search-based filtering of playlists by title or genre, and displays playlist metadata including owner information and song counts.

The administration subsystem gives administrators access to system-wide statistics, user account management (viewing, activating, deactivating, deleting), and oversight of all playlists on the platform with deletion capabilities.

### 8.3 Lessons Learned

The development of the MPSS system provided several valuable lessons in software engineering practice.

The importance of the MVC architectural pattern became evident throughout the development process. The clear separation between models, views, and controllers enabled focused development of each layer without unintended side effects. When modifications were required — such as adding a new field to a model or changing the layout of a view — the changes were localised to a specific layer, minimising the risk of introducing regressions.

The value of the Data Access Object pattern was demonstrated when adjustments to SQL queries were needed. Because all database interactions were encapsulated within DAO classes, query modifications did not require changes to any servlet or JSP code, confirming the pattern's effectiveness in isolating data access concerns.

Session management and security require careful attention in web applications. The implementation of the AuthFilter as a centralised security enforcement point proved far more maintainable than embedding authentication checks within each servlet. This approach ensures that new servlets automatically benefit from authentication enforcement simply by adding their URL patterns to the filter's configuration.

The Jakarta EE namespace migration (from javax.servlet to jakarta.servlet) required for Tomcat 10.1 provided practical experience with the kind of API evolution that occurs in real-world enterprise environments, reinforcing the importance of understanding deployment platform requirements.

---

## 9. Future Enhancements

While the current implementation of the Music Playlist Sharing System fulfils all stated requirements, several enhancements could extend the system's capabilities and value in future iterations.

### 9.1 REST API Integration

The current system renders all responses as HTML, limiting its usability to browser-based interactions. Developing a RESTful API layer would expose the system's functionality as JSON-based web services, enabling integration with mobile applications, third-party services, and front-end JavaScript frameworks. The API could follow the OpenAPI specification, providing standardised documentation and facilitating client code generation. Endpoints would include user authentication (with token-based security such as JWT), playlist CRUD operations, song management, and search functionality.

### 9.2 Mobile Application Version

A native or cross-platform mobile application (using technologies such as Flutter, React Native, or native Android/iOS development) would significantly expand the system's accessibility. The mobile application would consume the REST API described above, providing a native user experience optimised for mobile interactions including gesture-based navigation, push notifications for playlist updates, and offline playlist caching.

### 9.3 Music Streaming Integration

Integration with third-party music streaming APIs, such as the Spotify Web API or the YouTube Data API, would enable the system to provide playback capabilities directly within the platform. Users could search for songs using the streaming service's catalogue, add songs by reference rather than manual entry, and play songs directly within the playlist view. This enhancement would transform MPSS from a metadata management tool into a functional music listening platform.

### 9.4 Advanced Analytics and Recommendations

Implementing analytics features would provide users and administrators with insights into listening patterns and platform usage. User-facing analytics could include listening history, favourite genres, and playlist engagement statistics. A recommendation engine, potentially leveraging collaborative filtering algorithms, could suggest playlists to users based on their listening preferences and the preferences of similar users.

### 9.5 Cloud Deployment

Deploying the MPSS system to a cloud platform such as Amazon Web Services (AWS), Google Cloud Platform (GCP), or Heroku would provide scalability, high availability, and global accessibility. The deployment could utilise containerisation through Docker, with database hosting on a managed PostgreSQL service such as Amazon RDS or Google Cloud SQL. A CI/CD pipeline using tools such as GitHub Actions or Jenkins would automate the build, test, and deployment process.

### 9.6 Additional Feature Enhancements

Further enhancements could include collaborative playlists allowing multiple users to contribute songs, playlist import and export in standard formats such as M3U or XSPF, social features including playlist commenting and rating, user profile pages with avatar uploads, playlist cover images, a playlist duplication or fork feature, real-time notifications using WebSocket technology, and an email verification step during registration to improve account security.

---

## 10. References

Apache Software Foundation (2024) *Apache Tomcat 10 Documentation.* Available at: https://tomcat.apache.org/tomcat-10.1-doc/index.html (Accessed: February 2026).

Apple Developer Documentation (2024) *MusicKit and Apple Music API.* Available at: https://developer.apple.com/musickit/ (Accessed: February 2026).

Bauer, C. and King, G. (2015) *Java Persistence with Hibernate.* 2nd edn. Manning Publications.

Fowler, M. (2002) *Patterns of Enterprise Application Architecture.* Addison-Wesley Professional.

Gamma, E., Helm, R., Johnson, R. and Vlissides, J. (1994) *Design Patterns: Elements of Reusable Object-Oriented Software.* Addison-Wesley Professional.

Google Developers (2024) *YouTube Data API Reference.* Available at: https://developers.google.com/youtube/v3 (Accessed: February 2026).

Jakarta EE (2024) *Jakarta Servlet Specification 6.0.* Available at: https://jakarta.ee/specifications/servlet/6.0/ (Accessed: February 2026).

Jakarta EE (2024) *Jakarta Server Pages Specification 3.1.* Available at: https://jakarta.ee/specifications/pages/3.1/ (Accessed: February 2026).

Juneau, J. (2022) *Jakarta EE Recipes: A Problem-Solution Approach.* 2nd edn. Apress.

Murach, J. and Urban, M. (2020) *Murach's Java Servlets and JSP.* 3rd edn. Mike Murach & Associates.

Oracle Corporation (2023) *JDBC — Java Database Connectivity.* Available at: https://docs.oracle.com/javase/tutorial/jdbc/ (Accessed: February 2026).

Oracle Corporation (2023) *The Java Tutorials — Servlets.* Available at: https://docs.oracle.com/javaee/7/tutorial/servlets.htm (Accessed: February 2026).

PostgreSQL Global Development Group (2024) *PostgreSQL 14 Documentation.* Available at: https://www.postgresql.org/docs/14/ (Accessed: February 2026).

Reenskaug, T. (1979) *Models — Views — Controllers.* Technical Note, Xerox PARC.

Spotify Engineering (2023) *Spotify Engineering Blog.* Available at: https://engineering.atspotify.com/ (Accessed: February 2026).

Sun Microsystems (2002) *Core J2EE Patterns: Data Access Object.* Available at: https://www.oracle.com/java/technologies/dataaccessobject.html (Accessed: February 2026).

---

## 11. Appendices

### Appendix A: Source Code

This appendix provides placeholders for screenshots of the key source code files comprising the Music Playlist Sharing System.

**A.1 Model Layer Source Code**

The following screenshot shows the User.java JavaBean class, which implements the Serializable interface and maps to the users database table. The class demonstrates the standard JavaBeans convention with private fields, a no-argument constructor, public getter and setter methods, and the isAdmin() utility method for role checking.

[Insert Screenshot Here – User.java source code showing the complete JavaBean with fields, constructors, getters, and setters]

The following screenshot shows the Playlist.java JavaBean class, including the transient display fields (ownerUsername, songCount) and the dual getter methods for the isPublic field (isPublic() for Java code and getIsPublic() for EL expressions).

[Insert Screenshot Here – Playlist.java source code showing the complete JavaBean with the EL-compatible getter methods]

The following screenshot shows the Song.java JavaBean class, representing a song within a playlist with fields for title, artist, album, duration, and playlist reference.

[Insert Screenshot Here – Song.java source code showing the complete JavaBean]

**A.2 DAO Layer Source Code**

The following screenshot shows the UserDAO.java class, demonstrating the register() method with password hashing, the login() method with parameterised queries, and the mapResultSetToUser() mapping method.

[Insert Screenshot Here – UserDAO.java source code showing key methods including register(), login(), and findAll()]

The following screenshot shows the PlaylistDAO.java class, demonstrating SQL JOINs for populating the ownerUsername field and subqueries for computing the songCount.

[Insert Screenshot Here – PlaylistDAO.java source code showing key methods including create(), findByUserId(), and search()]

**A.3 Controller Layer Source Code**

The following screenshot shows the AuthServlet.java class, demonstrating the login handling logic including credential verification, active status checking, session creation, and role-based redirection.

[Insert Screenshot Here – AuthServlet.java source code showing the handleLogin() and handleRegister() methods]

The following screenshot shows the PlaylistServlet.java class, demonstrating the CRUD action routing through the doGet() and doPost() methods and the ownership verification in handleEditForm() and handleDelete().

[Insert Screenshot Here – PlaylistServlet.java source code showing the action dispatch and CRUD handlers]

**A.4 Filter Source Code**

The following screenshot shows the AuthFilter.java class, demonstrating the @WebFilter annotation with protected URL patterns, session checking, and role-based access control for admin pages.

[Insert Screenshot Here – AuthFilter.java source code showing the doFilter() method with authentication and authorisation logic]

**A.5 Utility Classes Source Code**

The following screenshot shows the DBConnection.java utility class with the PostgreSQL connection parameters and the getConnection() method, alongside the PasswordUtil.java class demonstrating SHA-256 password hashing.

[Insert Screenshot Here – DBConnection.java and PasswordUtil.java source code showing database connection management and password hashing]

### Appendix B: Database Schema

The following screenshot shows the complete PostgreSQL database schema script (schema.sql), including table creation statements with constraints, index definitions, and sample data insertion statements for the users, playlists, and songs tables.

[Insert Screenshot Here – Complete schema.sql file showing CREATE TABLE statements, indexes, and INSERT statements for sample data]

The following screenshot shows the database tables as viewed through the PostgreSQL command-line client, displaying the table structure (columns, types, constraints) for each of the three tables.

[Insert Screenshot Here – PostgreSQL \d command output showing the structure of users, playlists, and songs tables]

### Appendix C: Deployment Configuration

The following screenshot shows the web.xml deployment descriptor, configured for Jakarta EE 6.0 with the welcome file, session timeout configuration, and custom error page mappings.

[Insert Screenshot Here – web.xml deployment descriptor showing Jakarta EE namespace, session configuration, and error page mappings]

The following screenshot shows the Apache Tomcat 10.1 deployment directory structure under webapps/mpss, displaying the WEB-INF directory with compiled classes, library JARs, and web.xml, alongside the JSP pages and static resources.

[Insert Screenshot Here – Tomcat webapps/mpss directory listing showing the deployment structure with WEB-INF/classes, WEB-INF/lib, jsp/, and css/ directories]

The following screenshot shows the project's library dependencies in the WEB-INF/lib directory: the PostgreSQL JDBC driver (postgresql-42.7.3.jar) and the Jakarta-compatible JSTL implementation (taglibs-standard-impl-1.2.5-migrated-0.0.1.jar and taglibs-standard-spec-1.2.5-migrated-0.0.1.jar).

[Insert Screenshot Here – Contents of WEB-INF/lib showing the three JAR files required for database connectivity and JSTL tag processing]

---

*End of Report*
