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
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <link rel="stylesheet" href="/resources/css/font-awesome.min.css">
    <link rel="stylesheet" href="/resources/css/main.css">
</head>

<body>

<spring:hasBindErrors name="nameOfYourModelAttribute">
    <c:if test="${errors.globalErrorCount > 0}">
        <div class="alert alert-danger"><form:errors/></div>
    </c:if>
</spring:hasBindErrors>

    <div class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Practice application</a>
            </div>

            <form:form action="login" method="get">
                <button type="submit" class="btn navbar-btn navbar-right" id="header-btn">
                    Log in
                </button>
            </form:form>

        </div>
    </div>

    <form:form name="form-SignUp" commandName="user" action="sign-up" method="POST">

    <div class="container">
        <div class="form">
                <div id = "sign_up">

                    <h1>Sign up</h1>
                    <div class="form-group">
                        <form:label path="firstName" for="firstName">Your First Name:</form:label>
                        <form:input path="firstName" type="text"  pattern=".{2,}" title="2 символа минимум" maxlength="10" name="firstName" class="form-control" id="firstName" required="required" placeholder="Pavel"/>
                        <form:errors path="firstName" cssClass="has-error"/>
                    </div>
                    <div class="form-group">
                        <form:label path="lastName" for="lastName">Your Last Name:</form:label>
                        <form:input path="lastName" type="text" pattern=".{2,}" title="2 символа минимум" maxlength="10" name="lastName" class="form-control" id="lastName" required="required" placeholder="Khankevich"/>
                        <form:errors path="lastName" cssClass="has-error"/>
                    </div>
                    <div class="form-group">
                        <form:label path="userName" for="userName">Your username:</form:label>
                        <form:input path="userName" type="text" pattern=".{5,}" title="5 символов минимум" maxlength="10" name="userName" class="form-control" id="userName" required="required" placeholder="Username"/>
                        <form:errors path="userName" cssClass="has-error"/>
                    </div>
                    <div class="form-group">
                        <form:label path="email" for="email">Your email:</form:label>
                        <form:input path="email" type="text" pattern=".{5,}" title="5 символов минимум" maxlength="20" name="email" class="form-control" id="email" required="required" placeholder="email"/>
                        <form:errors path="email" cssClass="has-error"/>
                    </div>
                    <div class="form-group">
                        <form:label path="password" for="pwd">Your password:</form:label>
                        <form:input path="password" type="password" pattern=".{5,}" title="5 символов минимум" maxlength="20" name="password" class="form-control" id="pwd" required="required" placeholder="Password"/>
                        <form:errors path="password" cssClass="has-error"/>
                    </div>
                    <div class="form-group">
                        <form:label path="matchingPassword" for="pwdMatches">Confirm your password:</form:label>
                        <form:input path="matchingPassword" type="password" pattern=".{5,}" title="5 символов минимум" maxlength="20" name="matchingPassword" class="form-control" id="pwdMatches" required="required" placeholder="Password"/>
                    </div>

                    <div class="form-group">
                        <form:label path="avgScore" for="avgScore">Set your average score:</form:label>
                        <form:input path="avgScore" type="number" step="0.01" min="0" max="10"  class="form-control" id="avgScore" required="required"/>
                    </div>

                    <div class="form-group">
                        <form:label path="budget" for="budget">Is budget:</form:label>
                        <form:checkbox path="budget" class="form-control" id="budget"/>
                    </div>

                    <div class="form-group">

                        <label>Select speciality:

                            <select name="specialityId">
                                <c:forEach items="${specialityList}" var="i">
                                    <option value="${i.id}">${i.name}</option>
                                </c:forEach>
                            </select>

                        </label>

                    </div>

                    <div class="sign-up button">
                        <input type="submit" value="Sign up" />
                    </div>

                </div>
        </div>
    </div>

</form:form>
</body>
</html>
