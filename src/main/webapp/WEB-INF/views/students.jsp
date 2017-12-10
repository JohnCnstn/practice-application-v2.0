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

    <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.2.1.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/createPracticeWithHeadMaster.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/createPractice.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/createUniversity.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/createFaculty.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/createHeadMaster.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/createStudent.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/createSpeciality.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/slideMenu/BootSlideMenu.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/bower_components/datatables.net/js/jquery.dataTables.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/resources/js/test.js"/>"></script>



    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script>
        $(function () {
            $('#example1').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": true,
                "ordering": true,
                "info": false,
                "autoWidth": false
            });
        });
    </script>

    <script src="//cdn.jsdelivr.net/webshim/1.14.5/polyfiller.js"></script>
    <script>
        webshims.setOptions('waitReady', false);
        webshims.setOptions('forms-ext', {types: 'date'});
        webshims.polyfill('forms forms-ext');
    </script>

    <script>
        jQuery(document).ready(function($) {
            $(".clickable-row").click(function() {
                window.location = $(this).data("href");
            });
        });
    </script>

    <script>
        function openNav() {
            document.getElementById("mySidenav").style.width = "250px";
        }

        function closeNav() {
            document.getElementById("mySidenav").style.width = "0";
        }
    </script>

    <script>
        $(document).ready(function(){
            $("#myInput").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>

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

                                        <select id="universityId" name="universityId">
                                            <c:forEach items="${universityList}" var="i">
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

                                        <select id="facultyId" name="facultyId">
                                            <c:forEach items="${listOfFaculties}" var="i">
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

                                        <select id="companyId" name="companyId">
                                            <c:forEach items="${listOfCompanies}" var="i">
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

                                            <select id="specialityId" name="specialityId">
                                                <c:forEach items="${specialityList}" var="i">
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

                                            <select id="headMasterId" name="headMasterId">
                                                <c:forEach items="${listOfHeadMasters}" var="i">
                                                    <option value="${i.id}">${i.userName}</option>
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

            </sec:authorize>

            <sec:authorize access="hasRole('HEAD_MASTER')">

                <form:form name="form-Customer" commandName="practiceDto" action="sign-up" method="POST" id="customerForm">

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

            </sec:authorize>

    <div id="wrapper">

        <div id="table-wrapper">

        <div class="overlay"></div>

        <!-- Sidebar -->
        <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
            <ul class="nav sidebar-nav">

                <li class="sidebar-brand">
                    <a href="#">
                        ${user.userName}
                    </a>
                </li>

                <sec:authorize access="hasRole('ADMIN')">

                <li>
                    <a href="#"  data-toggle="modal" data-target="#headMasterModal">Create Head Master</a>
                </li>
                <li>
                    <a href="#"  data-toggle="modal" data-target="#createStudentModal">Create Student</a>
                </li>
                <li>
                    <a href="#"  data-toggle="modal" data-target="#practiceModal">Create Practice</a>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">University<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li class="dropdown-header">Dropdown heading</li>
                        <li><a href="#" data-toggle="modal" data-target="#universityModal">Create University</a></li>
                        <li><a href="#"  data-toggle="modal" data-target="#facultyModal">Create Faculty</a></li>
                        <li><a href="#"  data-toggle="modal" data-target="#specialityModal">Create Speciality</a></li>
                    </ul>
                </li>

                </sec:authorize>

                <sec:authorize access="hasRole('HEAD_MASTER')">

                    <li>
                        <a href="#"  data-toggle="modal" data-target="#myModal">Create Practice</a>
                    </li>

                </sec:authorize>

            </ul>
        </nav>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <button type="button" class="hamburger is-closed" data-toggle="offcanvas">
                <span class="hamb-top"></span>
                <span class="hamb-middle"></span>
                <span class="hamb-bottom"></span>
            </button>
                            <table class="table table-hover" id="example1" cellspacing="0" cellpadding="0" width="100%">
                                <thead>
                                <tr>
                                    <%--<th data-checkbox="true"></th>--%>
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

                                <sec:authorize access="hasRole('HEAD_MASTER')">

                                    <spring:url value="/head-master/userInfo" var="userProfileUrl" />

                                </sec:authorize>

                                <sec:authorize access="hasRole('ADMIN')">

                                    <spring:url value="/admin/userInfo" var="userProfileUrl" />

                                </sec:authorize>

                                <c:forEach items="${listOfStudents}" var="i" varStatus="status">
                                    <tr>
                                            <%--<td><form:checkbox path="students[${status}]" value="${i}"/></td>--%>
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

            <div class="navbar navbar-default navbar-fixed-top">

                <div class="container">

                    <%--<span class="navbar-left" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; open</span>--%>

                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">Practice application</a>
                    </div>

                    <%--<div id="mySidenav" class="sidenav">--%>
                    <%--<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>--%>

                    <%--<sec:authorize access="hasRole('ADMIN')">--%>

                    <%--<div class="container">--%>

                    <%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#universityModal">Create University</button>--%>
                    <%--</br>--%>
                    <%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#facultyModal">Create Faculty</button>--%>
                    <%--</br>--%>
                    <%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#headMasterModal">Create Head Master</button>--%>
                    <%--</br>--%>
                    <%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#createStudentModal">Create Student</button>--%>
                    <%--</br>--%>
                    <%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#practiceModal">Create Practice</button>--%>
                    <%--</br>--%>
                    <%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#specialityModal">Create Speciality</button>--%>

                    <%--</div>--%>

                    <%--</sec:authorize>--%>

                    <%--<sec:authorize access="hasRole('HEAD_MASTER')">--%>

                    <%--<div class="container">--%>

                    <%--<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Create Practice</button>--%>

                    <%--</div>--%>

                    <%--</sec:authorize>--%>

                    <%--</div>--%>

                    <form:form action="logout" method="get">
                        <button type="submit" class="btn navbar-btn navbar-right" id="header-btn">
                            Logout
                        </button>
                    </form:form>

                </div>
            </div>

        </div>
        <!-- /#page-content-wrapper -->

        </div>

    </div>

</body>
</html>
