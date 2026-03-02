# Music Playlist Sharing System — Deployment Guide

---

## Prerequisites

1. **JDK 8+** installed and `JAVA_HOME` configured
2. **Apache Tomcat 9+** installed
3. **PostgreSQL 12+** installed and running
4. **PostgreSQL JDBC Driver** (`postgresql-42.x.x.jar`)
5. **JSTL Library** (`javax.servlet.jstl-1.2.jar` and `javax.servlet.jsp.jstl-api-1.2.1.jar`)

---

## Step 1: Set Up PostgreSQL Database

### 1.1 Create the Database

```bash
# Open PostgreSQL shell
sudo -u postgres psql

# Create database
CREATE DATABASE mpss_db;

# Connect to it
\c mpss_db
```

### 1.2 Run the Schema Script

```bash
# From terminal (outside psql)
sudo -u postgres psql -d mpss_db -f /path/to/project/db/schema.sql
```

Or copy-paste the contents of `db/schema.sql` directly into psql.

### 1.3 Verify Data

```sql
\c mpss_db
SELECT COUNT(*) FROM users;      -- Should return 5
SELECT COUNT(*) FROM playlists;   -- Should return 7
SELECT COUNT(*) FROM songs;       -- Should return 27
```

### 1.4 Configure Database Credentials

Edit the file `src/main/java/com/mpss/util/DBConnection.java` if your PostgreSQL credentials differ:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/mpss_db";
private static final String USER = "postgres";       // Change if needed
private static final String PASSWORD = "postgres";   // Change if needed
```

---

## Step 2: Download Required Libraries

Create a `lib/` folder in the project root and download these JAR files:

### 2.1 PostgreSQL JDBC Driver
```bash
mkdir -p lib
cd lib
wget https://jdbc.postgresql.org/download/postgresql-42.7.1.jar
```

Or download from: https://jdbc.postgresql.org/download/

### 2.2 JSTL Libraries
```bash
# Download JSTL API and implementation
wget https://repo1.maven.org/maven2/javax/servlet/jstl/1.2/jstl-1.2.jar
wget https://repo1.maven.org/maven2/javax/servlet/jsp/jstl/javax.servlet.jsp.jstl-api/1.2.1/javax.servlet.jsp.jstl-api-1.2.1.jar
```

---

## Step 3: Compile the Project

### Option A: Using the Build Script

```bash
cd "/path/to/Music Playlist Sharing System"
export TOMCAT_HOME=/opt/tomcat    # Adjust to your Tomcat path
./build.sh
```

### Option B: Manual Compilation

```bash
# Set variables
PROJECT="/path/to/Music Playlist Sharing System"
TOMCAT_HOME="/opt/tomcat"

# Create build directories
mkdir -p "$PROJECT/build/WEB-INF/classes"
mkdir -p "$PROJECT/build/WEB-INF/lib"
mkdir -p "$PROJECT/dist"

# Compile Java files
find "$PROJECT/src/main/java" -name "*.java" > /tmp/sources.txt
javac -cp "$TOMCAT_HOME/lib/servlet-api.jar:$TOMCAT_HOME/lib/jsp-api.jar:$PROJECT/lib/*" \
      -d "$PROJECT/build/WEB-INF/classes" \
      @/tmp/sources.txt

# Copy web resources
cp -r "$PROJECT/src/main/webapp/"* "$PROJECT/build/"

# Copy library JARs
cp "$PROJECT/lib/"*.jar "$PROJECT/build/WEB-INF/lib/"

# Create WAR
cd "$PROJECT/build"
jar -cf "$PROJECT/dist/MPSS.war" .
```

### Option C: Using Apache Ant

```bash
# Ensure Ant is installed
sudo apt install ant    # or brew install ant

# Build
cd "/path/to/Music Playlist Sharing System"
ant -Dtomcat.home=/opt/tomcat war
```

---

## Step 4: Deploy to Tomcat

### 4.1 Copy WAR to Tomcat

```bash
cp dist/MPSS.war $TOMCAT_HOME/webapps/
```

### 4.2 Also Copy JDBC Driver to Tomcat lib (Recommended)

```bash
cp lib/postgresql-42.7.1.jar $TOMCAT_HOME/lib/
```

### 4.3 Start/Restart Tomcat

```bash
# Start Tomcat
$TOMCAT_HOME/bin/startup.sh

# Or restart if already running
$TOMCAT_HOME/bin/shutdown.sh
$TOMCAT_HOME/bin/startup.sh
```

### 4.4 Verify Deployment

Check Tomcat logs:
```bash
tail -f $TOMCAT_HOME/logs/catalina.out
```

Look for:
```
[DBConnection] PostgreSQL JDBC Driver loaded successfully.
```

---

## Step 5: Access the Application

Open your web browser and navigate to:

```
http://localhost:8080/MPSS/
```

### Default Login Credentials

| Role  | Username | Password  |
|-------|----------|-----------|
| Admin | admin    | admin123  |
| User  | alice    | user123   |
| User  | bob      | user123   |
| User  | carol    | user123   |

---

## Step 6: IDE Setup (Optional)

### Eclipse
1. Import as "Dynamic Web Project"
2. Add Tomcat as Server Runtime
3. Add JAR files from `lib/` to Build Path
4. Set `src/main/java` as source folder
5. Set `src/main/webapp` as web content folder

### IntelliJ IDEA
1. Open as existing project
2. Configure Tomcat as Application Server
3. Add `lib/` JARs to module dependencies
4. Mark `src/main/java` as Sources Root
5. Mark `src/main/webapp` as Web Resource Root
6. Create Artifact as "Web Application: Exploded"

---

## Troubleshooting

### Problem: "Class not found: org.postgresql.Driver"
**Solution:** Ensure `postgresql-42.x.x.jar` is in `TOMCAT_HOME/lib/` or in `WEB-INF/lib/`

### Problem: "Connection refused" to database
**Solution:** 
- Verify PostgreSQL is running: `sudo systemctl status postgresql`
- Check port: `sudo netstat -tlnp | grep 5432`
- Verify credentials in `DBConnection.java`

### Problem: JSP pages show raw code
**Solution:** Ensure JSTL JARs (`jstl-1.2.jar`) are in `WEB-INF/lib/`

### Problem: 404 on all pages
**Solution:** Verify the WAR was extracted correctly in `TOMCAT_HOME/webapps/MPSS/`

### Problem: "Permission denied" on Linux
**Solution:**
```bash
sudo chown -R tomcat:tomcat $TOMCAT_HOME/webapps/MPSS
```

---

## WAR File Structure (After Build)

```
MPSS.war
├── index.jsp
├── css/
│   └── style.css
├── js/
├── images/
├── jsp/
│   ├── includes/
│   │   ├── header.jsp
│   │   ├── navbar.jsp
│   │   └── footer.jsp
│   ├── landing.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── dashboard.jsp
│   ├── playlists.jsp
│   ├── playlist_form.jsp
│   ├── playlist_view.jsp
│   ├── explore.jsp
│   ├── admin_dashboard.jsp
│   ├── admin_users.jsp
│   ├── admin_playlists.jsp
│   └── error.jsp
└── WEB-INF/
    ├── web.xml
    ├── classes/
    │   └── com/mpss/
    │       ├── model/
    │       ├── dao/
    │       ├── controller/
    │       ├── util/
    │       └── filter/
    └── lib/
        ├── postgresql-42.7.1.jar
        └── jstl-1.2.jar
```

---

*Document Version: 1.0*
*Date: February 2026*
