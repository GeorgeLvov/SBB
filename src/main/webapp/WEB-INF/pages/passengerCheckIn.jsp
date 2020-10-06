<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>


<html>
<head>
    <title>SBB CFF FFS</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://use.fontawesome.com/465a5a8cc2.js"></script>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-dark">
    <a class="navbar-brand" href="<c:url value="/"/>" style="color: white">
        <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
        SBB CFF FFS
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>

<div class="container-fluid">
    <div class="row" style="height: 40px">
    </div>

    <div class="row">
        <div class="col-md-4">
        </div>

        <div class="col-md-4 bg-light" style="border-radius: 2%">

            <form:form method="POST" modelAttribute="passForm" class="form-signin">

                <h2 class="form-signin-heading" style="text-align:center; padding-top: 15px; padding-bottom: 15px">
                    Check In</h2>

                <spring:bind path="firstName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="firstName" class="form-control" placeholder="Enter your first name"
                                    autofocus="true"></form:input>
                        <form:errors path="firstName" cssStyle="color: red; font-size: 14px"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="lastName">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="lastName" class="form-control"
                                    placeholder="Enter your last name"></form:input>
                        <form:errors path="lastName" cssStyle="color: red; font-size: 14px"></form:errors>
                    </div>
                </spring:bind>

                <spring:bind path="birthDate">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="date" path="birthDate" class="form-control"
                                    placeholder="Birthdate"></form:input>
                        <form:errors path="birthDate" cssStyle="color: red; font-size: 14px"></form:errors>
                    </div>
                </spring:bind>

                <button class="btn btn-lg btn-success btn-block" type="submit" style="margin-top: 35px">Sign Up</button>
            </form:form>
        </div>

        <div class="col-md-4">
        </div>

    </div>
</div>

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