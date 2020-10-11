<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>

<html>
<head>
    <title>SBB: Set trip</title>
    <link rel="shortcut icon" href="/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://use.fontawesome.com/465a5a8cc2.js"></script>
    <script>
        function mainFunction() {
            if (${resultRouteDTO.sideArrivalTimes == null || empty resultRouteDTO.sideArrivalTimes}) {
                alert("Вы не добавили станций!");
            } else {
                var x = Date.parse("${resultRouteDTO.declaredArrivalDate}");
                var y = Date.parse("${(resultRouteDTO.sideArrivalTimes != null && !empty resultRouteDTO.sideArrivalTimes) ? resultRouteDTO.sideArrivalTimes.get(resultRouteDTO.sideArrivalTimes.size()-1) : "0"}");

                if (x !== y) {
                    $('#WARN').modal({
                        show: true
                    });
                } else {
                    $('#CR').modal({
                        show: true
                    });
                }
            }
        }
    </script>
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

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: white">
                    Show
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/admin/stations"/>">Show all stations</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<c:url value="/admin/trains"/>">Show all trains</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Show passengers</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/addemployee"/>" style="color: white"> Add new employee </a>
            </li>
        </ul>

        <a class="nav-link" href="<c:url value="/"/>" style="color: white">
            <i class="fa fa-user" aria-hidden="true"></i>
            Log out
        </a>
    </div>
</nav>

${resultRouteDTO}


<form:form method="POST" action="/admin/setRoute" modelAttribute="routeDTO" class="form-signin">

    <div class="row" style="height: 40px">
    </div>
    <div class="row">

        <div class="col-3">
            <spring:bind path="sideStations">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:select class="form-control" path="sideStations" varStatus="tagStatus" multiple="0">
                        <form:option value="" label="Select"/>
                        <form:options items="${stationList}" itemValue="title" itemLabel="title"/>
                    </form:select>
                    <form:errors path="sideStations" cssStyle="color: red; font-size: 14px"></form:errors>
                </div>
            </spring:bind>
        </div>


        <div class="col-3">
            <spring:bind path="sideArrivalTimes">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="datetime-local" path="sideArrivalTimes" class="form-control"
                                placeholder=""></form:input>
                    <form:errors path="sideArrivalTimes" cssStyle="color: red; font-size: 14px"></form:errors>
                </div>
            </spring:bind>
        </div>

        <div class="col-1">
            <spring:bind path="stops">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="number" path="stops" class="form-control"
                                min="5" max="180" step="5" placeholder=""></form:input>
                    <form:errors path="stops" cssStyle="color: red; font-size: 14px"></form:errors>
                </div>
            </spring:bind>
        </div>
    </div>
    <button type="submit" class="btn btn-success"> Add station</button>
</form:form>



<button class="btn btn-danger" onclick="mainFunction()"> Create trip</button>






<!-- Modal -->
<button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#exampleModal13">
    Reset last change
</button>
<div class="modal fade" id="exampleModal13" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel13" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel13">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <a class="btn btn-secondary" href="/admin/deleteLast">Reset Change</a>
            </div>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="WARN" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-show="">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Сейчас время прибытия маршрута не совпадает с введенным вами ранее!
                Но вы все равно можете добавить такой маршрут или удалите последнее изменение
                и вбейте правильную дату.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <a class="btn btn-danger" href="<c:url value="/admin/createtrip"/>">Create trip</a>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="CR" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-show="">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel2">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Создать маршрут??
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <%-- <button type="button" class="btn btn-primary">Save changes</button>--%>
                <a class="btn btn-danger" href="<c:url value="/admin/createtrip"/>">Create trip</a>
            </div>
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
