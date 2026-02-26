<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="includes/header.jsp">
    <jsp:param name="title" value="Welcome"/>
</jsp:include>

<!-- Landing Page Hero -->
<div class="hero-section text-center">
    <div class="container position-relative">
        <h1><i class="bi bi-music-note-list"></i> Music Playlist Sharing System</h1>
        <p class="lead mb-4">Create, curate, and share your favorite music playlists with the world.</p>
        <div class="d-flex justify-content-center gap-3">
            <a href="${pageContext.request.contextPath}/register" class="btn btn-lg btn-magenta px-4">
                <i class="bi bi-person-plus"></i> Get Started
            </a>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-lg btn-outline-light px-4">
                <i class="bi bi-box-arrow-in-right"></i> Login
            </a>
        </div>
    </div>
</div>

<!-- Features Section -->
<section class="py-5">
    <div class="container">
        <h2 class="text-center mb-2" style="color: var(--dark-accent); font-weight: 700;">Why Choose MPSS?</h2>
        <p class="text-center text-muted mb-5">Everything you need to organize and share your music taste.</p>
        <div class="row g-4">
            <div class="col-md-4">
                <div class="feature-card h-100">
                    <div class="feature-icon bg-primary-custom">
                        <i class="bi bi-collection-play"></i>
                    </div>
                    <h5 style="color: var(--dark-accent);">Create Playlists</h5>
                    <p class="text-muted">Organize your favorite songs into themed playlists with custom genres and descriptions.</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="feature-card h-100">
                    <div class="feature-icon bg-magenta-custom">
                        <i class="bi bi-share"></i>
                    </div>
                    <h5 style="color: var(--dark-accent);">Share Publicly</h5>
                    <p class="text-muted">Make your playlists public for others to discover, or keep them private for yourself.</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="feature-card h-100">
                    <div class="feature-icon bg-purple-custom">
                        <i class="bi bi-compass"></i>
                    </div>
                    <h5 style="color: var(--dark-accent);">Explore & Discover</h5>
                    <p class="text-muted">Browse public playlists from other users and discover new music across all genres.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- Stats Section -->
<section class="py-5" style="background-color: var(--cream);">
    <div class="container text-center">
        <div class="row g-4">
            <div class="col-md-4">
                <h2 style="color: var(--primary); font-weight: 700;">Unlimited</h2>
                <p class="text-muted">Playlists to Create</p>
            </div>
            <div class="col-md-4">
                <h2 style="color: var(--accent-magenta); font-weight: 700;">Share</h2>
                <p class="text-muted">With the Community</p>
            </div>
            <div class="col-md-4">
                <h2 style="color: var(--highlight-purple); font-weight: 700;">100% Free</h2>
                <p class="text-muted">No Hidden Costs</p>
            </div>
        </div>
    </div>
</section>

<!-- CTA Section -->
<section class="py-5 text-center">
    <div class="container">
        <h3 style="color: var(--dark-accent); font-weight: 700;">Ready to Start Sharing?</h3>
        <p class="text-muted mb-4">Join our community and start creating playlists today.</p>
        <a href="${pageContext.request.contextPath}/register" class="btn btn-primary-custom btn-lg px-5">
            <i class="bi bi-rocket-takeoff"></i> Sign Up Now
        </a>
    </div>
</section>

<jsp:include page="includes/footer.jsp"/>
