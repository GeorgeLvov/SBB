<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SBB: Stations</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/css/forMainPages.css"/>" />
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
                    Management &#8592;
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



<div class="row" style="margin-top: 30px;">
    <div class="col-lg"></div>

    <div class="col-4">
        <div class="container bg-light rounded-container" style="border-radius: 5px; height: 450px; position: relative">
            <h2 style="text-align: center; padding-top: 20px; padding-bottom: 10px;">Stations</h2>

            <table class="table-sm" style="text-align: center; width: 400px;">
                <thead class="thead bg-danger" style="border: none; color: white">
                <tr>
                    <th scope="col" width="25%">№</th>
                    <th scope="col" width="50%">Title</th>
                    <th scope="col">Edit</th>
                </tr>
                </thead>
                <tbody id="listingTable">
                </tbody>
            </table>

            <div style="position: absolute; text-align: center;  bottom: 20px">
                <a href="javascript:prevPage()" id="btn_prev">Prev</a>
                <a href="javascript:nextPage()" id="btn_next">Next</a>
                page: <span id="page"></span>
            </div>
        </div>
    </div>

    <div class="col-1"></div>

    <div class="col-4">

        <div class="container bg-light" style="border-radius: 5px; height: 450px;">
            <h2 style="text-align: center; padding-top: 20px; padding-bottom: 10px;">Trains</h2>
            <table class="table-sm" style="text-align: center; width: 400px; margin-bottom: 30px;">
                <thead class="thead bg-danger" style="color: white">
                <tr>
                    <th scope="col" width="25%">№</th>
                    <th scope="col" width="50%">Name</th>
                    <th scope="col">Capacity</th>
                </tr>
                </thead>
                <tbody id="trainTable">
                </tbody>
            </table>
            <div style="position: absolute; text-align: center;  bottom: 20px">
                <a href="javascript:prevTrainPage()" id="btn_prevt">Prev</a>
                <a href="javascript:nextTrainPage()" id="btn_nextt">Next</a>
                page: <span id="trainPage"></span>
            </div>
        </div>

    </div>
    <div class="col-lg"></div>
</div>

<footer class="page-footer fixed-bottom" style="background-color: #F4F6F6;">
    <p class="text-center footer-text" style="vertical-align: middle">&copy; Swiss Federal Railways, 2020 </p>
</footer>

<script src="${pageContext.request.contextPath}/res/js/paginationStations.js"></script>
<script src="${pageContext.request.contextPath}/res/js/paginationTrains.js"></script>

<c:forEach var="station" items="${stationsList}" >
    <script> data(${station.id}, "${station.title}") </script>
</c:forEach>
<c:forEach var="train" items="${trainsList}" >
    <script> trainData("${train.trainName}", "${train.capacity}") </script>
</c:forEach>


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

