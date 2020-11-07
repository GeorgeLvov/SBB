<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>

<html>
<head>
    <title>SBB: Add train | station</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/css/navbar.css"/>" />
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
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
                    Management &#8592;
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/trainselect"/>">Set trip for train</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Show
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/admin/trainsandstations"/>">Show all trains | stations</a>
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


<div class="container-fluid">
    <div class="row" style="height: 20px">

    </div>
    <div class="row">
        <div class="col-lg"></div>
        <div class="col-4" style="border-radius: 2%">
        <div class="card bg-light" style="width: 24rem; height: 31rem;">
            <img class="card-img-top" src="${pageContext.request.contextPath}/res/img/station.jpeg"
                 alt="Card image cap">

            <div class="card-body">
                <h3 style="padding-bottom: 23px">Add new station:</h3>
                <form:form method="POST" name="stationForm" action="/admin/addStation" modelAttribute="stationDTO"
                           class="form-signin" onsubmit="return validateStationForm()">
                    <spring:bind path="title">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label id="statLbl" for="statInput" style="font-size: 14px"> Enter station title: </label>
                            <form:input type="text" path="title" class="form-control" placeholder="ex: Zurich Hbf"
                                        autofocus="true" name="station" id="statInput"
                                        onchange="undoStationInputStyle()"></form:input>
                            <form:errors path="title" cssStyle="display: none"></form:errors>
                        </div>
                    </spring:bind>
                    <button type="submit" class="btn btn-success" style="width: 340px; margin-top:5px">Create</button>
                </form:form>
            </div>
        </div>
        </div>

        <div class="col-4" style="border-radius: 2%">

        <div class="card bg-light" style="width: 24rem; height: 31rem;">

            <img class="card-img-top" src="${pageContext.request.contextPath}/res/img/train2.jpg"
                 width="60px" height="190px" alt="Card image cap">

            <div class="card-body" style="padding-top: 15px">

                <h3 style="padding-bottom: 10px">Add new train:</h3>

                <form:form name="trainForm" method="POST" action="/admin/addTrain" modelAttribute="trainDTO"
                           class="form-signin" onsubmit="return validateTrainForm()">
                    <spring:bind path="trainName">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label id="trainLbl" for="trainInput" style="font-size: 14px">Enter train name:</label>
                            <form:input type="text" path="trainName" class="form-control" placeholder="ex: HSR-350x"
                                        autofocus="true" id="trainInput"
                                        onchange="undoTrainInputStyle('train')"></form:input>
                            <form:errors path="trainName" cssStyle="display: none"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="capacity">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <label id="capLbl" for="capInput" style="font-size: 14px">Enter capacity:</label>
                            <form:input type="number" path="capacity" class="form-control" name="capacity"
                                        autofocus="true" id="capInput"
                                        onchange="undoTrainInputStyle('cap')"></form:input>
                            <form:errors path="capacity" cssStyle="display: none"></form:errors>
                        </div>
                    </spring:bind>

                    <button class="btn btn-success" type="submit" style="width: 340px; margin-top: 5px">
                        Create
                    </button>
                </form:form>
            </div>
        </div>
        </div>
        <div class="col-lg"></div>
    </div>
</div>


<footer class="fixed-bottom page-footer" style="background-color: #F4F6F6; margin-top: 40px">
    <p class="text-center footer-text">&copy; Swiss Federal Railways, 2020 </p>
</footer>


<script src="${pageContext.request.contextPath}/res/js/commonFormValidation.js"></script>
<script src="${pageContext.request.contextPath}/res/js/stationFormValidation.js"></script>
<script src="${pageContext.request.contextPath}/res/js/trainFormValidation.js"></script>

<c:choose>
    <c:when test="${param.train != null}">
        <script>trainSuccess()</script>
    </c:when>
    <c:when test="${param.station != null}">
        <script>stationSuccess()</script>
    </c:when>
</c:choose>

</body>
</html>
