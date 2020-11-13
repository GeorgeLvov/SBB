<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<form:form action="/checkin" name="checkInForm" method="POST" modelAttribute="ticket" class="form-signin">
    <h2 class="form-signin-heading"
        style="padding-bottom: 15px"> ${param.index} Check In ${param.departureTime}</h2>

    <input type="hidden" name="timeSearchFrom" value="${param.searchDF}"/>
    <input type="hidden" name="timeSearchTo" value="${param.searchDT}"/>

    <form:hidden path="trainId" value="${param.trainId}"/>
    <form:hidden path="tripId" value="${param.tripId}"/>
    <form:hidden path="departureTime" value="${param.departureTime}"/>
    <form:hidden path="arrivalTime" value="${param.arrivalTime}"/>
    <form:hidden path="stationFromId" value="${param.stationF}"/>
    <form:hidden path="stationToId" value="${param.stationT}"/>

    <spring:bind path="passengerName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label for="firstN">
                Passenger name:</label>
            <form:input type="text" path="passengerName" class="form-control" placeholder="Enter name"
                        id="firstN"></form:input>
        </div>
    </spring:bind>

    <spring:bind path="passengerSurName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label for="lastN">
                Passenger surname:</label>
            <form:input type="text" path="passengerSurName" class="form-control" placeholder="Enter surname"
                        id="lastN"></form:input>
        </div>
    </spring:bind>

    <spring:bind path="birthDate">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label for="BD">
                Date of birth:
            </label>
            <form:input type="date" path="birthDate" class="form-control" placeholder="Birthdate"
                        id="BD" min="1800-01-01"></form:input>
        </div>
    </spring:bind>
    <button class="btn btn-lg btn-success btn-block" type="submit" style="margin-top: 35px">Check In</button>
</form:form>--%>

<%--<script>
    console.log(${param.index})
    var firstNameInp;
    var lastNameInp;
    var birthDateInp;

    var firstNameLabel;
    var lastNameLabel;
    var birthDateLabel;

    var firstNameLabelText;
    var lastNameLabelText;
    var birthDateLabelText;

    function init() {
        console.log(${param.index})
        firstNameInp = document.getElementById("firstN${param.index}");
        lastNameInp = document.getElementById("lastN${param.index}");
        birthDateInp = document.getElementById("BD${param.index}");
        firstNameLabel = document.getElementById("fNLbl${param.index}");
        lastNameLabel = document.getElementById("lNLbl${param.index}");
        birthDateLabel = document.getElementById("bDLbl${param.index}");
        firstNameLabelText = firstNameLabel.textContent;
        lastNameLabelText = lastNameLabel.textContent;
        birthDateLabelText = birthDateLabel.textContent;
    }


    function validationCheckInForm() {
        init();
        let firstNameVal = document.forms["checkInForm${param.index}"]["firstN${param.index}"].value;
        let lastNameVal = document.forms["checkInForm${param.index}"]["lastN${param.index}"].value;
        let birthDateVal = document.forms["checkInForm${param.index}"]["BD${param.index}"].value;

        if (firstNameVal === null || firstNameVal.match(/^ *$/) !== null) {
            invalid(firstNameInp, firstNameLabel, "*Name can't be empty.");
            return false;
        }

        if (firstNameVal.length < 2 || firstNameVal.length > 100) {
            invalid(firstNameInp, firstNameLabel, "*Name must be between 1 and 100 characters.");
            return false;
        }

        if (firstNameVal.match("^[a-zA-Z -]+$") === null) {
            invalid(firstNameInp, firstNameLabel, "*Invalid symbol (only spaces or hyphens are allowed).");
            return false;
        }

        if (lastNameVal === null || lastNameVal.match(/^ *$/) !== null) {
            invalid(lastNameInp, lastNameLabel, "*Surname can't be empty.");
            return false;
        }

        if (lastNameVal.length < 2 || lastNameVal.length > 100) {
            invalid(lastNameInp, lastNameLabel, "*Surname must be between 1 and 100 characters.");
            return false;
        }

        if (lastNameVal.match("^[a-zA-Z -]+$") === null) {
            invalid(lastNameInp, lastNameLabel, "*Invalid symbol (only spaces or hyphens are allowed).");
            return false;
        }

        if (birthDateVal === null || birthDateVal.match(/^ *$/) !== null) {
            invalid(birthDateInp, birthDateLabel, "*Birthdate can't be empty.");
            return false;
        }

        if (new Date(birthDateVal) > new Date()) {
            invalid(birthDateInp, birthDateLabel, "*Birthdate can't be later then now.");
            return false;
        }
    }

    function undoCheckInInputStyle(param) {
        if (param === 'firstN') {
            undoStyle(firstNameInp, firstNameLabel);
            firstNameLabel.textContent = firstNameLabelText;
        } else if (param === 'lastN') {
            undoStyle(lastNameInp, lastNameLabel);
            lastNameLabel.textContent = lastNameLabelText;
        } else if (param === 'BD') {
            undoStyle(birthDateInp, birthDateLabel);
            birthDateLabel.textContent = birthDateLabelText;
        }
    }

</script>--%>

<script src="<c:url value="/res/js/validation/commonFormValidation.js"/>"></script>
<%--<script src="<c:url value="/res/js/validation/checkInFormValidation.js"/>"></script>--%>
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
<%--<form:form action="/checkin" name="checkInForm" method="POST"
           modelAttribute="ticket" class="form-signin" onsubmit="return validationCheckInForm()">
    <h2 class="form-signin-heading"
        style="padding-bottom: 15px"> ${param.index} Check In ${param.departureTime}</h2>

    <input type="hidden" name="timeSearchFrom" value="${param.searchDF}"/>
    <input type="hidden" name="timeSearchTo" value="${param.searchDT}"/>

    <form:hidden path="trainId" value="${param.trainId}"/>
    <form:hidden path="tripId" value="${param.tripId}"/>
    <form:hidden path="departureTime" value="${param.departureTime}"/>
    <form:hidden path="arrivalTime" value="${param.arrivalTime}"/>
    <form:hidden path="stationFromId" value="${param.stationF}"/>
    <form:hidden path="stationToId" value="${param.stationT}"/>

    <spring:bind path="passengerName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label id="fNLbl" for="firstN">
                Passenger name:</label>
            <form:input type="text" path="passengerName" class="form-control" placeholder="Enter name"
                        id="firstN" onchange="undoCheckInInputStyle('firstN')"></form:input>
        </div>
    </spring:bind>

    <spring:bind path="passengerSurName">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label id="lNLbl" for="lastN">
                Passenger surname:</label>
            <form:input type="text" path="passengerSurName" class="form-control" placeholder="Enter surname"
                        id="lastN" onchange="undoCheckInInputStyle('lastN')"></form:input>
        </div>
    </spring:bind>

    <spring:bind path="birthDate">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <label id="bDLbl" for="BD">
                Date of birth:
            </label>
            <form:input type="date" path="birthDate" class="form-control" placeholder="Birthdate"
                        id="BD" min="1800-01-01" onchange="undoCheckInInputStyle('BD')"></form:input>
        </div>
    </spring:bind>
    <button class="btn btn-lg btn-success btn-block" type="submit" style="margin-top: 35px">Check In</button>
</form:form>--%>