<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>SBB CFF FFS: Schedule</title>
    <link rel="shortcut icon" href="<c:url value="/res/img/sbbBadge.png"/>" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/css/navbar.css"/>"/>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-md navbar-light fixed-top bg-light">
    <a class="navbar-brand" href="<c:url value="/"/>">
        <img src="<c:url value="/res/img/sbbBadge.png"/>" width="30" height="30" class="d-inline-block align-top"
             alt="">
        Swiss Federal Railways &#8592;
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <security:authorize access="hasRole('USER') or hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/alltickets"/>">
                        <i class="fas fa-ticket-alt"></i>
                        My tickets
                    </a>
                </li>
            </security:authorize>
        </ul>

        <security:authorize access="isAnonymous()">
            <a class="nav-link" href="<c:url value="/login"/>">
                <i class="fa fa-user" aria-hidden="true"></i>
                Log in
            </a>
        </security:authorize>

        <security:authorize access="hasRole('USER') or hasRole('ADMIN')">
            <a class="nav-link" href="<c:url value="/logout"/>">
                <i class="fa fa-user" aria-hidden="true"></i>
                Log out
            </a>
        </security:authorize>

    </div>
</nav>

<c:choose>

    <c:when test="${empty scheduleDTOList}">
        <div class="container-fluid">
            <div class="row" style="height: 100px">
                <div class="col-2"></div>
                <div class="col-8" style="text-align:center;">
                    <h1 style="font-size: 50px; margin-top: 100px">No trains available!</h1>
                </div>
                <div class="col-2"></div>
            </div>
        </div>
    </c:when>

    <c:otherwise>
        <div class="container">
            <div class="row" style="height: 50px"></div>
        </div>

        <div class="row" style="margin-top:30px">
            <div class="col-2"></div>
            <div class="col-8" style="text-align:center;">
                <c:if test="${param.err != null}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>Failed!</strong>
                        <c:choose>
                            <c:when test="${errors.size() > 1}">
                                Please enter correct personal data.
                            </c:when>
                            <c:otherwise>
                                Such passenger is already checked in for this trip.
                            </c:otherwise>
                        </c:choose>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>
                <h2><c:out value="${scheduleDTOList[0].stationFromDTO.title}"/>
                    &#8594;
                    <c:out value="${scheduleDTOList[0].stationToDTO.title}"/></h2>
            </div>
            <div class="col-2"></div>
        </div>

        <div class="container mt-4 p-md-4 col-12 rounded-container">
            <table class="table" style="text-align: center">
                <tr>
                    <th scope="col">Train</th>
                    <th scope="col">From</th>
                    <th scope="col">To</th>
                    <th scope="col">Departure</th>
                    <th scope="col">Arrival</th>
                    <th scope="col">Trip Info</th>
                    <th scope="col">Tickets left</th>
                    <th scope="col">Buy Ticket</th>
                </tr>

                <tbody>
                <c:forEach var="scheduleDTO" items="${scheduleDTOList}" varStatus="vs">
                    <tr>
                        <th scope="row">${scheduleDTO.tripDTO.trainDTO.trainName}</th>
                        <td>${scheduleDTO.stationFromDTO.title}</td>
                        <td>${scheduleDTO.stationToDTO.title}</td>
                        <fmt:setLocale value="en_US" scope="session"/>
                        <td>
                            <strong><fmt:formatDate value="${scheduleDTO.departureTime}" pattern="HH:mm"/></strong>
                            <br>
                            <fmt:formatDate value="${scheduleDTO.departureTime}" pattern="E, dd.MM.yyyy"/>
                        </td>
                        <td>
                            <strong><fmt:formatDate value="${scheduleDTO.arrivalTime}" pattern="HH:mm"/></strong>
                            <br>
                            <fmt:formatDate value="${scheduleDTO.arrivalTime}" pattern="E, dd.MM.yyyy"/>
                        </td>
                        <td><!-- Button for Trip info modal -->
                            <button type="button" class="btn btn-info" data-toggle="modal"
                                    data-target="#exampleModal${vs.index}" id="viewDetailButton${vs.index}">
                                Show info
                            </button>
                            <!-- Trip Info Modal -->
                            <div class="modal fade" id="exampleModal${vs.index}" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header" style="border-bottom: 0 none;">
                                            <h5 class="modal-title" id="exampleModalLabel">
                                                Trip info
                                            </h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <table class="table">
                                                <thead class="thead-success">
                                                <tr>
                                                    <th scope="col">From</th>
                                                    <th scope="col">To</th>
                                                    <th scope="col">Departure</th>
                                                    <th scope="col">Arrival</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c1:forEach var="segment" items="${scheduleDTO.scheduleDTOList}">
                                                    <c:choose>
                                                        <c:when test="${(segment.departureTime.equals
                                                        (scheduleDTO.departureTime))
                                                        && (segment.arrivalTime.equals(scheduleDTO.arrivalTime))}">
                                                            <tr>
                                                                <td><strong>${segment.stationFromDTO.title}</strong></td>
                                                                <td><strong>${segment.stationToDTO.title}</strong></td>
                                                                <td><strong><fmt:formatDate
                                                                        value="${segment.departureTime}"
                                                                        pattern="HH:mm dd.MM"/></strong></td>
                                                                <td><strong><fmt:formatDate
                                                                        value="${segment.arrivalTime}"
                                                                        pattern="HH:mm dd.MM"/></strong></td>
                                                            </tr>

                                                        </c:when>
                                                        <c:when test="${segment.departureTime
                                                        .equals(scheduleDTO.departureTime)}">
                                                            <tr>
                                                                <td><strong>${segment.stationFromDTO.title}</strong></td>
                                                                <td>${segment.stationToDTO.title}</td>
                                                                <td><strong><fmt:formatDate
                                                                        value="${segment.departureTime}"
                                                                        pattern="HH:mm dd.MM"/></strong></td>
                                                                <td><fmt:formatDate value="${segment.arrivalTime}"
                                                                                    pattern="HH:mm dd.MM"/></td>
                                                            </tr>
                                                        </c:when>
                                                        <c:when test="${segment.arrivalTime
                                                        .equals(scheduleDTO.arrivalTime)}">
                                                            <tr>
                                                                <td>${segment.stationFromDTO.title}</td>
                                                                <td><b>${segment.stationToDTO.title}</b></td>
                                                                <td><fmt:formatDate value="${segment.departureTime}"
                                                                                    pattern="HH:mm dd.MM"/></td>
                                                                <td><strong><fmt:formatDate
                                                                        value="${segment.arrivalTime}"
                                                                        pattern="HH:mm dd.MM"/></strong></td>
                                                            </tr>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <tr>
                                                                <td>${segment.stationFromDTO.title}</td>
                                                                <td>${segment.stationToDTO.title}</td>
                                                                <td><fmt:formatDate value="${segment.departureTime}" pattern="HH:mm dd.MM"/></td>
                                                                <td><fmt:formatDate value="${segment.arrivalTime}" pattern="HH:mm dd.MM"/></td>
                                                            </tr>
                                                        </c:otherwise>
                                                    </c:choose>

                                                </c1:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="modal-footer" style="border-top: 0 none;">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                                <%-- End Trip Info Modal--%>
                        </td>
                        <td>
                            <c1:choose>
                                <c1:when test="${scheduleDTO.availableOnTime}">
                                    ${scheduleDTO.freePlacesCount}
                                </c1:when>
                                <c1:otherwise>
                                    -
                                </c1:otherwise>
                            </c1:choose>
                        </td>
                        <td>
                            <c1:choose>
                                <c1:when test="${(scheduleDTO.freePlacesCount == 0) || (!scheduleDTO.availableOnTime)}">
                                    <button type="button" class="btn btn-secondary btn" disabled>Buy ticket</button>
                                </c1:when>
                                <c1:otherwise>
                                    <c:choose>
                                        <c:when test="${empty pageContext.request.userPrincipal}">
                                            <a class="btn btn-danger" href="<c:url value="/login"/>" role="button">Buy ticket</a>
                                        </c:when>
                                        <c:otherwise>
                                            <!-- Button for modal check in form -->
                                            <button type="button" class="btn btn-danger" data-toggle="modal"
                                                    data-target="#m${vs.index}"
                                            onclick="setIndex(${vs.index})">Buy ticket</button>
                                        </c:otherwise>
                                    </c:choose>
                                    <%-- Modal for check in form--%>
                                    <div class="modal fade" id="m${vs.index}" tabindex="-1" role="dialog"
                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <div class="navbar-brand">
                                                        <img src="<c:url value="/res/img/sbbBadge.png"/>"
                                                             width="30" height="30" class="d-inline-block align-top"
                                                             alt="">
                                                        Swiss Federal Railways
                                                    </div>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                            aria-label="Close" onclick="closeMod()">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <form:form name="checkInForm${vs.index}" method="POST"
                                                               action="/checkin"
                                                               modelAttribute="ticket" class="form-signin"
                                                               onsubmit="return validationCheckInForm()">
                                                        <h2 class="form-signin-heading"
                                                            style="padding-bottom: 15px">
                                                            Check In</h2>

                                                        <input type="hidden" name="timeSearchFrom"
                                                               value="${param.dateFrom}"/>
                                                        <input type="hidden" name="timeSearchTo" value="${param.dateTo}"/>
                                                        <form:hidden path="trainId"
                                                                     value="${scheduleDTO.tripDTO.trainDTO.id}"/>
                                                        <form:hidden path="tripId" value="${scheduleDTO.tripDTO.id}"/>
                                                        <form:hidden path="departureTime"
                                                                     value="${scheduleDTO.departureTime}"/>
                                                        <form:hidden path="arrivalTime"
                                                                     value="${scheduleDTO.arrivalTime}"/>
                                                        <form:hidden path="stationFromId" value="${param.stationFrom}"/>
                                                        <form:hidden path="stationToId" value="${param.stationTo}"/>

                                                        <spring:bind path="passengerDTO.firstName">
                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                <label id="fNLbl${vs.index}" for="firstN${vs.index}">
                                                                    Passenger name:</label>
                                                                <form:input type="text" path="passengerDTO.firstName"
                                                                            class="form-control"
                                                                            placeholder="Enter name" id="firstN${vs.index}"
                                                                            onchange="undoCheckInInputStyle('firstN')"></form:input>
                                                            </div>
                                                        </spring:bind>

                                                        <spring:bind path="passengerDTO.lastName">
                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                <label id="lNLbl${vs.index}" for="lastN${vs.index}">
                                                                    Passenger surname:</label>
                                                                <form:input type="text" path="passengerDTO.lastName"
                                                                            class="form-control"
                                                                            placeholder="Enter surname"
                                                                            id="lastN${vs.index}"
                                                                            onchange="undoCheckInInputStyle('lastN')"></form:input>
                                                            </div>
                                                        </spring:bind>

                                                        <spring:bind path="passengerDTO.birthDate">
                                                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                                                <label id="bDLbl${vs.index}" for="BD${vs.index}">
                                                                    Date of birth:
                                                                </label>
                                                                <form:input type="date" path="passengerDTO.birthDate"
                                                                            class="form-control"
                                                                            placeholder="Birthdate"
                                                                            id="BD${vs.index}"
                                                                            onchange="undoCheckInInputStyle('BD')"></form:input>
                                                            </div>
                                                        </spring:bind>
                                                        <button class="btn btn-lg btn-success btn-block" type="submit"
                                                                style="margin-top: 35px">Check In
                                                        </button>
                                                    </form:form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <%-- End modal for check in form--%>
                                </c1:otherwise>
                            </c1:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>

<footer class="fixed-bottom page-footer" style="background-color:#F2F3F4">
    <p class="text-center footer-text">&copy; Swiss Federal Railways, 2020 </p>
</footer>

<script src="<c:url value="/res/js/validation/commonFormValidation.js"/>"></script>
<script src="<c:url value="/res/js/validation/checkInFormValidation.js"/>"></script>
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

