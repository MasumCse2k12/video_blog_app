<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0" name="viewport">
    <meta name="description" content="">
    <meta name="auther" content="">
    <title>Video Blog</title>
    <link rel="icon" href="${pageContext.request.contextPath}/resources/assets/image/logo.png" type="image/x-icon">
    <link href="https://fonts.googleapis.com/css?family=Hind+Siliguri:400,500,600,700" rel="stylesheet">
    <!-- Fontawesome -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/assets/fontawesome/css/font-awesome.min.css">
    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/assets/bootstrap/css/bootstrap.min.css">
    <!-- main style -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/assets/css/style.css">
    <!-- responsive style -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/assets/css/responsive.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
    <![endif]-->

</head>

<body>
<div class="login-page">
    <div class="login-form">
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-lg-4 position-relative">
                    <h4 class="title text-center">
                        <small>Log in to</small>
                        <hr/>
                        <b class="logo-name">Video Blog</b>
                    </h4>
                </div>
                <div class="col-sm-12 col-lg-7 position-relative">
                    <form action="${pageContext.request.contextPath}/j_login" method='POST' class="pt-3"
                          id="loginForm">
                        <c:if test="${not empty signout}">
                            <div class="alert alert-success border border-success">
                                <label>Logout successful</label>
                            </div>
                        </c:if>
                        <c:if test="${not empty sessionOut}">
                            <div class="alert alert-success border border-success">
                                <label>Logout for Session Timeout</label>
                            </div>
                        </c:if>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger border border-danger">
                                <c:out value="${error}"/>
                                <p id="demo"></p>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label for="username"><b>User ID</b>&nbsp;<span class="text-danger">*</span></label>
                            <input type="email" name="username" class="form-control" id="username"
                                   placeholder="Enter email" required autofocus>
                        </div>
                        <div class="form-group">
                            <label for="loginPass"><b>Password</b>&nbsp;<span
                                    class="text-danger">*</span></label>
                            <input type="password" name="password" class="form-control" id="loginPass" readonly
                                   onfocus="if (this.hasAttribute('readonly')) { this.removeAttribute('readonly'); this.blur();    this.focus();  }"
                                   placeholder="Enter password" required>
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <a href="javascript:void(0);" data-toggle="modal"
                                   data-target="#registerUser">Not Registered?</a>
                                &nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/blog">Go to Home</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Sign In&nbsp;&nbsp;
                                <i class="fa fa-sign-in fa-lg"></i>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Register User Modal -->
<div class="modal fade" id="registerUser" tabindex="-1" role="dialog" aria-labelledby="registerUser"
     aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="resetPassword">Register User</h5>
            </div>
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/user/register" class="pt-3" id="requestForm" method="POST">
                    <div class="form-group">
                        <label for="userEmail"><b>User Id</b></label>
                        <input type="email" class="form-control" id="userEmail" name="email" placeholder="Enter email"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="userPassword"><b>Password</b></label>
                        <input type="password" class="form-control" id="userPassword" name="password" placeholder="Enter password"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword"><b>Confirm Password</b></label>
                        <input type="password" class="form-control" id="confirmPassword"
                               placeholder="Enter Confirm password"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="userFullName"><b>User FullName</b></label>
                        <input type="text" class="form-control" id="userFullName" name="name" placeholder="Enter full name"
                               required>
                    </div>
                    <div class="form-group">
                        <label for="userPhone"><b>User Contact</b></label>
                        <input type="text" class="form-control" id="userPhone" name="phone" placeholder="Enter phone number">
                    </div>
                    <div class="form-group text-right">
                        <br>
                        <button type="button" class="btn btn-danger" data-dismiss="modal" aria-label="Close">
                            Close&nbsp;&nbsp;
                            <i class="fa fa-times fa-lg"></i>
                        </button>&nbsp;&nbsp;
                        <button type="button" id="registerUserRequest" class="btn btn-primary">Save
                            <i class="fa fa-sign-in fa-lg"></i>
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/assets/js/jquery.validate.min.js"></script>
<!-- Bootstrap -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/assets/bootstrap/js/bootstrap.min.js"></script>
<!-- Main JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/wow.min.js"></script>
<!-- Main JS -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/js/script.js"></script>

<script type="text/javascript">

    function resetModal() {
        $('#userEmail').val();
        $('#userPassword').val();
        $('#confirmPassword').val();
        $('#userFullName').val();
        $('#userPhone').val();
    }

    $("#registerUserRequest").click(function (ev) {
        ev.preventDefault();
        var password = $("#userPassword").val();
        var confirmPass = $("#confirmPassword").val();
        var form = $('#requestForm');
        form.validate();
        if (form.valid()) {

            if (password != '' && (password.length < 6 || password.length > 12)) {
                alert('Please put down min 6 characters & max 12 characters as password');
                return;
            }

            if (password != confirmPass) {
                alert('Enter password & confirm password are not same');
                return;
            }
            form.submit();
        } else {
            alert('Enter all the user information properly!');
        }
    });

</script>
</body>

</html>