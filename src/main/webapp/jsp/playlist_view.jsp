<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="${playlist.title}"/>
</jsp:include>
<jsp:include page="includes/navbar.jsp"/>

<!-- Page Header -->
<div class="page-header">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center">
            <div>
                <h1><i class="bi bi-music-player"></i> ${playlist.title}</h1>
                <p>
                    <i class="bi bi-person"></i> ${playlist.ownerUsername}
                    &nbsp;&bull;&nbsp;
                    <span class="genre-tag" style="background: rgba(255,255,255,0.2); color: white;">${playlist.genre}</span>
                    &nbsp;&bull;&nbsp;
                    <c:choose>
                        <c:when test="${playlist.isPublic}">
                            <i class="bi bi-globe"></i> Public
                        </c:when>
                        <c:otherwise>
                            <i class="bi bi-lock"></i> Private
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
            <c:if test="${isOwner}">
                <div class="d-flex gap-2">
                    <a href="${pageContext.request.contextPath}/playlist?action=edit&id=${playlist.id}"
                       class="btn btn-light">
                        <i class="bi bi-pencil"></i> Edit
                    </a>
                    <a href="${pageContext.request.contextPath}/playlist?action=delete&id=${playlist.id}"
                       class="btn btn-outline-light"
                       onclick="return confirm('Delete this playlist and all its songs?');">
                        <i class="bi bi-trash"></i> Delete
                    </a>
                </div>
            </c:if>
        </div>
    </div>
</div>

<div class="container mb-5">
    <!-- Messages -->
    <c:if test="${param.message == 'updated'}">
        <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle"></i> Playlist updated successfully!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.message == 'songadded'}">
        <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle"></i> Song added successfully!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.message == 'songdeleted'}">
        <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle"></i> Song removed successfully!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${not empty param.error}">
        <div class="alert alert-danger-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-exclamation-triangle"></i> ${param.error}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <!-- Playlist Description -->
    <c:if test="${not empty playlist.description}">
        <div class="card mb-4">
            <div class="card-body">
                <p class="mb-0"><i class="bi bi-info-circle"></i> ${playlist.description}</p>
            </div>
        </div>
    </c:if>

    <div class="row g-4">
        <!-- Songs List -->
        <div class="col-lg-${isOwner ? '8' : '12'}">
            <div class="card">
                <div class="card-header-custom d-flex justify-content-between align-items-center">
                    <span><i class="bi bi-music-note-list"></i> Songs (${songs.size()})</span>
                </div>
                <div class="card-body p-0">
                    <c:choose>
                        <c:when test="${empty songs}">
                            <div class="empty-state">
                                <i class="bi bi-music-note-beamed"></i>
                                <h5>No songs yet</h5>
                                <c:if test="${isOwner}">
                                    <p>Add songs to your playlist using the form.</p>
                                </c:if>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="table-responsive">
                                <table class="table table-custom mb-0">
                                    <thead>
                                        <tr>
                                            <th style="width: 50px;">#</th>
                                            <th>Title</th>
                                            <th>Artist</th>
                                            <th>Album</th>
                                            <th>Duration</th>
                                            <c:if test="${isOwner}">
                                                <th style="width: 80px;">Action</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="song" items="${songs}" varStatus="loop">
                                            <tr class="song-row">
                                                <td>
                                                    <div class="song-number">${loop.index + 1}</div>
                                                </td>
                                                <td>
                                                    <strong style="color: var(--dark-accent);">${song.title}</strong>
                                                </td>
                                                <td>${song.artist}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${not empty song.album}">${song.album}</c:when>
                                                        <c:otherwise><span class="text-muted">—</span></c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <i class="bi bi-clock"></i> ${song.duration}
                                                </td>
                                                <c:if test="${isOwner}">
                                                    <td>
                                                        <a href="${pageContext.request.contextPath}/song?action=delete&id=${song.id}&playlistId=${playlist.id}"
                                                           class="btn btn-sm btn-outline-danger"
                                                           onclick="return confirm('Remove this song?');">
                                                            <i class="bi bi-trash"></i>
                                                        </a>
                                                    </td>
                                                </c:if>
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

        <!-- Add Song Form (Owner only) -->
        <c:if test="${isOwner}">
            <div class="col-lg-4">
                <div class="card" style="position: sticky; top: 1rem;">
                    <div class="card-header-custom">
                        <i class="bi bi-plus-circle"></i> Add Song
                    </div>
                    <div class="card-body">
                        <form action="${pageContext.request.contextPath}/song" method="post">
                            <input type="hidden" name="action" value="add">
                            <input type="hidden" name="playlistId" value="${playlist.id}">

                            <div class="mb-3">
                                <label for="songTitle" class="form-label">Song Title *</label>
                                <input type="text" class="form-control" id="songTitle" name="title"
                                       placeholder="Song name" required>
                            </div>
                            <div class="mb-3">
                                <label for="artist" class="form-label">Artist *</label>
                                <input type="text" class="form-control" id="artist" name="artist"
                                       placeholder="Artist name" required>
                            </div>
                            <div class="mb-3">
                                <label for="album" class="form-label">Album</label>
                                <input type="text" class="form-control" id="album" name="album"
                                       placeholder="Album name (optional)">
                            </div>
                            <div class="mb-3">
                                <label for="duration" class="form-label">Duration *</label>
                                <input type="text" class="form-control" id="duration" name="duration"
                                       placeholder="e.g., 3:45" required pattern="^\d{1,3}:\d{2}$">
                                <div class="form-text">Format: M:SS (e.g., 3:45)</div>
                            </div>
                            <div class="d-grid">
                                <button type="submit" class="btn btn-magenta">
                                    <i class="bi bi-plus-lg"></i> Add Song
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <!-- Back Button -->
    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/playlists" class="btn btn-outline-custom">
            <i class="bi bi-arrow-left"></i> Back to My Playlists
        </a>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
