<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>

<html>
<head>
    <title>SBB: Assign new trip</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/css/navbar.css"/>" />
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>

    <script>
        function mainFunction() {
            if (${routeContainer.sideArrivalTimes == null || empty routeContainer.sideArrivalTimes}) {
                alert("You haven't added any arrival stations!");
            } else {
                var x = Date.parse("${routeContainer.declaredArrivalDate}");
                var y = Date.parse("${(routeContainer.sideArrivalTimes != null && !empty routeContainer.sideArrivalTimes)
                 ? routeContainer.sideArrivalTimes.get(routeContainer.sideArrivalTimes.size()-1) : "0"}");
                if (x !== y) {
                    $('#WARN').modal({
                        show: true
                    });
                } else {
                    $('#CT').modal({
                        show: true
                    });
                }
            }
        }
    </script>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-white">
    <a class="navbar-brand" href="<c:url value="/admin/management"/>">
        <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Swiss Federal Railways &#8592;
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/crud"/>">Add train | station</a>
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

        <a class="nav-link" href="<c:url value="/"/>">
            <i class="fa fa-user" aria-hidden="true"></i>
            Log out
        </a>
    </div>
</nav>

<!-- Modal for start set route-->
<div class="modal fade bd-example-modal-lg" id="startModalId" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <a class="navbar-brand" href="<c:url value="/admin/management"/>" style="color: black">
                    <img src="/res/img/sbbBadge.png" width="30" height="30" class="d-inline-block align-top" alt="">
                </a>
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/admin/management"/>">
                            <i class="fas fa-cog"></i>
                            Management &#8592;
                        </a>
                    </li>
                </ul>
            </div>

            <form:form method="POST" action="/admin/trainselect" modelAttribute="routeDTO" class="form-signin">
                <div class="modal-body">
                    <div class="row"></div>
                    <div class="row">
                        <div class="col-5">
                            <spring:bind path="trainId">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="trainselect">Select train:</label>
                                    <form:select class="form-control" path="trainId" varStatus="tagStatus"
                                                 multiple="0"
                                                 id="trainselect">
                                        <form:option value="" label="Select"/>
                                        <form:options items="${trainsList}" itemValue="id"
                                                      itemLabel="trainName"/></form:select>
                                    <form:errors path="trainId" cssStyle="color: red; font-size: 14px"></form:errors>
                                </div>
                            </spring:bind>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-5">
                            <spring:bind path="departureStationId">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="depStation">Departure station:</label>
                                    <form:select class="form-control" path="departureStationId" varStatus="tagStatus"
                                                 multiple="0" id="depStation">
                                        <form:option value="" label="From"/>
                                        <form:options items="${stationsList}" itemValue="id"
                                                      itemLabel="title"/></form:select>
                                    <form:errors path="departureStationId"
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
                                    <form:errors path="departureDate"
                                                 cssStyle="color: red; font-size: 14px"></form:errors>
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
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success" style="width: 200px">Continue</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<%-- Main table --%>
<div class="container bg-light mt-1 col-12">
    <table class="table table-sm" style="text-align: center">
        <thead class="thead-light">
        <tr>
            <th style="width: 25%" scope="col">Train</th>
            <th scope="col">Departure station</th>
            <th scope="col">Departure time</th>
            <th scope="col">Declared end time</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${routeContainer.trainDTO.trainName}</td>
                <td>${routeContainer.departureStation.title}</td>

                <javatime:parseLocalDateTime value="${routeContainer.departureDate}"
                                             pattern="yyyy-MM-dd'T'HH:mm" var="depDate"/>
                <td>
                    <strong><javatime:format pattern="HH:mm" value="${depDate}" style="MS" /></strong>
                    <javatime:format pattern="dd.MM.yy" value="${depDate}" style="MS" />
                </td>

                <javatime:parseLocalDateTime value="${routeContainer.declaredArrivalDate}"
                                             pattern="yyyy-MM-dd'T'HH:mm" var="arrDate"/>
                <td>
                    <strong><javatime:format pattern="HH:mm" value="${arrDate}" style="MS" /></strong>
                    <javatime:format pattern="dd.MM.yy" value="${arrDate}" style="MS" />
                </td>
            </tr>
        </tbody>
    </table>
</div>

<%-- Table for segments --%>
<c:if test="${!empty routeContainer.sideStations}">
    <h3 style="text-align: center; padding: 20px">Segments</h3>
    <div class="container bg-light">
        <table class="table table-sm" style="text-align: center">
            <thead class="thead-light">
            <tr>
                <th scope="col">Arrival station</th>
                <th scope="col">Arrival datetime</th>
                <th scope="col">Stop duration</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${routeContainer.sideStations}" varStatus="vs">
                <tr>
                    <td>${routeContainer.sideStations[vs.index].title}</td>

                    <javatime:parseLocalDateTime value="${routeContainer.sideArrivalTimes[vs.index]}"
                                                 pattern="yyyy-MM-dd'T'HH:mm" var="arrDaterime"/>
                    <td>
                        <strong><javatime:format pattern="HH:mm" value="${arrDaterime}" style="MS" /></strong>
                        <javatime:format pattern="dd.MM.yy" value="${arrDaterime}" style="MS" />
                    </td>

                    <td>${routeContainer.stops[vs.index]}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>


<%-- Form for segments --%>

<div class="container mb-5">
<form:form method="POST" action="/admin/setroute" modelAttribute="routeDTO" class="form-signin">
    <div class="row" style="height: 40px"></div>
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <div class="row">
                <div class="col-4">
                    <spring:bind path="sideStations">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label for="stat">Station:</label>
                            <form:select class="form-control" path="sideStations" multiple="0" id="stat">
                                <form:option value="" label="Select"/>
                                <form:options items="${stationsList}" itemValue="id" itemLabel="title"/>
                            </form:select>
                            <form:errors path="sideStations" cssStyle="color: red; font-size: 14px"></form:errors>
                        </div>
                    </spring:bind>
                </div>
                <div class="col-4">
                    <spring:bind path="sideArrivalTimes">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label for="arrDt">Arrival date:</label>
                            <form:input type="datetime-local" path="sideArrivalTimes" class="form-control"
                                        placeholder="" id="arrDt"></form:input>
                            <form:errors path="sideArrivalTimes" cssStyle="color: red; font-size: 14px"></form:errors>
                        </div>
                    </spring:bind>
                </div>
                <div class="col-3">
                    <spring:bind path="stops">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label for="stopD">Stop duration, min:</label>
                            <form:input type="number" path="stops" class="form-control"
                                        min="5" max="180" step="5" placeholder="" id="stopD"></form:input>
                            <form:errors path="stops" cssStyle="color: red; font-size: 14px"></form:errors>
                        </div>
                    </spring:bind>
                </div>
            </div>
            <div style="margin-top: 10px">
                <button type="submit" class="btn btn-success" style="width: 140px"> Add station</button>
                <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModal13"
                        style="width: 140px">
                    Delete change
                </button>
            </div>
            <div style="margin-top: 10px">
                <button type="button" class="btn btn-danger ui-button ui-corner-all ui-widget openModal" id="myBut"
                        style="width: 280px" onclick="mainFunction()">
                    Create trip
                </button>
            </div>
        </div>
    </div>
    <div class="col-2"></div>
</form:form>
</div>

<!-- Modal for delete -->
<div class="modal fade" id="exampleModal13" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel13"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel13">Delete last change</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <a class="btn btn-secondary" href="/admin/deleteLast">Delete</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal 1 for end creating trip -->
<div class="modal fade" id="WARN" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"
     data-show="">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <javatime:parseLocalDateTime value="${routeContainer.declaredArrivalDate}" pattern="yyyy-MM-dd'T'HH:mm"
                                             var="decArDate"/>
                <p>Declared arrival datetime of the trip:
                   <strong> <javatime:format pattern="HH:mm dd.MM.yyyy" value="${decArDate}" style="MS" /></strong>
                </p>
                The last entered arrival datetime now does not match the declared end time you entered earlier,
                but you can still create such a trip, or delete the last change and enter the correct date.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <a class="btn btn-danger" href="<c:url value="/admin/createtrip"/>">Create trip</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal 2 for end creating trip-->
<div class="modal fade" id="CT" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true"
     data-show="">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel2">SBB CFF FFS</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Create the trip?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <a class="btn btn-danger" href="<c:url value="/admin/createtrip"/>">Create trip</a>
            </div>
        </div>
    </div>
</div>

<footer class="fixed-bottom page-footer" style="background-color: #F4F6F6">
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

<c:if test="${(param.start != null) || (error != null)}">
    <script>
        $('#startModalId').modal({
            show: true,
            backdrop: 'static',
            keyboard: false,
        });
    </script>
</c:if>
</body>
</html>
