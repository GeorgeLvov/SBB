<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<c:forEach var="passenger" items="${passengersList}">
    <p>${passenger.id}</p>
    <p>${passenger.firstName}</p>
    <p>${passenger.lastName}</p>
    <p>${passenger.birthDate}</p>
</c:forEach>
</body>
</html>
