<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="${editMode ? 'Edit Playlist' : 'Create Playlist'}"/>
</jsp:include>
<jsp:include page="includes/navbar.jsp"/>

<!-- Page Header -->
<div class="page-header">
    <div class="container">
        <h1>
            <i class="bi bi-${editMode ? 'pencil-square' : 'plus-circle'}"></i>
            ${editMode ? 'Edit Playlist' : 'Create New Playlist'}
        </h1>
        <p>${editMode ? 'Update your playlist details' : 'Fill in the details to create a new playlist'}</p>
    </div>
</div>

<div class="container mb-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card">
                <div class="card-body p-4">
                    <!-- Error Messages -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger-custom alert-dismissible fade show" role="alert">
                            <i class="bi bi-exclamation-triangle"></i> ${error}
                            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                        </div>
                    </c:if>

                    <form action="${pageContext.request.contextPath}/playlist" method="post">
                        <input type="hidden" name="action" value="${editMode ? 'update' : 'create'}">
                        <c:if test="${editMode}">
                            <input type="hidden" name="id" value="${playlist.id}">
                        </c:if>

                        <div class="mb-3">
                            <label for="title" class="form-label">
                                <i class="bi bi-type"></i> Playlist Title *
                            </label>
                            <input type="text" class="form-control form-control-lg" id="title" name="title"
                                   value="${editMode ? playlist.title : title}"
                                   placeholder="e.g., Morning Vibes" required maxlength="150">
                        </div>

                        <div class="mb-3">
                            <label for="description" class="form-label">
                                <i class="bi bi-text-paragraph"></i> Description
                            </label>
                            <textarea class="form-control" id="description" name="description"
                                      rows="3" placeholder="Describe your playlist...">${editMode ? playlist.description : description}</textarea>
                        </div>

                        <div class="mb-3">
                            <label for="genre" class="form-label">
                                <i class="bi bi-tag"></i> Genre *
                            </label>
                            <select class="form-select form-select-lg" id="genre" name="genre" required>
                                <option value="">Select a genre...</option>
                                <c:set var="currentGenre" value="${editMode ? playlist.genre : genre}"/>
                                <option value="Pop" ${currentGenre == 'Pop' ? 'selected' : ''}>Pop</option>
                                <option value="Rock" ${currentGenre == 'Rock' ? 'selected' : ''}>Rock</option>
                                <option value="Jazz" ${currentGenre == 'Jazz' ? 'selected' : ''}>Jazz</option>
                                <option value="Classical" ${currentGenre == 'Classical' ? 'selected' : ''}>Classical</option>
                                <option value="Hip-Hop" ${currentGenre == 'Hip-Hop' ? 'selected' : ''}>Hip-Hop</option>
                                <option value="R&B" ${currentGenre == 'R&B' ? 'selected' : ''}>R&B</option>
                                <option value="Electronic" ${currentGenre == 'Electronic' ? 'selected' : ''}>Electronic</option>
                                <option value="Country" ${currentGenre == 'Country' ? 'selected' : ''}>Country</option>
                                <option value="Reggae" ${currentGenre == 'Reggae' ? 'selected' : ''}>Reggae</option>
                                <option value="Blues" ${currentGenre == 'Blues' ? 'selected' : ''}>Blues</option>
                                <option value="Metal" ${currentGenre == 'Metal' ? 'selected' : ''}>Metal</option>
                                <option value="Folk" ${currentGenre == 'Folk' ? 'selected' : ''}>Folk</option>
                                <option value="Latin" ${currentGenre == 'Latin' ? 'selected' : ''}>Latin</option>
                                <option value="Afrobeats" ${currentGenre == 'Afrobeats' ? 'selected' : ''}>Afrobeats</option>
                                <option value="Bongo Flava" ${currentGenre == 'Bongo Flava' ? 'selected' : ''}>Bongo Flava</option>
                                <option value="Mixed" ${currentGenre == 'Mixed' ? 'selected' : ''}>Mixed</option>
                            </select>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">
                                <i class="bi bi-eye"></i> Visibility *
                            </label>
                            <div class="d-flex gap-3">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="visibility" id="public"
                                           value="public"
                                           ${editMode ? (playlist.isPublic ? 'checked' : '') : (visibility == 'private' ? '' : 'checked')}>
                                    <label class="form-check-label" for="public">
                                        <i class="bi bi-globe"></i> Public — Anyone can see this
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="visibility" id="private"
                                           value="private"
                                           ${editMode ? (!playlist.isPublic ? 'checked' : '') : (visibility == 'private' ? 'checked' : '')}>
                                    <label class="form-check-label" for="private">
                                        <i class="bi bi-lock"></i> Private — Only you can see this
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-primary-custom btn-lg flex-fill">
                                <i class="bi bi-${editMode ? 'check-lg' : 'plus-lg'}"></i>
                                ${editMode ? 'Update Playlist' : 'Create Playlist'}
                            </button>
                            <a href="${pageContext.request.contextPath}/playlists"
                               class="btn btn-outline-secondary btn-lg">
                                Cancel
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
