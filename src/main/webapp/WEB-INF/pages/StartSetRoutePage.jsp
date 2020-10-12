<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>SBB: SetRoute</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <script src="https://use.fontawesome.com/465a5a8cc2.js"></script>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-dark">

    <a class="navbar-brand" href="<c:url value="/admin"/>" style="color: white">
        <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
        SBB CFF FFS
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/crud"/>" style="color: white">Add train | station</a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/admin/trainselect" style="color: white">Set trip for train</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: white">
                    Show
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/admin/stations"/>">Show all stations</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<c:url value="/admin/trains"/>">Show all trains</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Show passengers</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/addemployee"/>" style="color: white"> Add new employee </a>
            </li>
        </ul>

        <a class="nav-link" href="<c:url value="/"/>" style="color: white">
            <i class="fa fa-user" aria-hidden="true"></i>
            Log out
        </a>
    </div>
</nav>





<div class="container-fluid">
    <div class="row" style="height: 60px"></div>
    <div class="row">
        <div class="col-2"></div>
        <div class="col-md-8 bg-light" style="border-radius: 2%">
            <form:form method="POST" modelAttribute="routeDTO" class="form-signin">
                <div class="row" style="height: 40px">
                </div>
                <div class="row">
                    <div class="col-5">
                        <spring:bind path="trainName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="trainselect">Train:</label>
                                <form:select class="form-control" path="trainName" varStatus="tagStatus" multiple="0"
                                             id="trainselect">
                                    <form:option value="" label="Select"/>
                                    <form:options items="${trainsList}" itemValue="trainName" itemLabel="trainName"/>
                                </form:select>
                                <form:errors path="trainName" cssStyle="color: red; font-size: 14px"></form:errors>
                            </div>
                        </spring:bind>
                    </div>
                </div>
                <div class="row">
                    <div class="col-5">
                        <spring:bind path="departureStationName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="depStation">Departure station:</label>
                                <form:select class="form-control" path="departureStationName" varStatus="tagStatus"
                                             multiple="0" id="depStation">
                                    <form:option value="" label="From"/>
                                    <form:options items="${stationList}" itemValue="title" itemLabel="title"/>
                                </form:select>
                                <form:errors path="departureStationName"
                                             cssStyle="color: red; font-size: 14px"></form:errors>
                            </div>
                        </spring:bind>
                    </div>
                </div>
                <div class="row">
                    <div class="col-4">
                        <spring:bind path="departureDate">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="depTime">Departure time:</label>
                                <form:input type="datetime-local" path="departureDate" class="form-control"
                                            placeholder="" id="depTime"></form:input>
                                <form:errors path="departureDate" cssStyle="color: red; font-size: 14px"></form:errors>
                            </div>
                        </spring:bind>
                    </div>
                    <div class="col-4">
                        <spring:bind path="declaredArrivalDate">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <label for="arrTime">End time of the route:</label>
                                <form:input type="datetime-local" path="declaredArrivalDate" class="form-control"
                                            placeholder="" id="arrTime"></form:input>
                                <form:errors path="declaredArrivalDate"
                                             cssStyle="color: red; font-size: 14px"></form:errors>
                            </div>
                        </spring:bind>
                    </div>
                </div>
                <button type="submit" class="btn btn-danger" style="margin-top: 40px;">Create route</button>
            </form:form>
        </div>
    </div>
</div>
<%--<footer style="text-align: center; margin-top: 120px;">
    Swiss Federal Railways, 2020
</footer>--%>

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