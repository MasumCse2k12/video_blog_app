<%@tag description="Page template" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="menu" fragment="true" %>
<%@attribute name="title" required="true" %>
<%@attribute name="script" fragment="true" %>
<%@attribute name="bodyheader" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html removeIntertagSpaces="true">
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0" name="viewport">
        <meta name="description" content="">
        <meta name="auther" content="">
        <title>Video App</title>
        <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/image/gob-logo.png"
              type="image/x-icon">

            <%-- CSS--%>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/resources/assets/css/materialize.min.css">
        <link rel="stylesheet" type="text/css"
              href="${pageContext.request.contextPath}/resources/assets/css/jquery.dataTables.css">

            <%--        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/main.css">--%>


            <%-- Scripts--%>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/resources/assets/js/materialize.min.js"></script>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/resources/assets/js/jquery.dataTables.js"></script>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate.min.js"></script>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/resources/assets/js/script.js"></script>

        <jsp:invoke fragment="script"/>

        <script>
        </script>
        <style>

            /*right bottom*/
            /*#toast-container {*/
            /*    position: fixed !important;*/
            /*    top: auto !important;*/
            /*    bottom: 15px !important;*/
            /*    right: 15px !important;*/
            /*    left: auto !important;*/
            /*    color: #fff*/
            /*}*/

            /*left bottom*/

            #toast-container {
                position: fixed !important;
                top: auto !important;
                bottom: 15px !important;
                left: 15px !important;
                right: auto !important;
                color: #fff
            }

            /*center middle*/
            /*#toast-container {*/
            /*    position: fixed !important;*/
            /*    top: 50% !important;*/
            /*    left: 50% !important;*/
            /*    transform: translate(-50%, -50%);*/
            /*    color: #fff*/
            /*}*/

            .global-loader-wrapper {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100vh;
                z-index: 99999
            }

            .global-loader-overlay {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100vh;
                background-color: #f1f1f1;
                opacity: .9
            }

            .loader-box {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%)
            }

            .circle-clipper .circle {
                border-right-color: #20a868 !important;
                border-left-color: #369 !important;
                border-top-color: #f1f1f1 !important
            }

        </style>
    </head>
    <body>
    <div id="container-fluid">
        <jsp:doBody/>
    </div>
    <div class="global-loader-wrapper" id="global-loader-wrapper">
        <div class="global-loader-overlay"></div>
        <div class="loader-box">
            <div class="preloader-wrapper active">
                <div class="spinner-layer spinner-red-only">
                    <div class="circle-clipper left">
                        <div class="circle"></div>
                    </div>
                    <div class="gap-patch">
                        <div class="circle"></div>
                    </div>
                    <div class="circle-clipper right">
                        <div class="circle"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</compress:html>