<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Dashboard"/>
</jsp:include>
<jsp:include page="includes/navbar.jsp"/>

<!-- Page Header -->
<div class="page-header">
    <div class="container">
        <h1><i class="bi bi-speedometer2"></i> Dashboard</h1>
        <p>Welcome back, ${sessionScope.currentUser.fullName}!</p>
    </div>
</div>

<div class="container mb-5">
    <!-- Stats Row -->
    <div class="row g-4 mb-4">
        <div class="col-md-4">
            <div class="stat-card">
                <div class="stat-number">${myPlaylistCount}</div>
                <div class="stat-label">My Playlists</div>
                <div class="stat-icon"><i class="bi bi-collection-play"></i></div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="stat-card purple">
                <div class="stat-number">${mySongCount}</div>
                <div class="stat-label">Total Songs</div>
                <div class="stat-icon"><i class="bi bi-music-note-beamed"></i></div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="stat-card magenta">
                <div class="stat-number">${myPublicCount}</div>
                <div class="stat-label">Public Playlists</div>
                <div class="stat-icon"><i class="bi bi-globe"></i></div>
            </div>
        </div>
    </div>

    <div class="row g-4">
        <!-- My Playlists -->
        <div class="col-lg-7">
            <div class="card">
                <div class="card-header-custom d-flex justify-content-between align-items-center">
                    <span><i class="bi bi-collection-play"></i> My Playlists</span>
                    <a href="${pageContext.request.contextPath}/playlist?action=new" class="btn btn-sm btn-light">
                        <i class="bi bi-plus-lg"></i> New
                    </a>
                </div>
                <div class="card-body p-0">
                    <c:choose>
                        <c:when test="${empty myPlaylists}">
                            <div class="empty-state">
                                <i class="bi bi-music-note-list"></i>
                                <h5>No playlists yet</h5>
                                <p>Create your first playlist to get started!</p>
                                <a href="${pageContext.request.contextPath}/playlist?action=new" class="btn btn-primary-custom">
                                    <i class="bi bi-plus-lg"></i> Create Playlist
                                </a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="list-group list-group-flush">
                                <c:forEach var="pl" items="${myPlaylists}" varStatus="loop">
                                    <c:if test="${loop.index < 5}">
                                        <a href="${pageContext.request.contextPath}/playlist?action=view&id=${pl.id}"
                                           class="list-group-item list-group-item-action d-flex justify-content-between align-items-center px-4 py-3">
                                            <div>
                                                <h6 class="mb-1" style="color: var(--dark-accent);">${pl.title}</h6>
                                                <small class="text-muted">
                                                    <span class="genre-tag">${pl.genre}</span>
                                                    &nbsp;
                                                    <i class="bi bi-music-note"></i> ${pl.songCount} songs
                                                </small>
                                            </div>
                                            <div>
                                                <c:choose>
                                                    <c:when test="${pl.isPublic}">
                                                        <span class="badge-public"><i class="bi bi-globe"></i> Public</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge-private"><i class="bi bi-lock"></i> Private</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </a>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <c:if test="${myPlaylists.size() > 5}">
                                <div class="text-center py-3">
                                    <a href="${pageContext.request.contextPath}/playlists" class="btn btn-outline-custom btn-sm">
                                        View All Playlists <i class="bi bi-arrow-right"></i>
                                    </a>
                                </div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <!-- Recent Public Playlists -->
        <div class="col-lg-5">
            <div class="card">
                <div class="card-header-custom">
                    <i class="bi bi-globe"></i> Recent Public Playlists
                </div>
                <div class="card-body p-0">
                    <c:choose>
                        <c:when test="${empty recentPublic}">
                            <div class="empty-state">
                                <i class="bi bi-compass"></i>
                                <h5>Nothing here yet</h5>
                                <p>Be the first to share a playlist!</p>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="list-group list-group-flush">
                                <c:forEach var="pl" items="${recentPublic}">
                                    <a href="${pageContext.request.contextPath}/playlist?action=view&id=${pl.id}"
                                       class="list-group-item list-group-item-action px-4 py-3">
                                        <div class="d-flex justify-content-between">
                                            <h6 class="mb-1" style="color: var(--dark-accent);">${pl.title}</h6>
                                            <small class="text-muted">${pl.songCount} songs</small>
                                        </div>
                                        <small class="text-muted">
                                            <i class="bi bi-person"></i> ${pl.ownerUsername}
                                            &nbsp;&bull;&nbsp;
                                            <span class="genre-tag">${pl.genre}</span>
                                        </small>
                                    </a>
                                </c:forEach>
                            </div>
                            <div class="text-center py-3">
                                <a href="${pageContext.request.contextPath}/explore" class="btn btn-outline-custom btn-sm">
                                    Explore All <i class="bi bi-arrow-right"></i>
                                </a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
