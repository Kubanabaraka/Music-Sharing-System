<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Admin Dashboard"/>
</jsp:include>
<jsp:include page="includes/navbar.jsp"/>

<!-- Page Header -->
<div class="page-header">
    <div class="container">
        <h1><i class="bi bi-shield-check"></i> Admin Dashboard</h1>
        <p>System overview and management</p>
    </div>
</div>

<div class="container mb-5">
    <!-- Stats Row -->
    <div class="row g-4 mb-4">
        <div class="col-md-4">
            <div class="stat-card">
                <div class="stat-number">${totalUsers}</div>
                <div class="stat-label">Total Users</div>
                <div class="stat-icon"><i class="bi bi-people"></i></div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="stat-card purple">
                <div class="stat-number">${totalPlaylists}</div>
                <div class="stat-label">Total Playlists</div>
                <div class="stat-icon"><i class="bi bi-collection-play"></i></div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="stat-card magenta">
                <div class="stat-number">${totalSongs}</div>
                <div class="stat-label">Total Songs</div>
                <div class="stat-icon"><i class="bi bi-music-note-beamed"></i></div>
            </div>
        </div>
    </div>

    <!-- Quick Links -->
    <div class="row g-4 mb-4">
        <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/admin/users" class="text-decoration-none">
                <div class="card p-4 text-center" style="cursor: pointer;">
                    <i class="bi bi-people" style="font-size: 2.5rem; color: var(--primary);"></i>
                    <h5 class="mt-2" style="color: var(--dark-accent);">Manage Users</h5>
                    <p class="text-muted mb-0">View, activate, deactivate, or delete user accounts</p>
                </div>
            </a>
        </div>
        <div class="col-md-6">
            <a href="${pageContext.request.contextPath}/admin/playlists" class="text-decoration-none">
                <div class="card p-4 text-center" style="cursor: pointer;">
                    <i class="bi bi-collection-play" style="font-size: 2.5rem; color: var(--accent-magenta);"></i>
                    <h5 class="mt-2" style="color: var(--dark-accent);">Manage Playlists</h5>
                    <p class="text-muted mb-0">View and manage all playlists in the system</p>
                </div>
            </a>
        </div>
    </div>

    <!-- Recent Playlists -->
    <div class="card">
        <div class="card-header-custom">
            <i class="bi bi-clock-history"></i> Recent Public Playlists
        </div>
        <div class="card-body p-0">
            <c:choose>
                <c:when test="${empty recentPlaylists}">
                    <div class="empty-state">
                        <i class="bi bi-collection-play"></i>
                        <h5>No playlists yet</h5>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive">
                        <table class="table table-custom mb-0">
                            <thead>
                                <tr>
                                    <th>Title</th>
                                    <th>Owner</th>
                                    <th>Genre</th>
                                    <th>Songs</th>
                                    <th>Created</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="pl" items="${recentPlaylists}">
                                    <tr>
                                        <td><strong>${pl.title}</strong></td>
                                        <td>${pl.ownerUsername}</td>
                                        <td><span class="genre-tag">${pl.genre}</span></td>
                                        <td>${pl.songCount}</td>
                                        <td><small class="text-muted">${pl.createdAt}</small></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
