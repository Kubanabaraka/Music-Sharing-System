# Music Playlist Sharing System (MPSS)

A complete web-based application for creating, managing, and sharing music playlists. Built with Java Servlets, JSP, JDBC, and PostgreSQL following the MVC architectural pattern.

---

## Features

- **User Registration & Authentication** — Secure sign-up/login with SHA-256 password hashing
- **Playlist Management** — Full CRUD operations with genre categorization
- **Song Management** — Add/remove songs within playlists
- **Public/Private Playlists** — Control visibility of your playlists
- **Explore & Search** — Discover public playlists by title or genre
- **Admin Dashboard** — User management, system statistics, content oversight
- **Responsive UI** — Bootstrap 5 with custom branded color palette
- **Role-Based Access Control** — Regular User and Administrator roles

---

## Tech Stack

| Component    | Technology               |
|--------------|--------------------------|
| Language     | Java 8+                  |
| Web          | Servlets 4.0, JSP 2.3   |
| Database     | PostgreSQL 12+           |
| DB Access    | JDBC                     |
| Frontend     | Bootstrap 5.3, CSS3      |
| Server       | Apache Tomcat 9+         |

---

## Quick Start

### Prerequisites
- JDK 8 or higher
- PostgreSQL 12 or higher
- Apache Tomcat 9 or higher

### 1. Set Up Database
```bash
sudo -u postgres psql
CREATE DATABASE mpss_db;
\c mpss_db
\i db/schema.sql
```

### 2. Download Dependencies
Place these JARs in the `lib/` directory:
- `postgresql-42.7.1.jar` — [Download](https://jdbc.postgresql.org/download/)
- `javax.servlet-api-4.0.1.jar`
- `javax.servlet.jsp-api-2.3.3.jar`
- `jstl-1.2.jar`

### 3. Build & Deploy
```bash
chmod +x build.sh
./build.sh
```
Or compile manually:
```bash
mkdir -p build/WEB-INF/classes
javac -cp "lib/*" -d build/WEB-INF/classes src/main/java/com/mpss/**/*.java
cp -r src/main/webapp/* build/
cp lib/postgresql-42.7.1.jar lib/jstl-1.2.jar build/WEB-INF/lib/
cp build/ $CATALINA_HOME/webapps/mpss -r
```

### 4. Access
- **Application:** http://localhost:8080/mpss/
- **Admin Login:** username: `admin`, password: `admin123`
- **User Login:** username: `alice`, password: `user123`

---

## Project Structure

```
├── docs/                    # Academic documentation
├── db/schema.sql            # PostgreSQL database script
├── src/main/java/com/mpss/
│   ├── model/               # JavaBeans (User, Playlist, Song)
│   ├── dao/                 # Data Access Objects
│   ├── controller/          # Servlet Controllers
│   ├── util/                # DB Connection, Password Hashing
│   └── filter/              # Authentication Filter
├── src/main/webapp/
│   ├── css/style.css        # Custom themed CSS
│   ├── jsp/                 # JSP view pages
│   └── WEB-INF/web.xml     # Deployment descriptor
├── build.xml                # Ant build script
├── build.sh                 # Shell build script
└── README.md
```

---

## Color Palette

| Color            | Hex       | Usage                        |
|------------------|-----------|------------------------------|
| Primary Blue     | `#213885` | Navbar, buttons, headers     |
| Dark Accent      | `#081849` | Gradients, page headers      |
| Secondary Cream  | `#ECDFD2` | Tags, accent backgrounds     |
| Neutral Gray     | `#CCCACC` | Muted text, borders          |
| Highlight Purple | `#5F3475` | Stat cards, gradients        |
| Accent Magenta   | `#893172` | CTAs, badges, highlights     |

---

## Documentation

| Document | Description |
|----------|-------------|
| [Project Proposal](docs/01_Project_Proposal.md) | Problem statement, objectives, scope |
| [System Analysis](docs/02_System_Analysis.md) | Requirements, use cases, user roles |
| [System Design](docs/03_System_Design.md) | Architecture, class & ER diagrams |
| [Testing Documentation](docs/06_Testing_Documentation.md) | 24 test cases with results |
| [Deployment Guide](docs/07_Deployment_Guide.md) | Full setup & deployment instructions |
| [Final Report](docs/08_Final_Report.md) | Complete academic project report |

---

## License

This project is developed for academic purposes.
