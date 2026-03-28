<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Login"/>
</jsp:include>

<div class="auth-container">
    <div class="auth-card">
        <!-- Brand Icon -->
        <div class="brand-icon">
            <i class="bi bi-music-note-list"></i>
        </div>

        <h2 class="text-center">Welcome Back</h2>
        <p class="subtitle text-center">Sign in to your MPSS account</p>

        <!-- Success Messages -->
        <c:if test="${param.message == 'registered'}">
            <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
                <i class="bi bi-check-circle"></i> Registration successful! Please login.
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>
        <c:if test="${param.message == 'loggedout'}">
            <div class="alert alert-success-custom alert-dismissible fade show" role="alert">
                <i class="bi bi-check-circle"></i> You have been logged out successfully.
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <!-- Error Messages -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger-custom alert-dismissible fade show" role="alert">
                <i class="bi bi-exclamation-triangle"></i> ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <!-- Login Form -->
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="mb-3">
                <label for="username" class="form-label">
                    <i class="bi bi-person"></i> Username
                </label>
                <input type="text" class="form-control form-control-lg" id="username" name="username"
                       value="${username}" placeholder="Enter your username" required autofocus>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">
                    <i class="bi bi-lock"></i> Password
                </label>
                <input type="password" class="form-control form-control-lg" id="password" name="password"
                       placeholder="Enter your password" required>
            </div>
            <div class="d-grid mb-3">
                <button type="submit" class="btn btn-primary-custom btn-lg">
                    <i class="bi bi-box-arrow-in-right"></i> Sign In
                </button>
            </div>
        </form>

        <hr>
        <p class="text-center mb-0">
            Don't have an account?
            <a href="${pageContext.request.contextPath}/register" style="color: var(--accent-magenta); font-weight: 600;">
                Create one here
            </a>
        </p>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
