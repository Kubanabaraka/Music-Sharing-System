#!/bin/bash
# ============================================================
# Music Playlist Sharing System (MPSS)
# Manual Build & Deploy Script
# ============================================================
# This script compiles the project and creates a WAR file
# without requiring Maven or Gradle.
# ============================================================

set -e

echo "========================================"
echo "  MPSS Build Script"
echo "========================================"

# ---- Configuration ----
PROJECT_DIR="$(cd "$(dirname "$0")" && pwd)"
SRC_DIR="$PROJECT_DIR/src/main/java"
WEB_DIR="$PROJECT_DIR/src/main/webapp"
BUILD_DIR="$PROJECT_DIR/build"
CLASSES_DIR="$BUILD_DIR/WEB-INF/classes"
LIB_DIR="$PROJECT_DIR/lib"
DIST_DIR="$PROJECT_DIR/dist"
WAR_NAME="MPSS.war"

# Tomcat installation path (adjust as needed)
TOMCAT_HOME="${TOMCAT_HOME:-/opt/tomcat}"
TOMCAT_LIB="$TOMCAT_HOME/lib"

# ---- Step 1: Clean ----
echo "[1/5] Cleaning previous build..."
rm -rf "$BUILD_DIR" "$DIST_DIR"

# ---- Step 2: Create directories ----
echo "[2/5] Creating build directories..."
mkdir -p "$CLASSES_DIR"
mkdir -p "$DIST_DIR"
mkdir -p "$LIB_DIR"
mkdir -p "$BUILD_DIR/WEB-INF/lib"

# ---- Step 3: Build classpath ----
echo "[3/5] Building classpath..."
CLASSPATH=""

# Add Tomcat servlet API
if [ -d "$TOMCAT_LIB" ]; then
    for jar in "$TOMCAT_LIB"/*.jar; do
        CLASSPATH="$CLASSPATH:$jar"
    done
else
    echo "WARNING: Tomcat lib not found at $TOMCAT_LIB"
    echo "Trying to find servlet-api.jar..."
    SERVLET_JAR=$(find / -name "servlet-api.jar" -o -name "javax.servlet-api-*.jar" 2>/dev/null | head -1)
    if [ -n "$SERVLET_JAR" ]; then
        CLASSPATH="$CLASSPATH:$SERVLET_JAR"
        echo "Found: $SERVLET_JAR"
    fi
fi

# Add project libraries
if [ -d "$LIB_DIR" ]; then
    for jar in "$LIB_DIR"/*.jar; do
        [ -f "$jar" ] && CLASSPATH="$CLASSPATH:$jar"
    done
fi

# ---- Step 4: Compile ----
echo "[4/5] Compiling Java sources..."
find "$SRC_DIR" -name "*.java" > /tmp/mpss_sources.txt

javac -encoding UTF-8 \
      -source 8 -target 8 \
      -cp "$CLASSPATH" \
      -d "$CLASSES_DIR" \
      @/tmp/mpss_sources.txt

echo "  Compiled $(wc -l < /tmp/mpss_sources.txt) Java files."

# ---- Step 5: Package WAR ----
echo "[5/5] Packaging WAR file..."

# Copy web resources
cp -r "$WEB_DIR"/* "$BUILD_DIR/"

# Copy libraries to WEB-INF/lib
if ls "$LIB_DIR"/*.jar 1>/dev/null 2>&1; then
    cp "$LIB_DIR"/*.jar "$BUILD_DIR/WEB-INF/lib/"
fi

# Create WAR
cd "$BUILD_DIR"
jar -cf "$DIST_DIR/$WAR_NAME" .
cd "$PROJECT_DIR"

echo "========================================"
echo "  Build Successful!"
echo "  WAR: $DIST_DIR/$WAR_NAME"
echo "========================================"
echo ""
echo "To deploy, copy the WAR to Tomcat webapps:"
echo "  cp $DIST_DIR/$WAR_NAME $TOMCAT_HOME/webapps/"
echo ""
echo "Then access: http://localhost:8080/MPSS/"
