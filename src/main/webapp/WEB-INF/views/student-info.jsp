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
    <link rel="stylesheet" href="<c:url value="/resources/css/tableStudent.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/dataTable/dataTable.min.css"/>">



    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/alert/sweetalert/sweetalert.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/student-info-view/selectPractice.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-info-view/removeFromPractice.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/student-info-view/head-master/head-master-assignOnPractice.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-info-view/head-master/head-master-romoveFromPractice.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/alert/successalert.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/alert/deletealert.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/alert/erroralert.js"/>"></script>

    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/dataTables/datatables.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"/>"></script>

</head>
<body>

<form:form name="form-SetOnPractice" commandName="arrayParam" method="POST" id="studentOnPracticeForm">

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

                                    <table class="table table-hover myTable" id="setOnPracticeTable" cellspacing="0" cellpadding="0" width="100%">
                                        <thead>
                                        <tr>
                                            <th><span>Company</span></th>
                                            <th><span>Head master</span></th>
                                            <th><span>Start date</span></th>
                                            <th><span>End date</span></th>
                                        </tr>
                                        </thead>
                                        <tbody id="myTable">

                                        <spring:url value="/userInfo" var="userProfileUrl" />

                                        <c:forEach items="${practiceDtoList}" var="i">
                                            <tr id="${i.id}" data-toggle="${i.id}">
                                                <td>${i.companyName}</td>
                                                <td>${i.headMasterName}</td>
                                                <td>${i.startDate}</td>
                                                <td>${i.endDate}</td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>

                                    </table>

                                </label>

                            </div>

                            <div class="sign-up button">
                                <input id="assignButton" type="submit" value="Submit" />
                            </div>

                        </div>

                    </div>

                    <div class="modal-footer">
                        <div class="col-sm-7" id="postResultDiv"></div>
                    </div>

                </div>

            </div>
        </div>

    </div>

</form:form>

<form:form name="form-DeleteFromPractice" commandName="arrayParam" method="POST" id="deleteStudentFromPracticeForm">

    <div class="container">

        <!-- Modal -->
        <div class="modal fade" id="deleteStudentFromPracticeModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Select practice</h4>
                    </div>
                    <div class="modal-body">

                        <div id = "delete_from_practice">

                            <div class="form-group">

                                <label>Select practice:

                                    <table class="table table-hover myTable" id="removeFromPracticeTable" cellspacing="0" cellpadding="0" width="100%">
                                        <thead>
                                        <tr>
                                            <th><span>Company</span></th>
                                            <th><span>Head master</span></th>
                                            <th><span>Start date</span></th>
                                            <th><span>End date</span></th>
                                        </tr>
                                        </thead>
                                        <tbody>

                                        <spring:url value="/userInfo" var="userProfileUrl" />

                                        <c:forEach items="${studentPracticeList}" var="i">
                                            <tr id="${i.id}" data-toggle="${i.id}">
                                                <td>${i.companyName}</td>
                                                <td>${i.headMasterName}</td>
                                                <td>${i.startDate}</td>
                                                <td>${i.endDate}</td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>

                                    </table>

                                </label>

                            </div>

                            <div class="sign-up button">
                                <input id="removeButton" type="submit" value="Submit" />
                            </div>

                        </div>

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

                            <table class="table table-bordered table-user-information">
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
                                <tr>
                                    <td>Average score:</td>
                                    <td>${student.avgScore}</td>
                                </tr>
                                <tr>
                                    <td>Status:</td>
                                    <td>${student.status}</td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <sec:authorize access="hasRole('ADMIN')">
                    <div class="panel-footer">
                        <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                        <span class="pull-right">
                                                                <button data-original-title="Remove from practice" type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#deleteStudentFromPracticeModal"><i class="glyphicon glyphicon-remove"></i></button>
                                                                <button data-original-title="Set on practice" type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#studentOnPracticeModal"><i class="glyphicon glyphicon-edit"></i></button>
                        </span>
                    </div>
                </sec:authorize>
                <sec:authorize access="hasRole('HEAD_MASTER')">
                    <div class="panel-footer">
                        <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-envelope"></i></a>
                        <span class="pull-right">
                                                                <button id="headMasterRemoveFromPractice" data-original-title="Remove from practice" type="button" class="btn btn-sm btn-danger"><i class="glyphicon glyphicon-remove"></i></button>
                                                                <button id="headMasterSetOnPractice" data-original-title="Set on practice" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></button>
                        </span>
                    </div>
                </sec:authorize>

            </div>
        </div>
    </div>
</div>

</body>
</html>
