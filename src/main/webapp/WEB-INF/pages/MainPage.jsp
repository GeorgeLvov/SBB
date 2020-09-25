<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %><html>
<html>
<head>
    <title>SBB CFF FFS</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/forMainPages.css"/>" />
    <script src="https://use.fontawesome.com/465a5a8cc2.js"></script>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-dark" style="color: white">

    <a class="navbar-brand" href="<c:url value="/"/>" style="color: white">
        <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
        SBB CFF FFS
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="#" style="color: white">Timetable</a>
            </li>

            <security:authorize access="hasRole('USER')">
                <li class="nav-item">
                    <a class="nav-link" href="#" style="color: white">My tickets</a>
                </li>
            </security:authorize>
            <security:csrfInput/>
        </ul>

        <security:authorize access="isAnonymous()">
        <a class="nav-link" href="<c:url value="/user"/>" style="color: white">
            <i class="fa fa-user" aria-hidden="true"></i>
            Log in
        </a>
        </security:authorize>
        <security:csrfInput/>
        <security:authorize access="hasRole('USER')">
            <a href="<c:url value="/logout"/>" style="color: white">
                <i class="fa fa-user" aria-hidden="true"></i>
                Log out
            </a>
        </security:authorize>
        <security:csrfInput/>
    </div>

</nav>

<div class="container">
    <div class="row" style="height: 50px">
    </div>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
            <form action="" style="padding-top: 40px">
                <div class="form-group row">
                    <div class="col">
                        <input type="text" class="form-control" placeholder="From">
                    </div>
                    <div class="col">
                        <input type="text" class="form-control" placeholder="To">
                    </div>
                </div>
                <br>
                <div class="form-group row">
                    <div class="col-2">
                        <input type="text" class="form-control" placeholder="Date">
                    </div>
                    <div class="col-2">
                        <input type="text" class="form-control" placeholder="Time">
                    </div>
                    <div class="col-2">
                        <input type="text" class="form-control" placeholder="Time">
                    </div>
                    <button type="button" class="btn btn-danger">Search</button>
                </div>
            </form>
        </div>
        <div class="col-1"></div>
    </div>
</div>

<a href="<c:url value="/admin"/>">adminPage</a>

<h3> Hello ${pageContext.request.userPrincipal.name}, </h3>
<h4>Welcome to SBB! </h4>

<a href="<c:url value='/logout' />">Click here to logout</a>


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
