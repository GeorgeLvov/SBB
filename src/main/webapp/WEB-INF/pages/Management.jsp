<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SBB CFF FFS</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/css/management.css"/>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>

<nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">

    <a class="navbar-brand" href="<c:url value="/"/>">
        <img src="${pageContext.request.contextPath}/res/img/sbbBadge.png" width="30" height="30"
             class="d-inline-block align-top" alt="">
        Swiss Federal Railways &#8592;
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/admin/addemployee">
                    <i class="fas fa-user-friends"></i>
                    Add new employee
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    Log out
                </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
        <div class="col-lg"></div>
        <div class="col-5">
            <div class="list-group">
                <a href="${pageContext.request.contextPath}/admin/crud"
                   class="list-group-item list-group-item-action list-group-item">
                    <i class="far fa-plus-square"></i>
                    Add train / station
                    <span class="arrow">
                    <i class="fas fa-long-arrow-alt-right"></i>
                </span>
                </a>

                <a href="${pageContext.request.contextPath}/admin/trainselect"
                   class="list-group-item list-group-item-action list-group-item">
                    <i class="far fa-calendar-alt"></i>
                    Assign new trip
                    <span class="arrow">
                    <i class="fas fa-long-arrow-alt-right"></i>
                </span>
                </a>

                <a href="${pageContext.request.contextPath}/admin/trains"
                   class="list-group-item list-group-item-action list-group-item">
                    <i class="fas fa-subway"></i>
                    Show all trains
                    <span class="arrow">
                    <i class="fas fa-long-arrow-alt-right"></i>
                </span>
                </a>

                <a href="${pageContext.request.contextPath}/admin/stations"
                   class="list-group-item list-group-item-action list-group-item">
                    <i class="fas fa-map-pin"></i>
                    Show all stations
                    <span class="arrow">
                    <i class="fas fa-long-arrow-alt-right"></i>
                </span>
                </a>

                <a href="${pageContext.request.contextPath}/admin/allTrips"
                   class="list-group-item list-group-item-action list-group-item">
                    <i class="fas fa-list"></i>
                    Show all trips
                    <span class="arrow">
                    <i class="fas fa-long-arrow-alt-right"></i>
                </span>
                </a>
            </div>

        </div>
        <div class="col-lg"></div>
    </div>

</div>


<footer class="fixed-bottom page-footer" style="background-color: #F4F6F6 ">
    <p class="text-center footer-text">&copy; Swiss Federal Railways, 2020 </p>
</footer>

<%--<c:if test="${message != null}">
    <div class="alert alert-success alert-dismissible fade show col-sm-4 offset-sm-4" style="text-align: center; margin-top: 70px">
        <strong>Success!</strong>
        <p>${message}</p>
        <p><strong>username</strong>: ${username}</p>
        <p><strong>password</strong>: ${password}</p>
        <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
</c:if>

<c:if test="${successMessage != null}">
    <div class="alert alert-success alert-dismissible fade show col-sm-6 offset-sm-3" style="text-align: center; margin-top: 70px">
        <strong>Success!</strong>
        <p>Trip was created! <br> Show it in dropdown tab "Show" - "Show all trips"</p>
        <button type="button" class="close" data-dismiss="alert">&times;</button>
    </div>
</c:if>--%>

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

<%--    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/crud"/>"> Add train | station </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/trainselect"/>"> Set trip for train </a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Show
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/admin/stations"/>">Show all stations</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<c:url value="/admin/trains"/>">Show all trains</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<c:url value="/admin/allTrips"/>">Show all trips</a>

                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/addemployee"/>">
                    Add new employee </a>
            </li>
        </ul>

        <a class="nav-link" href="/logout">
            <i class="fa fa-user" aria-hidden="true"></i>
            Log out
        </a>
    </div>--%>