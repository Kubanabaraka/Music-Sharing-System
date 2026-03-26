<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Explore Playlists"/>
</jsp:include>
<jsp:include page="includes/navbar.jsp"/>

<!-- Page Header -->
<div class="page-header">
    <div class="container">
        <h1><i class="bi bi-compass"></i> Explore Playlists</h1>
        <p>Discover public playlists shared by the community</p>
    </div>
</div>

<div class="container mb-5">
    <!-- Search Bar -->
    <div class="row justify-content-center mb-4">
        <div class="col-md-8 col-lg-6">
            <form action="${pageContext.request.contextPath}/explore" method="get">
                <div class="input-group input-group-lg">
                    <span class="input-group-text" style="background: white; border-right: none;">
                        <i class="bi bi-search" style="color: var(--primary);"></i>
                    </span>
                    <input type="text" class="form-control" name="search" 
                           value="${searchQuery}" placeholder="Search by title or genre..."
                           style="border-left: none;">
                    <button type="submit" class="btn btn-primary-custom px-4">Search</button>
                </div>
            </form>
            <c:if test="${not empty searchQuery}">
                <div class="mt-2">
                    <span class="text-muted">Showing results for: <strong>"${searchQuery}"</strong></span>
                    <a href="${pageContext.request.contextPath}/explore" class="ms-2 text-decoration-none">
                        <i class="bi bi-x-circle"></i> Clear
                    </a>
                </div>
            </c:if>
        </div>
    </div>

    <!-- Playlists Grid -->
    <c:choose>
        <c:when test="${empty playlists}">
            <div class="empty-state" style="padding: 5rem 1rem;">
                <i class="bi bi-search" style="font-size: 4rem;"></i>
                <h4>No playlists found</h4>
                <p class="text-muted">
                    <c:choose>
                        <c:when test="${not empty searchQuery}">
                            Try a different search term.
                        </c:when>
                        <c:otherwise>
                            No public playlists are available yet.
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row g-3">
                <c:forEach var="pl" items="${playlists}">
                    <div class="col-md-6 col-lg-4">
                        <div class="playlist-card h-100">
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <h5 class="mb-0" style="color: var(--dark-accent);">${pl.title}</h5>
                                <span class="badge-public"><i class="bi bi-globe"></i> Public</span>
                            </div>
                            <p class="text-muted small mb-2">
                                <c:choose>
                                    <c:when test="${not empty pl.description}">
                                        ${pl.description}
                                    </c:when>
                                    <c:otherwise>
                                        <em>No description</em>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <div class="mb-2">
                                <small class="text-muted">
                                    <i class="bi bi-person"></i> ${pl.ownerUsername}
                                </small>
                            </div>
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <span class="genre-tag">${pl.genre}</span>
                                <span class="song-count">
                                    <i class="bi bi-music-note"></i> ${pl.songCount} songs
                                </span>
                            </div>
                            <a href="${pageContext.request.contextPath}/playlist?action=view&id=${pl.id}"
                               class="btn btn-primary-custom btn-sm w-100">
                                <i class="bi bi-eye"></i> View Playlist
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="includes/footer.jsp"/>
