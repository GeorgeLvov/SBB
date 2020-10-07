<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
<head>
    <title>SBB: My Tickets</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://use.fontawesome.com/465a5a8cc2.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">

    <a class="navbar-brand" href="<c:url value="/"/>">
        <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
        SBB CFF FFS
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>


    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">

            <li class="nav-item">
                <a class="nav-link" href="#">Timetable</a>
            </li>
        </ul>

        <security:authorize access="hasRole('USER') or hasRole('ADMIN')">
            <a href="<c:url value="/logout"/>" style="color: white"><i class="fa fa-user" aria-hidden="true"></i>
                Log out
            </a>
        </security:authorize>
    </div>
</nav>




<div class="container mt-4 p-md-4 col-12 rounded-container">
    <div class="container-fluid">
        <div class="row" style="height: 100px">
            <div class="col-5"></div>
            <div class="col-4"><h1 style="padding-top: 25px">Tickets</h1></div>
            <div class="col-5"></div>

        </div>
    </div>
    <table class="table" style="text-align: center">
        <thead class="thead-light">
        <tr>
            <th scope="col">Trains</th>
            <th scope="col">From</th>
            <th scope="col">To</th>
            <th scope="col">Departure</th>
            <th scope="col">Arrival</th>
            <th scope="col">Passenger</th>
            <th scope="col"></th>
            <th scope="col">Download e-ticket</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ticketInfo" items="${ticketInfos}" varStatus="vs">
        <tbody>
        <tr>
            <th scope="row">${ticketInfo.trainName}</th>
            <td>${ticketInfo.statFromTitle}</td>
            <td>${ticketInfo.statToTitle}</td>
            <fmt:setLocale value="en_US" scope="session"/>
            <td>
                <strong><fmt:formatDate value="${ticketInfo.departureTime}" pattern="HH:mm"/></strong>
                <br>
                <fmt:formatDate value="${ticketInfo.departureTime}" pattern="E, dd.MM.yyyy"/>
            </td>
            <td>
                <strong><fmt:formatDate value="${ticketInfo.arrivalTime}" pattern="HH:mm"/></strong>
                <br>
                <fmt:formatDate value="${ticketInfo.arrivalTime}" pattern="E, dd.MM.yyyy"/>
            </td>

            <td><!-- Button trigger modal -->
                <button type="button" class="btn btn-info" data-toggle="modal"
                        data-target="#exampleModal${vs.index}" id="viewDetailButton${vs.index}">
                    Show data
                </button>

                <!-- Modal -->
                <div class="modal fade" id="exampleModal${vs.index}" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">
                                    Passenger
                                </h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                    <%--Modalbody--%>
                                <table class="table">
                                    <thead class="thead-success">
                                    <tr>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>Name</td>
                                        <td>${ticketInfo.firstName}</td>
                                    </tr>
                                    <tr>
                                        <td>Surname</td>
                                        <td>${ticketInfo.lastName}</td>
                                    </tr>
                                    <tr>
                                        <td>Date of Birth</td>
                                        <td>${ticketInfo.birthDate}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </td>

            <td>
                <c:choose>
                    <c:when test="${ticketInfo.valid == true}">
                       <span class="badge badge-success">valid</span>
                    </c:when>
                    <c:otherwise>
                       <span class="badge badge-secondary">invalid</span>
                    </c:otherwise>
                </c:choose>
            </td>
            
            <td><a href="/export/${ticketInfo.ticketId}">Download</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
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
