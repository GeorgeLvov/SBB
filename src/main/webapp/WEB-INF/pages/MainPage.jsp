<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
    <title>SBB CFF FFS</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/css/forMainPages.css"/>" />
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    
</head>

<body>

<nav class="navbar navbar-expand-md navbar-light fixed-top bg-white">

    <a class="navbar-brand" href="<c:url value="/"/>">
        <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Swiss Federal Railways
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="#exampleModalCenter" data-toggle="modal" data-target="#exampleModalCenter">
                    <i class="fa fa-clock" aria-hidden="true"></i>
                    Timetable
                </a>
            </li>

            <security:authorize access="hasRole('USER') or hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/alltickets"/>">
                        <i class="fas fa-ticket-alt"></i>
                            My tickets
                    </a>
                </li>
            </security:authorize>

            <security:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/admin/management"/>" >
                        <i class="fas fa-cog"></i>
                        Management
                    </a>
                </li>
            </security:authorize>

        </ul>

        <security:authorize access="isAnonymous()">
            <a class="nav-link" href="<c:url value="/success"/>">
                <i class="fa fa-user" aria-hidden="true"></i>
                Log in
            </a>
        </security:authorize>


        <security:authorize access="hasRole('USER') or hasRole('ADMIN')">
            <a class="nav-link" href="<c:url value="/logout"/>"><i class="fa fa-user" aria-hidden="true"></i>
                Log out
            </a>
        </security:authorize>
    </div>
</nav>

<%--Timetable --%>

<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Timetable</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <c:url value="/timetable" var="var"/>
                <form action="${var}" method="GET">
                    <div class="form-group">
                        <select class="form-control" name="stationId" id="exampleSelectSt" required style="margin-top: 20px;">
                            <option value="" disabled selected>Select station</option>
                            <c:forEach var="station" items="${stations}">
                                <option value="${station.id}">${station.title}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="modal-footer" style="border-top: 0 none;">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" style="margin-top: 30px;">Close</button>
                        <button type="submit" class="btn btn-danger" style="margin-top: 30px;">Search</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>

<div class="container">
    <div class="row" style="height: 80px">
    </div>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-10">

            <c:url value="/schedule" var="varT"/>
            <form action="${varT}" method="GET" style="padding-top: 40px">
                <div class="row">
                    <div class="col-6">
                        <div class="form-group">
                            <select class="form-control" name="stationFrom" id="exampleSelect" required>
                            <option value="" disabled selected>From</option>
                                <c:forEach var="station" items="${stations}">
                                    <option value="${station.id}">${station.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="form-group">
                            <select class="form-control" name="stationTo" id="examplSelect" required>
                                <option value="" disabled selected>To</option>
                                <c:forEach var="station" items="${stations}">
                                    <option value="${station.id}">${station.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <br>

                <div class="row">
                    <div class="col-3">
                        <div class="form-group">
                            <input class="form-control" name="dateFrom" type="datetime-local" value="${currentDateTime}"
                                   id="exdatetimelo">
                        </div>
                    </div>

                    <div class="col-3">
                        <div class="form-group">
                            <input class="form-control" name="dateTo" type="datetime-local" value="${currentDateTime}"
                                   id="exdatetimel">
                        </div>
                    </div>
                    <div class="col-3">
                        <button type="submit" class="btn btn-danger" style="width: 100px;">Search
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-1"></div>
    </div>
</div>
<footer class="fixed-bottom page-footer" style="background-color: #F4F6F6 ">
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


