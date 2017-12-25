<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Practice application</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/tableStudent.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/slideMenu/slideMenu.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/sideBar.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/dataTable/dataTable.min.css"/>">

    <link rel="stylesheet" href="<c:url value="/resources/css/students/buttons/buttons.css"/>">

    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/creator/createPracticeWithHeadMaster.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/creator/createPractice.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/creator/createUniversity.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/creator/createFaculty.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/creator/createHeadMaster.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/creator/createStudent.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/creator/createSpeciality.js"/>"></script>


    <script type="text/javascript" src="<c:url value="/resources/js/student-view/loader/loadUniversities.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/loader/loadFaculties.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/loader/loadCompanies.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/loader/loadSpecialities.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/loader/loadHeadMasters.js"/>"></script>


    <script type="text/javascript" src="<c:url value="/resources/js/slideMenu/BootSlideMenu.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/alert/successalert.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/alert/erroralert.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/alert/sweetalert/sweetalert.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/alert/confirmalert.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/dataTables/datatables.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/student-view/head-master/assignStudentsOnPractice.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/head-master/releaseStudentsFromPractice.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/student-view/admin/deleteStudents.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/dataTables/colreoder.min.js"/>"></script>

    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

    <script>
        $(document).ready(function () {
            var table = $('#example1').DataTable({
                dom: '<"myTableToolBar">frtip',
                fnInitComplete: function(){
                    $('div.myTableToolBar').html(
                        '<sec:authorize access="hasRole('HEAD_MASTER')">' +
                        '   <a id="headMasterAssignButton" class="action-button shadow animate green">Assign</a>\n' +
                        '   <a id="headMasterReleaseButton" class="action-button shadow animate yellow">Release</a>' +
                        '</sec:authorize>' +
                        '<sec:authorize access="hasRole('ADMIN')">' +
                        '   <a id="headMasterAssignButton" class="action-button shadow animate green">Assign</a>\n' +
                        '   <a id="headMasterReleaseButton" class="action-button shadow animate yellow">Release</a>\n' +
                        '   <a id="deleteButton" class="action-button shadow animate red">Delete</a>' +
                        '</sec:authorize>'

                    );
                },
                "paging": true,
                "lengthChange": false,
                "searching": true,
                "ordering": true,
                "info": false,
                "autoWidth": false,
                "colReorder": true
            });
        });
    </script>

    <style>
        .myTableToolBar {
            float:left;
        }
    </style>

    <script>
        $(document).ready(function () {
            var trigger = $('.hamburger'),
                overlay = $('.overlay'),
                isClosed = false;

            trigger.click(function () {
                hamburger_cross();
            });

            function hamburger_cross() {

                if (isClosed == true) {
                    overlay.hide();
                    trigger.removeClass('is-open');
                    trigger.addClass('is-closed');
                    isClosed = false;
                } else {
                    overlay.show();
                    trigger.removeClass('is-closed');
                    trigger.addClass('is-open');
                    isClosed = true;
                }
            }

            $('[data-toggle="offcanvas"]').click(function () {
                $('#wrapper').toggleClass('toggled');
            });
        });
    </script>

</head>
<body>

<div class="col-sm-7" style="margin:20px 0px 20px 0px">
    <div id="getResultDiv" style="padding:20px 10px 20px 50px">
        <ul class="list-group">
        </ul>
    </div>
</div>

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

    <sec:authorize access="hasRole('ADMIN')">

        <form:form name="form-University" commandName="universityDto" method="POST" id="universityForm">
            <!-- Modal -->
            <div class="modal fade" id="universityModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Create university</h4>
                        </div>
                        <div class="modal-body">

                            <div id = "create_university">

                                <div class="form-group">
                                    <form:label path="name" for="universityName">University Name:</form:label>
                                    <form:input path="name" type="text" class="form-control" id="universityName" required="required" placeholder="BSUiR"/>
                                </div>

                                <div class="sign-up button">
                                    <input type="submit" value="Create" />
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

        </form:form>

        <form:form name="form-Faculty" commandName="facultyDto" method="POST" id="facultyForm">

            <div class="container">

                <!-- Modal -->
                <div class="modal fade" id="facultyModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Create faculty</h4>
                            </div>
                            <div class="modal-body">

                                <div id = "create_faculty">

                                    <div class="form-group">

                                        <label>Select university:

                                            <select id="universityId">
                                                <c:forEach items="${html}" var="i">
                                                    <option value="${i.id}">${i.name}</option>
                                                </c:forEach>
                                            </select>

                                        </label>

                                    </div>

                                    <div class="form-group">
                                        <form:label path="name" for="facultyName">Your Faculty Name:</form:label>
                                        <form:input path="name" type="text" name="facultyName" class="form-control" id="facultyName" required="required" placeholder="FKSiS"/>
                                    </div>

                                    <div class="sign-up button">
                                        <input type="submit" value="Create" />
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

        <form:form name="form-Speciality" commandName="specialityDto" method="POST" id="specialityForm">

            <div class="container">

                <!-- Modal -->
                <div class="modal fade" id="specialityModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Create speciality</h4>
                            </div>
                            <div class="modal-body">

                                <div id = "create_speciality">

                                    <div class="form-group">

                                        <label>Select a faculty:

                                            <select id="facultyId">
                                                <c:forEach items="${facultiesList}" var="i">
                                                    <option value="${i.id}">${i.name}</option>
                                                </c:forEach>
                                            </select>

                                        </label>

                                    </div>

                                    <div class="form-group">
                                        <form:label path="name" for="specialityName">Your Speciality Name:</form:label>
                                        <form:input path="name" type="text" class="form-control" id="specialityName" required="required" placeholder="POiT"/>
                                    </div>

                                    <div class="sign-up button">
                                        <input type="submit" value="Create" />
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

        <form:form name="form-HeadMaster" commandName="headMasterDto" method="POST" id="headMasterForm">

            <div class="container">

                <!-- Modal -->
                <div class="modal fade" id="headMasterModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Create HeadMaster</h4>
                            </div>
                            <div class="modal-body">

                                <div id = "create_headMaster">

                                    <div class="form-group">

                                        <div class="form-group">
                                            <form:label path="firstName" for="firstName">HeadMaster First Name:</form:label>
                                            <form:input path="firstName" type="text" class="form-control" id="firstName" required="required" placeholder="Pavel"/>
                                        </div>
                                        <div class="form-group">
                                            <form:label path="lastName" for="lastName">HeadMaster Last Name:</form:label>
                                            <form:input path="lastName" type="text" class="form-control" id="lastName" required="required" placeholder="Khankevich"/>
                                        </div>
                                        <div class="form-group">
                                            <form:label path="userName" for="userName">HeadMaster userName:</form:label>
                                            <form:input path="userName" type="text" class="form-control" id="userName" required="required" placeholder="Username"/>
                                        </div>
                                        <div class="form-group">
                                            <form:label path="email" for="email">HeadMaster email:</form:label>
                                            <form:input path="email" type="text" class="form-control" id="email" required="required" placeholder="email"/>
                                        </div>
                                        <div class="form-group">
                                            <form:label path="password" for="password">HeadMaster password:</form:label>
                                            <form:input path="password" type="password" class="form-control" id="password" required="required" placeholder="Password"/>
                                        </div>

                                        <label>Select Company:

                                            <select id="companyId">
                                                <c:forEach items="${companiesList}" var="i">
                                                    <option value="${i.id}">${i.name}</option>
                                                </c:forEach>
                                            </select>

                                        </label>

                                    </div>

                                    <div class="sign-up button">
                                        <input type="submit" value="Create" />
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

        <form:form name="form-CreateStudent" commandName="studentDto" method="POST" id="createStudentForm">

            <div class="container">

                <!-- Modal -->
                <div class="modal fade" id="createStudentModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Create Student</h4>
                            </div>
                            <div class="modal-body">

                                <div id = "create_student">

                                    <div class="form-group">

                                        <div class="form-group">
                                            <form:label path="firstName" for="studentFirstName">Student First Name:</form:label>
                                            <form:input path="firstName" type="text" class="form-control" id="studentFirstName" required="required" placeholder="Pavel"/>
                                        </div>
                                        <div class="form-group">
                                            <form:label path="lastName" for="studentLastName">Student Last Name:</form:label>
                                            <form:input path="lastName" type="text" class="form-control" id="studentLastName" required="required" placeholder="Khankevich"/>
                                        </div>
                                        <div class="form-group">
                                            <form:label path="userName" for="studentUserName">Student userName:</form:label>
                                            <form:input path="userName" type="text" class="form-control" id="studentUserName" required="required" placeholder="Username"/>
                                        </div>
                                        <div class="form-group">
                                            <form:label path="email" for="studentEmail">Student email:</form:label>
                                            <form:input path="email" type="text" class="form-control" id="studentEmail" required="required" placeholder="email"/>
                                        </div>
                                        <div class="form-group">
                                            <form:label path="password" for="studentPassword">Student password:</form:label>
                                            <form:input path="password" type="password" class="form-control" id="studentPassword" required="required" placeholder="Password"/>
                                        </div>

                                        <div class="form-group">
                                            <form:label path="avgScore" for="avgScore">Average score:</form:label>
                                            <form:input path="avgScore" type="number" class="form-control" id="avgScore" required="required"/>
                                        </div>

                                        <div class="form-group">
                                            <form:label path="budget" for="budget">Is budget:</form:label>
                                            <form:checkbox path="budget" class="form-control" id="budget"/>
                                        </div>

                                        <div class="form-group">

                                            <label>Select speciality:

                                                <select id="specialityId">
                                                    <c:forEach items="${specialitiesList}" var="i">
                                                        <option value="${i.id}">${i.name}</option>
                                                    </c:forEach>
                                                </select>

                                            </label>

                                        </div>

                                    <div class="sign-up button">
                                        <input type="submit" value="Create" />
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

            </div>

        </form:form>

        <form:form name="form-Practice" commandName="practiceDto" method="POST" id="practiceForm">

            <div class="container">

                <!-- Modal -->
                <div class="modal fade" id="practiceModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Create practice</h4>
                            </div>
                            <div class="modal-body">

                                <div id = "create_practice_admin">

                                    <div class="form-group">

                                        <div class="form-group has-feedback">
                                            <div class="form-group">
                                                <div class="input-group date">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <form:label path="startDate" for="startDateAdmin">Start date of practice:</form:label>
                                                    <form:input path="startDate" type="date" class="form-control" id="startDateAdmin" required="required" placeholder="18:12:1997"/>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="form-group">

                                        <div class="form-group has-feedback">
                                            <div class="form-group">
                                                <div class="input-group date">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <form:label path="endDate" for="endDateAdmin">End date of practice:</form:label>
                                                    <form:input path="endDate" type="date" class="form-control" id="endDateAdmin" required="required"/>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <form:label path="quantity" for="quantity">Quantity of students:</form:label>
                                        <form:input path="quantity" type="quantity" class="form-control" id="quantity" required="required"/>
                                    </div>

                                    <label>Select HeadMaster:

                                        <select id="headMasterId">
                                            <c:forEach items="${headMasterList}" var="i">
                                                <option value="${i.id}">${i.name}</option>
                                            </c:forEach>
                                        </select>

                                    </label>

                                    <div class="sign-up button">
                                        <input type="submit" value="Sign up" />
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

        <form:form name="form-SetOnPractice" commandName="studentDto" method="POST" id="assignOnPracticeForm">

            <div class="container">

                <!-- Modal -->
                <div class="modal fade" id="assignOnPracticeModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Select practice</h4>
                            </div>
                            <div class="modal-body">

                                <div id = "assignOnPractice">

                                    <div class="form-group">

                                        <label>Select practice:

                                            <table class="table table-hover myTable" id="practiceTable" cellspacing="0" cellpadding="0" width="100%">
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
                                                    <tr data-toggle="${i.id}">
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
                                        <input type="submit" value="Submit" />
                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>
                </div>

            </div>

        </form:form>

    </sec:authorize>

    <sec:authorize access="hasRole('HEAD_MASTER')">

        <form:form name="form-Customer" commandName="practiceDto" method="POST" id="customerForm">

            <div class="container">

                <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Create practice</h4>
                            </div>
                            <div class="modal-body">

                                <div id = "create_practice">

                                    <div class="form-group">

                                        <div class="form-group has-feedback">
                                            <div class="form-group">
                                                <div class="input-group date">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <form:label path="startDate" for="startDate">Start date of practice:</form:label>
                                                    <form:input path="startDate" type="date" name="startDate" class="form-control" id="startDate" required="required" placeholder="18:12:1997"/>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="form-group">

                                        <div class="form-group has-feedback">
                                            <div class="form-group">
                                                <div class="input-group date">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </div>
                                                    <form:label path="endDate" for="endDate">End date of practice:</form:label>
                                                    <form:input path="endDate" type="date" name="endDate" class="form-control" id="endDate" required="required"/>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <div class="form-group">
                                        <form:label path="quantity" for="quantity">Quantity of students:</form:label>
                                        <form:input path="quantity" type="quantity" class="form-control" id="quantity" required="required"/>
                                    </div>

                                    <div class="sign-up button">
                                        <input type="submit"/>
                                    </div>

                                </div>

                            </div>

                        </div>

                    </div>
                </div>

            </div>

        </form:form>

    </sec:authorize>

    <div id="wrapper">

        <!-- Sidebar -->
        <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
            <ul class="nav sidebar-nav">

                <li class="sidebar-brand">
                    <a href="#">
                        ${user.userName}
                    </a>
                </li>

                <sec:authorize access="hasRole('ADMIN')">

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Student<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="dropdown-header">Actions with students</li>
                            <li><a id="getAllSpecialities" data-toggle="modal" data-target="#createStudentModal">Create</a></li>
                            <li><a href="#" id="assignButton" data-toggle="modal" data-target="#assignOnPracticeModal">Assign</a></li>
                            <li><a href="#" id="releaseButton" data-toggle="modal" data-target="#assignOnPracticeModal">Release</a></li>
                        </ul>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">University<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="dropdown-header">Actions with university</li>
                            <li><a href="#" data-toggle="modal" data-target="#universityModal">Create University</a></li>
                            <li><a href="#" id="getAllUniversities" data-toggle="modal" data-target="#facultyModal">Create Faculty</a></li>
                            <li><a href="#" id="getAllFaculties" data-toggle="modal" data-target="#specialityModal">Create Speciality</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="#" id="getAllCompanies"  data-toggle="modal" data-target="#headMasterModal">Create Head Master</a>
                    </li>
                    <li>
                        <a href="#" id="getAllHeadMasters" data-toggle="modal" data-target="#practiceModal">Create Practice</a>
                    </li>

                </sec:authorize>

                <sec:authorize access="hasRole('HEAD_MASTER')">

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown">Practice<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="dropdown-header">Actions with practice</li>
                            <a data-toggle="modal" data-target="#myModal">Create</a>
                        </ul>
                    </li>

                </sec:authorize>

            </ul>
        </nav>

        <!-- Page Content -->
        <div id="page-content-wrapper">

            <div id="table-wrapper">

                <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
                    <span class="hamb-top"></span>
                    <span class="hamb-middle"></span>
                    <span class="hamb-bottom"></span>
                </button>

            <table class="table table-hover myTable" id="example1" cellspacing="0" cellpadding="0" width="100%">
                <thead>
                <tr>
                    <th><span>First Name</span></th>
                    <th><span>Second Name</span></th>
                    <th><span>University</span></th>
                    <th><span>Faculty</span></th>
                    <th><span>Is budget</span></th>
                    <th><span>Average score</span></th>
                    <th><span>Status</span></th>
                    <th><span>Info</span></th>
                </tr>
                </thead>
                <tbody id="myTable">

                <spring:url value="/userInfo" var="userProfileUrl" />

                <c:forEach items="${listOfStudents}" var="i" varStatus="status">
                    <tr id="${i.id}">
                        <td class='clickable-row' data-href="${userProfileUrl}/${i.id}">${i.firstName}</td>
                        <td>${i.lastName}</td>
                        <td>${i.speciality.faculty.university.name}</td>
                        <td>${i.speciality.faculty.name}</td>
                        <td>${i.budget}</td>
                        <td>${i.avgScore}</td>
                        <td>${i.status}</td>
                        <td>
                            <button class="btn btn-info" onclick="location.href='${userProfileUrl}/${i.id}'">Info
                                <i class="fa fa-info-circle" aria-hidden="true"></i>
                            </button>

                        </td>
                    </tr>
                </c:forEach>

                </tbody>

            </table>

        </div>

        </div>

    </div>

</body>
</html>
