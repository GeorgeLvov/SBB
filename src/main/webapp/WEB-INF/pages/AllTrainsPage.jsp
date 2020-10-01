<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SBB: Trains</title>
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
                <a class="nav-link" href="<c:url value="/admin/crud"/>" style="color: white">  Add train | station </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/setroute"/>" style="color: white"> Set trip for train </a>
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
<div class="container bg-light mt-4 p-md-4 col-6 rounded-container">
    <table class="table">
        <thead class="thead-light">
        <tr>
            <th scope="col">#TrainID</th>
            <th scope="col">Name</th>
            <th scope="col">Capacity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="train" items="${trainsList}">
            <tr>
                <th scope="row">${train.id}</th>
                <td>${train.trainName}</td>
                <td>${train.capacity}</td>
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