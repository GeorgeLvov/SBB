<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>SBB: Passengers</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/css/navbar.css"/>" />
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <a class="navbar-brand" href="<c:url value="/"/>">
        <img src="${pageContext.request.contextPath}/res/img/sbbBadge.png" width="30" height="30"
             class="d-inline-block align-top" alt="">
        Swiss Federal Railways
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/management"/>" >
                    <i class="fas fa-cog"></i>
                    Management
                </a>
            </li>
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
                    <a class="dropdown-item" href="<c:url value="/admin/trainsandstations"/>">
                        Show all trains | stations</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<c:url value="/admin/allTrips"/>">Show all trips</a>
                </div>
            </li>
        </ul>

        <a class="nav-link" href="<c:url value="/logout"/>">
            <i class="fa fa-user" aria-hidden="true"></i>
            Log out
        </a>

    </div>
</nav>

<c:choose>
    <c:when test="${empty passengers}">
        <div class="container-fluid">
            <div class="row" style="height: 100px">
                <div class="col-2"></div>
                <div class="col-8"  style="text-align:center;" >
                    <h1 style="font-size: 50px; margin-top: 100px">No passengers registered for the trip!</h1>
                </div>
                <div class="col-2"></div>
            </div>
        </div>
        <footer class="fixed-bottom page-footer" style="background-color: #F4F6F6 ">
            <p class="text-center footer-text">&copy; Swiss Federal Railways, 2020 </p>
        </footer>
    </c:when>
    <c:otherwise>
        <div class="container mt-4 p-md-3 mb-5 col-12 rounded-container">
            <fmt:setLocale value="en_US" scope="session"/>
            <div style="text-align: center; margin-bottom: 35px">
                <h3>
                        ${currentTrip.departureStationDTO.title} &#8594; ${currentTrip.arrivalStationDTO.title}
                    <h5><fmt:formatDate value="${currentTrip.departureTime}" pattern="HH:mm dd.MM.yy"/> -
                        <fmt:formatDate value="${currentTrip.arrivalTime}" pattern="HH:mm dd.MM.yy"/></h5>
                </h3>
            </div>

            <table class="table" style="text-align: center">
                <tr>
                    <th scope="col">Train</th>
                    <th scope="col">Name</th>
                    <th scope="col">Surname</th>
                    <th scope="col">Date of birth</th>
                    <th scope="col">From</th>
                    <th scope="col">To</th>
                    <th scope="col">Departure</th>
                    <th scope="col">Arrival</th>
                </tr>

                <tbody>

                <c:forEach var="passenger" items="${passengers}" varStatus="vs">

                    <tr>
                        <th scope="row">${passenger.trainName}</th>
                        <td>${passenger.firstName}</td>
                        <td>${passenger.lastName}</td>
                        <td> <fmt:formatDate value="${passenger.birthDate}" pattern="dd-MM-yyyy"/></td>
                        <td>${passenger.stationFrom}</td>
                        <td>${passenger.stationTo}</td>
                        <td>
                            <strong><fmt:formatDate value="${passenger.departureTime}" pattern="HH:mm"/></strong>
                            <br>
                            <fmt:formatDate value="${passenger.departureTime}" pattern="E, dd.MM.yyyy"/>
                        </td>
                        <td>
                            <strong><fmt:formatDate value="${passenger.arrivalTime}" pattern="HH:mm"/></strong>
                            <br>
                            <fmt:formatDate value="${passenger.arrivalTime}" pattern="E, dd.MM.yyyy"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>

<footer class="fixed-bottom page-footer" style="background-color: #F4F6F6; padding-top: -50px">
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
