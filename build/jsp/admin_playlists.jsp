<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Manage Playlists"/>
</jsp:include>
<jsp:include page="includes/navbar.jsp"/>

<!-- Page Header -->
<div class="page-header">
    <div class="container">
        <h1><i class="bi bi-collection-play"></i> All Playlists</h1>
        <p>Manage all playlists in the system</p>
    </div>
</div>

<div class="container mb-5">
    <!-- Messages -->
    <c:if test="${param.message == 'deleted'}">
        <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle"></i> Playlist deleted successfully!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <div class="card">
        <div class="card-header-custom">
            <i class="bi bi-collection-play-fill"></i> All Playlists (${playlists.size()})
        </div>
        <div class="card-body p-0">
            <c:choose>
                <c:when test="${empty playlists}">
                    <div class="empty-state">
                        <i class="bi bi-collection-play"></i>
                        <h5>No playlists in the system</h5>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive">
                        <table class="table table-custom mb-0">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Title</th>
                                    <th>Owner</th>
                                    <th>Genre</th>
                                    <th>Songs</th>
                                    <th>Visibility</th>
                                    <th>Created</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="pl" items="${playlists}">
                                    <tr>
                                        <td>${pl.id}</td>
                                        <td><strong>${pl.title}</strong></td>
                                        <td>${pl.ownerUsername}</td>
                                        <td><span class="genre-tag">${pl.genre}</span></td>
                                        <td>${pl.songCount}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${pl.isPublic}">
                                                    <span class="badge-public"><i class="bi bi-globe"></i> Public</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge-private"><i class="bi bi-lock"></i> Private</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td><small class="text-muted">${pl.createdAt}</small></td>
                                        <td>
                                            <div class="d-flex gap-1">
                                                <a href="${pageContext.request.contextPath}/playlist?action=view&id=${pl.id}"
                                                   class="btn btn-sm btn-primary-custom" title="View">
                                                    <i class="bi bi-eye"></i>
                                                </a>
                                                <form action="${pageContext.request.contextPath}/admin/playlists" method="post"
                                                      style="display: inline;"
                                                      onsubmit="return confirm('Delete this playlist and all its songs?');">
                                                    <input type="hidden" name="action" value="deletePlaylist">
                                                    <input type="hidden" name="playlistId" value="${pl.id}">
                                                    <button type="submit" class="btn btn-sm btn-outline-danger" title="Delete">
                                                        <i class="bi bi-trash"></i>
                                                    </button>
                                                </form>
                                            </div>
                                        </td>
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
