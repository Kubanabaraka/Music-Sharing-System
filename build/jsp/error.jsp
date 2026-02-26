<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Error"/>
</jsp:include>

<div class="auth-container">
    <div class="auth-card text-center">
        <div style="font-size: 4rem; color: var(--accent-magenta); margin-bottom: 1rem;">
            <i class="bi bi-exclamation-triangle"></i>
        </div>
        <h2 style="color: var(--dark-accent);">Oops! Something went wrong</h2>
        <p class="text-muted mb-4">
            The page you're looking for might not exist or an error occurred.
        </p>
        <div class="d-flex justify-content-center gap-2">
            <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-primary-custom">
                <i class="bi bi-house"></i> Go to Dashboard
            </a>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-custom">
                <i class="bi bi-box-arrow-in-right"></i> Login
            </a>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
