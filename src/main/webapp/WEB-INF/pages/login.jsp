<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>SBB CFF FFS</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://use.fontawesome.com/465a5a8cc2.js"></script>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<c:url value="/"/>">
        <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Swiss Federal Railways &#8592;
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>


<div class="container-fluid">
    <div class="row" style="height: 30px">
    </div>

    <div class="row">
        <div class="col-md-4">
        </div>

        <div class="col-md-4 bg-light" style="border-radius: 2%">
            <h2 style="text-align:center; padding-top: 15px; padding-bottom: 15px">Sign In</h2>
            <c:url var="var" value="/login"/>
            <form action="${var}" method="post">
                <security:csrfInput/>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert" style="font-size: 15px">
                        <strong>Failed to log in.</strong>
                        <br>
                        Please make sure that you have entered your <strong>login</strong> and
                        <strong>password </strong>
                        correctly.
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <p>${logoutmessage}</p>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                </c:if>
                <div class="form-group">
                    <label for="username"></label>
                    <input type="text" name="username" class="form-control" id="username"
                           aria-describedby="emailHelp"
                           placeholder="Enter your login">
                </div>
                <div class="form-group">
                    <label for="password"></label>
                    <input type="password" name="password" class="form-control" id="password"
                           placeholder="Enter your password">
                </div>
                <div class="form-check">
                    <input name="remember" id="remember-me" type="checkbox" class="form-check-input"/>
                    <label class="form-check-label" for="remember-me">Remember me</label>
                </div>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <button type="submit" class="btn btn-lg btn-success btn-block" style="margin-top: 15px">Sign In</button>
            </form>
            <security:authorize access="isAnonymous()">
            <h5 style=" padding-top: 35px; padding-bottom: 10px">Don't have an account?</h5>
            <a class="btn btn-info" href="${pageContext.request.contextPath}/registration" role="button"
               style="width: 210px; padding-bottom: 10px; margin-bottom: 20px;">Create it here!</a>
        </div>
        </security:authorize>

        <div class="col-md-4">
        </div>

    </div>
</div>

<footer class="fixed-bottom page-footer" style="background-color:#F2F3F4">
    <p class="text-center footer-text">&copy; Swiss Federal Railways, 2020 </p>
</footer>

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


