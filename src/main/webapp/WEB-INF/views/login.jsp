<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Log in</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/main.css">
    <link rel="stylesheet" href="/resources/css/logout-success.css">
</head>
<body>

<c:if test="${not empty error}">
    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
        <circle class="path circle" fill="none" stroke="#D06079" stroke-width="6" stroke-miterlimit="10" cx="65.1" cy="65.1" r="62.1"/>
        <line class="path line" fill="none" stroke="#D06079" stroke-width="6" stroke-linecap="round" stroke-miterlimit="10" x1="34.4" y1="37.9" x2="95.8" y2="92.3"/>
        <line class="path line" fill="none" stroke="#D06079" stroke-width="6" stroke-linecap="round" stroke-miterlimit="10" x1="95.8" y1="38" x2="34.4" y2="92.2"/>
    </svg>
    <p class="error">Wrong userName or password!</p>
</c:if>

<c:if test="${not empty logout}">
    <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
        <circle class="path circle" fill="none" stroke="#73AF55" stroke-width="6" stroke-miterlimit="10" cx="65.1" cy="65.1" r="62.1"/>
        <polyline class="path check" fill="none" stroke="#73AF55" stroke-width="6" stroke-linecap="round" stroke-miterlimit="10" points="100.2,40.2 51.5,88.8 29.8,67.5 " />
    </svg>
    <p class="success">Logout Successfully!</p>
</c:if>

<div class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Practice application</a>
        </div>

        <form:form action="sign-up" method="get">
            <button type="submit" class="btn navbar-btn navbar-right" id="header-btn">
                Sign up
            </button>
        </form:form>

    </div>
</div>

<form:form name="form-Login" action="login" method="post">

<div class="container">
    <div class="form">
        <div col-lg-4 col-lg-offset-8>
            <div id = "login">

                <h1>Sign in</h1>
                <div class="form-group">
                    <label for="userName">Your username:</label>
                    <div class="input-group ">
                        <div class="input-group-addon"><i class="fa fa-user"></i></div>
                        <input type="text" name="username-signin" class="form-control" id="userName" required="required" placeholder="Username">
                    </div>
                </div>

                <div class="form-group">
                    <label for="password">Your password:</label>
                    <div class="input-group ">
                        <div class="input-group-addon"><i class="fa fa-key"></i></div>
                        <input type="password" name="password-signin" class="form-control" id="password" required="required" placeholder="Password">
                    </div>
                </div>

                <%--<form>--%>
                    <%--<div class="form-group">--%>
                        <%--<div class="input-group ">--%>
                            <%--<div class="input-group-addon"><i class="fa fa-search"></i></div>--%>
                            <%--<input type="text" id="myInput" class="form-control" placeholder="Search da Fish">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</form>--%>

                <div class="check-box">
                    <div class="keeplogin">
                        <input type="checkbox" name="remember-me" id="remember-me" />
                        <label for="remember-me">Keep me logged in</label>
                    </div>
                </div>

                <div class="login button">
                    <input type="submit" value="Login" />
                </div>

            </div>
        </div>
    </div>
</div>

<div id="f">
    <div class="container">
        <div class="row centered">
            <div col-lg-10 col-lg-offset-1>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-vk"></i></a>
            </div>
        </div>
    </div>
</div>

</form:form>
</body>
<%--</body>--%>
</html>
