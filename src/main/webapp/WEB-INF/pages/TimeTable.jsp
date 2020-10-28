<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
<head>
    <title>SBB: Timetable</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://use.fontawesome.com/465a5a8cc2.js"></script>
    <script>
        function showOrHideDeparture() {
            var divDeparture = document.getElementById("myDIV");
            var divArrival = document.getElementById("myDIV2");
            showHide(divDeparture, divArrival);
        }
        function showOrHideArrival(){
            var divArrival = document.getElementById("myDIV2")
            var divDeparture = document.getElementById("myDIV")
            showHide(divArrival, divDeparture);

        }
        function showHide(x, y) {
            if (x.style.display === "none" && y.style.display === "block") {
                x.style.display = "block";
                y.style.display = "none"
            }
        }
    </script>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">

    <a class="navbar-brand" href="<c:url value="/"/>">
        <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
        SBB CFF FFS
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav mr-auto">

            <security:authorize access="hasRole('ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="/admin">Management</a>
                </li>
            </security:authorize>

            <security:authorize access="hasRole('USER')">
                <li class="nav-item">
                    <a class="nav-link" href="#">My tickets</a>
                </li>
            </security:authorize>
        </ul>

        <security:authorize access="isAnonymous()">
            <a class="nav-link" href="<c:url value="/success"/>" style="color: white">
                <i class="fa fa-user" aria-hidden="true"></i>
                Log in
            </a>
        </security:authorize>


        <security:authorize access="hasRole('USER') or hasRole('ADMIN')">
            <a href="<c:url value="/logout"/>" style="color: white"><i class="fa fa-user" aria-hidden="true"></i>
                Log out
            </a>
        </security:authorize>

    </div>
</nav>
<div class="container">
    <div class="row" style="height: 90px">
    </div>
</div>
<div class="custom-control custom-radio custom-control-inline">
    <input type="radio" id="customRadioInline1" name="customRadioInline1" class="custom-control-input"
           onclick="showOrHideDeparture()" checked>
    <label class="custom-control-label" for="customRadioInline1">Departure</label>
</div>
<div class="custom-control custom-radio custom-control-inline">
    <input type="radio" id="customRadioInline2" name="customRadioInline1" class="custom-control-input"
           onclick="showOrHideArrival()">
    <label class="custom-control-label" for="customRadioInline2">Arrival</label>
</div>


<div id="myDIV" style="display: block">
    <c:choose>
        <c:when test="${empty scheduleDTOListFrom}">
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
            <div class="container mt-4 p-md-4 col-12 rounded-container">
                <h2 style="margin-bottom: 30px; text-align: center">Departure(${station.title})</h2>
                <table class="table" style="text-align: center">
                    <tr>
                        <th scope="col">Train</th>
                        <th scope="col">From</th>
                        <th scope="col">To</th>
                        <th scope="col">Departure</th>
                        <th scope="col">Arrival</th>
                        <th scope="col">Trip Info</th>
                    </tr>

                    <tbody>

                    <c:forEach var="scheduleDTO" items="${scheduleDTOListFrom}" varStatus="vs">
                        <tr>
                            <td>${scheduleDTO.trainDTO.trainName}</td>
                            <th scope="row">${scheduleDTO.stationFromDTO.title}</th>
                            <td>${scheduleDTO.tripInfoList.get(scheduleDTO.tripInfoList.size()-1).stationTo.title}</td>
                            <fmt:setLocale value="en_US" scope="session"/>
                            <td>
                                <strong><fmt:formatDate value="${scheduleDTO.departureTime}" pattern="HH:mm"/></strong>
                                <br>
                                <fmt:formatDate value="${scheduleDTO.departureTime}" pattern="E, dd.MM.yyyy"/>
                            </td>
                            <td>
                                <fmt:formatDate
                                        value="${scheduleDTO.tripInfoList.get(scheduleDTO.tripInfoList.size()-1).arrivalTime}"
                                        pattern="HH:mm"/>
                                <br>
                                <fmt:formatDate
                                        value="${scheduleDTO.tripInfoList.get(scheduleDTO.tripInfoList.size()-1).arrivalTime}"
                                        pattern="E, dd.MM.yyyy"/>
                            </td>

                            <td><!-- Button trigger modal -->
                                <button type="button" class="btn btn-info" data-toggle="modal"
                                        data-target="#exampleModal${vs.index}" id="viewDetailButton${vs.index}">
                                    Show info
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal${vs.index}" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header" style="border-bottom: 0 none">
                                                <h5 class="modal-title" id="exampleModalLabel1">
                                                    Trip info
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                    <%--Modalbody--%>
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
                                                    <c1:forEach var="tripInfo" items="${scheduleDTO.tripInfoList}">

                                                        <tr>
                                                            <c:choose>
                                                                <c:when test="${(tripInfo.stationFrom.title.equals(scheduleDTO.stationFromDTO.title)) && (tripInfo.departureTime.equals(scheduleDTO.departureTime))}">
                                                                    <td><strong>${tripInfo.stationFrom.title}</strong>
                                                                    </td>
                                                                    <td>${tripInfo.stationTo.title}</td>
                                                                    <td><strong><fmt:formatDate
                                                                            value="${tripInfo.departureTime}"
                                                                            pattern="HH:mm dd.MM"/></strong></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td>${tripInfo.stationFrom.title}</td>
                                                                    <td>${tripInfo.stationTo.title}</td>
                                                                    <td><fmt:formatDate
                                                                            value="${tripInfo.departureTime}"
                                                                            pattern="HH:mm dd.MM"/></td>
                                                                </c:otherwise>
                                                            </c:choose>

                                                            <td><fmt:formatDate value="${tripInfo.arrivalTime}"
                                                                                pattern="HH:mm dd.MM"/></td>
                                                        </tr>

                                                    </c1:forEach>
                                                    </tbody>
                                                </table>

                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                    Close
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div id="myDIV2" style="display: none">
    <c:choose>
        <c:when test="${empty scheduleDTOListTo}">
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
            <div class="container mt-4 p-md-4 col-12 rounded-container">
                <h2 style="margin-bottom: 30px; text-align: center">Arrival(${station.title})</h2>
                <table class="table" style="text-align: center">
                    <tr>
                        <th scope="col">Train</th>
                        <th scope="col">From</th>
                        <th scope="col">To</th>
                        <th scope="col">Departure</th>
                        <th scope="col">Arrival</th>
                        <th scope="col">Trip Info</th>
                    </tr>
                    <tbody>
                    <c:forEach var="scheduleDTO2" items="${scheduleDTOListTo}" varStatus="vs2">
                        <tr>
                            <td>${scheduleDTO2.trainDTO.trainName}</td>
                            <td>${scheduleDTO2.tripInfoList.get(0).stationFrom.title}</td>
                            <th scope="row">${scheduleDTO2.stationToDTO.title}</th>
                            <fmt:setLocale value="en_US" scope="session"/>
                            <td>
                                <fmt:formatDate value="${scheduleDTO2.tripInfoList.get(0).departureTime}"
                                                pattern="HH:mm"/>
                                <br>
                                <fmt:formatDate value="${scheduleDTO2.tripInfoList.get(0).departureTime}"
                                                pattern="E, dd.MM.yyyy"/>
                            </td>
                            <td>
                                <strong> <fmt:formatDate value="${scheduleDTO2.arrivalTime}"
                                                         pattern="HH:mm"/> </strong>
                                <br>
                                <fmt:formatDate
                                        value="${scheduleDTO2.arrivalTime}"
                                        pattern="E, dd.MM.yyyy"/>
                            </td>

                            <td><!-- Button trigger modal -->
                                <button type="button" class="btn btn-info" data-toggle="modal"
                                        data-target="#exampleModal2${vs2.index}" id="viewDetailButton${vs2.index}">
                                    Show info
                                </button>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModal2${vs2.index}" tabindex="-1" role="dialog"
                                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header" style="border-bottom: 0 none">
                                                <h5 class="modal-title" id="exampleModalLabel2">
                                                    Trip info
                                                </h5>
                                                <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                    <%--Modalbody--%>
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
                                                    <c1:forEach var="tripInfo" items="${scheduleDTO2.tripInfoList}">
                                                        <tr>
                                                            <c:choose>
                                                                <c:when test="${(tripInfo.stationTo.title.equals(scheduleDTO2.stationToDTO.title)) && (tripInfo.arrivalTime.equals(scheduleDTO2.arrivalTime))}">
                                                                    <td>${tripInfo.stationFrom.title}</td>
                                                                    <td><strong>${tripInfo.stationTo.title}</strong>
                                                                    </td>
                                                                    <td><fmt:formatDate
                                                                            value="${tripInfo.departureTime}"
                                                                            pattern="HH:mm dd.MM"/></td>
                                                                    <td><strong><fmt:formatDate
                                                                            value="${tripInfo.arrivalTime}"
                                                                            pattern="HH:mm dd.MM"/></strong></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td>${tripInfo.stationFrom.title}</td>
                                                                    <td>${tripInfo.stationTo.title}</td>
                                                                    <td><fmt:formatDate
                                                                            value="${tripInfo.departureTime}"
                                                                            pattern="HH:mm dd.MM"/></td>
                                                                    <td><fmt:formatDate value="${tripInfo.arrivalTime}"
                                                                                        pattern="HH:mm dd.MM"/></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </tr>
                                                    </c1:forEach>
                                                    </tbody>
                                                </table>

                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                    Close
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
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
