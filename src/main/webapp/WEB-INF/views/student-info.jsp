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
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
</head>
<body>

<form:form name="form-SetOnPractice" commandName="practiceDto" method="POST" id="studentOnPracticeForm">

        <div class="container">

                <!-- Modal -->
                <div class="modal fade" id="studentOnPracticeModal" role="dialog">
                        <div class="modal-dialog">

                                <!-- Modal content-->
                                <div class="modal-content">
                                        <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Select practice</h4>
                                        </div>
                                        <div class="modal-body">

                                                <div id = "create_faculty">

                                                        <div class="form-group">

                                                                <label>Select practice:

                                                                        <select id="practiceId" name="practiceId">
                                                                                <c:forEach items="${listOfPractice}" var="i">
                                                                                        <option value="${i.id}">${i.headMaster.userName}</option>
                                                                                </c:forEach>
                                                                        </select>

                                                                </label>

                                                        </div>

                                                        <%--<div class="form-group">--%>
                                                                <%--<form:label path="name" for="facultyName">Your Faculty Name:</form:label>--%>
                                                                <%--<form:input path="name" type="text" name="facultyName" class="form-control" id="facultyName" required="required" placeholder="FKSiS"/>--%>
                                                        <%--</div>--%>

                                                        <div class="sign-up button">
                                                                <input type="submit" value="Submit" />
                                                        </div>

                                                </div>

                                        </div>

                                        <div class="modal-footer">
                                                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                                                <div class="col-sm-7" id="postResultDiv"></div>
                                        </div>

                                </div>

                        </div>
                </div>

        </div>

</form:form>

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
                        <div class="col-lg-6 col-lg-offset-3" >

                                <div class="panel panel-info">
                                        <div class="panel-heading">
                                                <h3 class="panel-title">${student.userName}</h3>
                                        </div>
                                        <div class="panel-body">
                                                <div class="row">

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
                                                                                <tr>
                                                                                        <td>University:</td>
                                                                                        <td>${student.speciality.faculty.university.name}</td>
                                                                                </tr>
                                                                                <tr>
                                                                                        <td>Faculty:</td>
                                                                                        <td>${student.speciality.faculty.name}</td>
                                                                                </tr>
                                                                                <tr>
                                                                                        <td>Speciality:</td>
                                                                                        <td>${student.speciality.name}</td>
                                                                                </tr>
                                                                                <tr>
                                                                                        <td>Is budget:</td>
                                                                                        <td>${student.budget}</td>
                                                                                </tr>

                                                                                        <td>Average score:</td>
                                                                                        <td>${student.avgScore}</td>
                                                                                </tr>
                                                                                <tr>
                                                                                        <td>Status:</td>
                                                                                        <td>${student.status}</td>
                                                                                </tr>

                                                                        </tbody>
                                                                </table>

                                                                <%--<a href="#" class="btn btn-primary">My Sales Performance</a>--%>
                                                                <%--<a href="#" class="btn btn-primary">Team Sales Performance</a>--%>
                                                        </div>
                                                </div>
                                        </div>
                                        <div class="panel-footer">
                                                <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                                                <span class="pull-right">
                                                        <button data-original-title="Set on practice" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#studentOnPracticeModal"><i class="glyphicon glyphicon-edit"></i></button>
                                                        <%--<a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>--%>
                                                        <%--<a data-original-title="Remove this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></a>--%>
                                                </span>
                                        </div>

                                </div>
                        </div>
                </div>
        </div>

</body>
</html>
