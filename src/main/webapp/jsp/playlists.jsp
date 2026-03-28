<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="My Playlists"/>
</jsp:include>
<jsp:include page="includes/navbar.jsp"/>

<!-- Page Header -->
<div class="page-header">
    <div class="container d-flex justify-content-between align-items-center">
        <div>
            <h1><i class="bi bi-collection-play"></i> My Playlists</h1>
            <p>Manage your music playlists</p>
        </div>
        <a href="${pageContext.request.contextPath}/playlist?action=new" class="btn btn-light btn-lg">
            <i class="bi bi-plus-lg"></i> Create Playlist
        </a>
    </div>
</div>

<div class="container mb-5">
    <!-- Messages -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-exclamation-triangle"></i> ${error}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.message == 'created'}">
        <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle"></i> Playlist created successfully!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.message == 'deleted'}">
        <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle"></i> Playlist deleted successfully!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${empty playlists}">
            <div class="empty-state" style="padding: 5rem 1rem;">
                <i class="bi bi-music-note-list" style="font-size: 4rem;"></i>
                <h4>You haven't created any playlists yet</h4>
                <p class="text-muted">Start by creating your first playlist!</p>
                <a href="${pageContext.request.contextPath}/playlist?action=new" class="btn btn-primary-custom btn-lg">
                    <i class="bi bi-plus-lg"></i> Create Your First Playlist
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row g-3">
                <c:forEach var="pl" items="${playlists}">
                    <div class="col-md-6 col-lg-4">
                        <div class="playlist-card h-100">
                            <div class="d-flex justify-content-between align-items-start mb-2">
                                <h5 class="mb-0" style="color: var(--dark-accent);">${pl.title}</h5>
                                <c:choose>
                                    <c:when test="${pl.isPublic}">
                                        <span class="badge-public"><i class="bi bi-globe"></i> Public</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge-private"><i class="bi bi-lock"></i> Private</span>
                                    </c:otherwise>
                                </c:choose>
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
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <span class="genre-tag">${pl.genre}</span>
                                <span class="song-count">
                                    <i class="bi bi-music-note"></i> ${pl.songCount} songs
                                </span>
                            </div>
                            <div class="d-flex gap-2">
                                <a href="${pageContext.request.contextPath}/playlist?action=view&id=${pl.id}"
                                   class="btn btn-sm btn-primary-custom flex-fill">
                                    <i class="bi bi-eye"></i> View
                                </a>
                                <a href="${pageContext.request.contextPath}/playlist?action=edit&id=${pl.id}"
                                   class="btn btn-sm btn-outline-custom flex-fill">
                                    <i class="bi bi-pencil"></i> Edit
                                </a>
                                <a href="${pageContext.request.contextPath}/playlist?action=delete&id=${pl.id}"
                                   class="btn btn-sm btn-outline-danger flex-fill"
                                   onclick="return confirm('Are you sure you want to delete this playlist?');">
                                    <i class="bi bi-trash"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="includes/footer.jsp"/>
