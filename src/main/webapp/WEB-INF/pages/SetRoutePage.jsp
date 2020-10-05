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
                <a class="nav-link" href="#" style="color: white">Set trip for train</a>
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


<form:form method="POST" modelAttribute="schInfoDTO" class="form-signin">

    <spring:bind path="trainId">
    <form:select class="form-control" path="trainId">
        <form:option value="0" label="Choose train" />
        <form:options items="${trainsList}" itemValue="id" itemLabel="trainName" />
    </form:select>
    </spring:bind>

    <spring:bind path="stationDTOs">
    <form:select class="form-control" path="stationFromId">
        <form:option value="0" label="From" />
        <form:options items="${stationsList}" itemValue="id" itemLabel="title" />
    </form:select>
    </spring:bind>

    <spring:bind path="stationDTOs">
    <form:select class="form-control" path="stationDTOs[0].id">
        <form:option value="0" label="To" />
        <form:options items="${stationsList}" itemValue="id" itemLabel="title" />
    </form:select>
    </spring:bind>

    <spring:bind path="stationDTOs">
        <form:select class="form-control" path="stationDTOs[1].id">
            <form:option value="0" label="To2" />
            <form:options items="${stationsList}" itemValue="id" itemLabel="title" />
        </form:select>
    </spring:bind>


    <spring:bind path="departureTime">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="datetime-local" path="departureTime" class="form-control" value="${currentTime}"
                        autofocus="true"></form:input>
            <form:errors path="departureTime" cssStyle="color: red; font-size: 14px"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="arrTimes">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="datetime-local" path="arrTimes[0].arrivalTime" class="form-control" value="1994-11-22T17:00:00"
                        autofocus="true"></form:input>
            <form:errors path="departureTime" cssStyle="color: red; font-size: 14px"></form:errors>
        </div>
    </spring:bind>

    <spring:bind path="arrTimes">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:input type="datetime-local" path="arrTimes[0].arrivalTime" class="form-control" value="1994-11-22T17:00:00"
                        autofocus="true"></form:input>
            <form:errors path="departureTime" cssStyle="color: red; font-size: 14px"></form:errors>
        </div>
    </spring:bind>


    <button class="btn btn-success" type="submit" style="margin-top: 35px">Sign Up</button>
</form:form>

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

<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<table width="100%">
    <tr>
        <td>Ширина</td>
        <td>Высота</td>
        <td>Артикуль</td>
        <td>Кол-во полотен</td>
    </tr>
    <tr>
        <td>
            <input type="text" name="width[0]" class="add_product_input" style="width:200px;" />
        </td>
        <td>
            <input type="text" name="height[0]" class="add_product_input" style="width:200px;" />
        </td>
        <td>
            <input type="text" name="articul[0]" class="add_product_input" style="width:200px;" />
        </td>
        <td>
            <input type="text" name="count[0]" class="add_product_input" style="width:200px;" />
        </td>
    </tr>
</table>
<button class="add_attr">Добавить</button>

<script>
    $('.add_attr').click(function() {
        var rowCount = $('tr').length - 1;
        var row = $('tr').last().clone();
        row.find('input').each(function() {
            var name = $(this).attr('name');
            $(this).attr('name', name.replace(/\[\d+\]/, '[' + rowCount + ']'))
        });
        $('table').append(row);
    });
</script>--%>