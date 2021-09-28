<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:video-wrapper title="Upload Blog" bodyheader="Upload Blog"><jsp:attribute name="script">
    <script>
        $(document).ready(function () {
            showGlobalLoader(false);

            function showGlobalLoader(state) {
                if (state) {
                    $('#global-loader-wrapper').show();
                } else {
                    $('#global-loader-wrapper').hide();
                }
            }
        });
    </script>
      </jsp:attribute>
    <jsp:body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container-fluid">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                        data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <a class="navbar-brand" href="#">Demo Video Blog</a>
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <c:if test="${action eq false}">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page"
                                   href="${pageContext.request.contextPath}/blog"><i
                                        class="fas fa-home me-2"></i>Home</a>
                            </li>
                        </c:if>
                        <c:if test="${action eq true}">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page"
                                   href="${pageContext.request.contextPath}/dashboard"><i
                                        class="fas fa-home me-2"></i>Home</a>
                            </li>
                        </c:if>
                        <c:if test="${action eq false}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/login"><i
                                        class="fas fa-user me-2"></i>Login</a>
                            </li>
                        </c:if>
                        <c:if test="${action eq true}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/signout"><i
                                        class="fas fa-user me-2"></i>Signout</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container pt-2">
            <c:if test="${action eq true}">
                <div class="row">
                    <div class="col-12 col-md-6 col-lg-12">
                        <div class="card text-center m-2">
                            <div class="card-body">
                                <form:form action="${pageContext.request.contextPath}/register" class="pt-3"
                                           id="requestForm" method="POST">
                                    <input type="text" id="url" name="url" placeholder="Enter Youtube valid embeded URL">&nbsp;&nbsp;
                                    <input type="text" id="title" name="description" placeholder="Enter Youtube title">&nbsp;&nbsp;
                                    <button type="submit" class="btn-primary" id="submit">Submit</button>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">

                <c:forEach items="${videoList}" var="video">
                    <div class="col-12 col-md-6 col-lg-4">

                        <div class="card text-center m-2 shadow">
                            <div class="card-header">
                                <b>Uploaded By: </b><i>${video.uploadedUser == null ? 'N/A' : video.uploadedUser}(${video.uploadedAt})</i>
                            </div>
                            <div class="card-body">
                                <iframe width="100%" height="315" src="${video.url}"
                                        title="YouTube video player" frameborder="0"
                                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                                        allowfullscreen></iframe>
                            </div>
                            <c:if test="${action eq true}">
                                <div class="card-footer">
                                        <span class="badge p-2 ps-2 pe-2 rounded-0 bg-info"><i
                                                class="fas fa-eye me-2"></i>View: 125</span>
                                    <span class="badge p-2 ps-2 pe-2 rounded-0 bg-success"><i
                                            class="fas fa-thumbs-up me-2"></i>Like: 12</span>
                                    <span class="badge p-2 ps-2 pe-2 rounded-0 bg-danger"><i
                                            class="fas fa-thumbs-down me-2"></i>Dislike: 5</span>
                                    <span class="badge p-2 ps-2 pe-2 rounded-0 bg-info"><i
                                            class="fas fa-eye me-2"></i>Details</span>
                                </div>
                            </c:if>
                        </div>
                    </div>

                </c:forEach>

            </div>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <hr class="mt-5 mb-0">
                    <p class="mb-0 p-3 text-center text-muted">All Right Reserved &copy; 2021</p>
                </div>
            </div>
        </div>
    </jsp:body>
</t:video-wrapper>
<!-- Main JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/script.js"></script>
<script type="text/javascript">
    showGlobalLoader(false);

    function showGlobalLoader(state) {
        if (state) {
            $('#global-loader-wrapper').show();
        } else {
            $('#global-loader-wrapper').hide();
        }
    }
</script>