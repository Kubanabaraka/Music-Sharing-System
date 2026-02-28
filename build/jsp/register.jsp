<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Register"/>
</jsp:include>

<div class="auth-container">
    <div class="auth-card" style="max-width: 500px;">
        <!-- Brand Icon -->
        <div class="brand-icon">
            <i class="bi bi-person-plus"></i>
        </div>

        <h2 class="text-center">Create Account</h2>
        <p class="subtitle text-center">Join MPSS and start sharing your playlists</p>

        <!-- Error Messages -->
        <c:if test="${not empty error}">
            <div class="alert alert-danger-custom alert-dismissible fade show" role="alert">
                <i class="bi bi-exclamation-triangle"></i> ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        </c:if>

        <!-- Registration Form -->
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="mb-3">
                <label for="fullName" class="form-label">
                    <i class="bi bi-person-badge"></i> Full Name
                </label>
                <input type="text" class="form-control form-control-lg" id="fullName" name="fullName"
                       value="${fullName}" placeholder="Enter your full name" required>
            </div>
            <div class="mb-3">
                <label for="username" class="form-label">
                    <i class="bi bi-at"></i> Username
                </label>
                <input type="text" class="form-control form-control-lg" id="username" name="username"
                       value="${username}" placeholder="Choose a username" required>
                <div class="form-text">Letters, numbers, and underscores only. Minimum 3 characters.</div>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">
                    <i class="bi bi-envelope"></i> Email Address
                </label>
                <input type="email" class="form-control form-control-lg" id="email" name="email"
                       value="${email}" placeholder="Enter your email" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">
                    <i class="bi bi-lock"></i> Password
                </label>
                <input type="password" class="form-control form-control-lg" id="password" name="password"
                       placeholder="Create a password (min 6 chars)" required>
            </div>
            <div class="mb-3">
                <label for="confirmPassword" class="form-label">
                    <i class="bi bi-lock-fill"></i> Confirm Password
                </label>
                <input type="password" class="form-control form-control-lg" id="confirmPassword" name="confirmPassword"
                       placeholder="Confirm your password" required>
            </div>
            <div class="d-grid mb-3">
                <button type="submit" class="btn btn-magenta btn-lg">
                    <i class="bi bi-person-plus"></i> Create Account
                </button>
            </div>
        </form>

        <hr>
        <p class="text-center mb-0">
            Already have an account?
            <a href="${pageContext.request.contextPath}/login" style="color: var(--primary); font-weight: 600;">
                Sign in here
            </a>
        </p>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
