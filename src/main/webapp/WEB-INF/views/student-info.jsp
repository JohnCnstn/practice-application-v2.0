<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <title>Title</title>
        <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">
        <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/"/>"></script>
</head>
<body>
        <div class="navbar navbar-default navbar-fixed-top">

                <div class="container">

                        <div class="navbar-header">
                                <a class="navbar-brand" href="#">Practice application</a>
                        </div>

                        <form:form action="logout" method="get">
                                <button type="submit" class="btn navbar-btn navbar-right" id="header-btn">
                                        Logout
                                </button>
                        </form:form>

                </div>
        </div>

        <div class="form-group" id="wrapper">
                <div class="row">
                        <div class="col-md-5  toppad  pull-right col-md-offset-3 ">
                                <A href="edit.html" >Edit Profile</A>

                                <p class=" text-info">May 05,2014,03:00 pm </p>
                        </div>
                        <div class="col-lg-6 col-lg-offset-3" >

                                <div class="panel panel-info">
                                        <div class="panel-heading">
                                                <h3 class="panel-title">Sheena Shrestha</h3>
                                        </div>
                                        <div class="panel-body">
                                                <div class="row">
                                                        <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"> </div>

                                                        <div class=" col-md-9 col-lg-9 ">

                                                                <table class="table table-user-information">
                                                                        <tbody id="userInfoTable">
                                                                                <tr>
                                                                                        <td>First name:</td>
                                                                                        <td>${student.firstName}</td>
                                                                                </tr>
                                                                                <tr>
                                                                                        <td>Last name:</td>
                                                                                        <td>${student.lastName}</td>
                                                                                </tr>
                                                                                <tr>
                                                                                        <td>Email:</td>
                                                                                        <td>${student.email}</td>
                                                                                </tr>

                                                                        </tbody>
                                                                </table>

                                                                <a href="#" class="btn btn-primary">My Sales Performance</a>
                                                                <a href="#" class="btn btn-primary">Team Sales Performance</a>
                                                        </div>
                                                </div>
                                        </div>
                                        <div class="panel-footer">
                                                <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                                                <span class="pull-right">
                            <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                            <a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>
                        </span>
                                        </div>

                                </div>
                        </div>
                </div>
        </div>

</body>
</html>
