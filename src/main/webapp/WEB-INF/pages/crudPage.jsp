<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
<head>
    <title>SBB: Add train | station</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
                <a class="nav-link" href="<c:url value="/setroute"/>" style="color: white">Set trip for train</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: white">
                    Show
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/stations"/>">Show all stations</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<c:url value="/trains"/>">Show all trains</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Show passengers</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/addemployee"/>" style="color: white"> Add new employee </a>
            </li>
        </ul>

        <a class="nav-link" href="<c:url value="/logout"/>" style="color: white">
            <i class="fa fa-user" aria-hidden="true"></i>
            Log out
        </a>
    </div>
</nav>

<div class="container-fluid">
    <div class="row" style="height: 100px">
    </div>
    <div class="row">

        <div class="col-md-1">
        </div>

        <div class="col-md-4 bg-light" style="border-radius: 2%">
            <h2 style="padding-top: 25px">Add Station</h2>
            <c:url value="/addStation" var="var"/>
            <form action="${var}" method="POST">
                <security:csrfInput />
                <div class="form-group">
                    <label for="stationTitle">Station Title:</label>
                    <input type="text" name="stationTitle" class="form-control" id="stationTitle"
                           aria-describedby="emailHelp"
                           placeholder="Enter station title">
                </div>
                <button type="submit" class="btn btn-success">Add</button>
            </form>
        </div>

        <div class="col-md-1">
        </div>

        <div class="col-md-4 bg-light " style="border-radius: 2%">
            <h2 style="padding-top: 25px">Add Train</h2>
            <c:url value="/addTrain" var="varT"/>
            <form action="${varT}" method="POST">
                <security:csrfInput />
                <div class="form-group">
                    <label for="trainNameLbl">Train name:</label>
                    <input type="text" name="trainName" class="form-control" id="trainNameLbl"
                           aria-describedby="emailHelp"
                           placeholder="Enter name">
                    <small id="trainNameHelp" class="form-text text-muted">Train name must not exceed 5
                        characters</small>
                </div>
                <div class="form-group">
                    <label for="capacityLbl">Train capacity:</label>
                    <input type="text" name="capacity" class="form-control" id="capacityLbl"
                           placeholder="Enter capacity">
                </div>
                <button type="submit" class="btn btn-success">Add</button>
            </form>
        </div>

        <div class="col-md-1">
        </div>

    </div>
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
