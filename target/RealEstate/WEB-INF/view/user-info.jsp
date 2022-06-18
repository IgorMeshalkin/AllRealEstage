<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--Accept-Charset: utf-8, iso-8859-1;q=0.5--%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8" />
    <title>User Info</title>
</head>
<html>
<body>
<h2>User info</h2>
<br>
<form:form method="get" action="saveUser" modelAttribute="user">

    <form:hidden path="id"/>

    Username: <form:input path="username"/>
    <br>
    Password: <form:input path="password"/>
    <br>
    First name: <form:input path="firstName"/>
    <br>
    Last name: <form:input path="lastName"/>
    <br>
    <input type="submit" value="OK">
</form:form>
</body>
</html>