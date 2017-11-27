<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <title>Title</title>
</head>
<body>


        <h1>${student.userName}</h1>
        <h1>${student.firstName}</h1>
        <h1>${student.email}</h1>
        <h1>${student.faculty.name}</h1>
        <h1>${student.faculty.university.name}</h1>
        <h1>${student.practice.startDate}</h1>

        <spring:url value="/head-master/userInfo/${student.id}/setPractice" var="userProfileUrl" />

        <button class="btn btn-info"
                onclick="location.href='${userProfileUrl}'">Submit</button>

</body>
</html>
