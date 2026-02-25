<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Manage Users"/>
</jsp:include>
<jsp:include page="includes/navbar.jsp"/>

<!-- Page Header -->
<div class="page-header">
    <div class="container">
        <h1><i class="bi bi-people"></i> User Management</h1>
        <p>Manage all system users</p>
    </div>
</div>

<div class="container mb-5">
    <!-- Messages -->
    <c:if test="${param.message == 'updated'}">
        <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle"></i> User status updated successfully!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>
    <c:if test="${param.message == 'deleted'}">
        <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
            <i class="bi bi-check-circle"></i> User deleted successfully!
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
    </c:if>

    <div class="card">
        <div class="card-header-custom d-flex justify-content-between align-items-center">
            <span><i class="bi bi-people-fill"></i> All Users (${users.size()})</span>
        </div>
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-custom mb-0">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Full Name</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Joined</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.id}</td>
                                <td><strong>${user.fullName}</strong></td>
                                <td>${user.username}</td>
                                <td>${user.email}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.admin}">
                                            <span class="badge-admin"><i class="bi bi-shield-check"></i> Admin</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge-user"><i class="bi bi-person"></i> User</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.active}">
                                            <span class="badge-active"><i class="bi bi-check-circle"></i> Active</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge-inactive"><i class="bi bi-x-circle"></i> Inactive</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><small class="text-muted">${user.createdAt}</small></td>
                                <td>
                                    <c:if test="${user.id != sessionScope.currentUser.id}">
                                        <div class="d-flex gap-1">
                                            <!-- Toggle Status -->
                                            <form action="${pageContext.request.contextPath}/admin/users" method="post"
                                                  style="display: inline;">
                                                <input type="hidden" name="action" value="toggleUser">
                                                <input type="hidden" name="userId" value="${user.id}">
                                                <input type="hidden" name="currentStatus" value="${user.active}">
                                                <button type="submit" class="btn btn-sm ${user.active ? 'btn-outline-warning' : 'btn-outline-success'}"
                                                        title="${user.active ? 'Deactivate' : 'Activate'}">
                                                    <i class="bi bi-${user.active ? 'pause-circle' : 'play-circle'}"></i>
                                                </button>
                                            </form>
                                            <!-- Delete User -->
                                            <form action="${pageContext.request.contextPath}/admin/users" method="post"
                                                  style="display: inline;"
                                                  onsubmit="return confirm('Are you sure you want to delete this user? All their playlists will also be deleted.');">
                                                <input type="hidden" name="action" value="deleteUser">
                                                <input type="hidden" name="userId" value="${user.id}">
                                                <button type="submit" class="btn btn-sm btn-outline-danger"
                                                        title="Delete User">
                                                    <i class="bi bi-trash"></i>
                                                </button>
                                            </form>
                                        </div>
                                    </c:if>
                                    <c:if test="${user.id == sessionScope.currentUser.id}">
                                        <span class="text-muted small">(You)</span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="includes/footer.jsp"/>
