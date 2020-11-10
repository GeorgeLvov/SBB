<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<html>
<head>
    <title>SBB CFF FFS</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/css/navbar.css"/>"/>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <style>
       label {
           font-size: 14px;
       }
    </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <a class="navbar-brand" href="<c:url value="/"/>">
        <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Swiss Federal Railways
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">
            <security:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/admin/management">
                        <i class="fas fa-cog"></i>
                        Management &#8592;
                    </a>
                </li>
            </security:authorize>
        </ul>

        <security:authorize access="isAnonymous()">
            <a class="nav-link" href="<c:url value="/login"/>">
                <i class="fa fa-user" aria-hidden="true"></i>
                Back to Sign In
            </a>
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
        <a class="nav-link" href="${pageContext.request.contextPath}/logout">
            <i class="fa fa-user" aria-hidden="true"></i>
            Log out
        </a>
        </security:authorize>
    </div>
</nav>

<div class="container-fluid">
    <div class="row" style="height: 40px">
    </div>

    <div class="row">
        <div class="col-md-4">
        </div>

        <div class="col-md-4 bg-light" style="border-radius: 2%">
            <form:form name="regForm" method="POST" modelAttribute="userForm" class="form-signin"
            onsubmit="return validateRegistrationForm()">

                <security:authorize access="isAnonymous()">
                    <h2 class="form-signin-heading" style="text-align:center; padding-top: 15px; padding-bottom: 5px">
                        Register</h2>
                    <p style="text-align: center; color: darkgray; padding-bottom: 10px;">It only takes a minute and
                        then
                        you will be able to view purchased tickets and download them in pdf.</p>
                </security:authorize>

                <security:authorize access="hasRole('ADMIN')">
                    <h2 class="form-signin-heading" style="text-align:center; padding-top: 15px; padding-bottom: 15px">
                        Add new employee account</h2>
                </security:authorize>

                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label id="emailLbl" for="emailInp">Email: </label>
                        <form:input type="text" path="username" class="form-control" placeholder="Email"
                                    autofocus="true" id="emailInp"
                                    onchange="undoUserInputStyle('email')"></form:input>
                        <form:errors path="username" cssStyle="display: none"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label id="passLbl" for="passInp">Password: </label>
                        <form:input type="password" path="password" class="form-control"
                                    placeholder="Password" id="passInp"
                                    onchange="undoUserInputStyle('pass')"></form:input>
                        <form:errors path="password" cssStyle="display: none"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="confirmPassword">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <label id="confLbl" for="confInp">Confirm password: </label>
                        <form:input type="password" path="confirmPassword" class="form-control"
                                    placeholder="Confirm your password" id="confInp"
                                    onchange="undoUserInputStyle('conf')"></form:input>
                        <form:errors path="confirmPassword" cssStyle="display: none"></form:errors>
                    </div>
                </spring:bind>

                <button class="btn btn-lg btn-success btn-block" type="submit" style="margin-top: 35px">
                    <security:authorize access="isAnonymous()">
                        Sign Up
                    </security:authorize>
                    <security:authorize access="hasRole('ADMIN')">
                        Create account
                    </security:authorize>
                </button>
            </form:form>
        </div>
        <div class="col-md-4">
        </div>

    </div>
</div>

<footer class="fixed-bottom page-footer" style="background-color:#F2F3F4">
    <p class="text-center footer-text">&copy; Swiss Federal Railways, 2020 </p>
</footer>

<script src="<c:url value="/res/js/validation/commonFormValidation.js"/>"></script>
<script src="<c:url value="/res/js/validation/userFormValidation.js"/>"></script>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>