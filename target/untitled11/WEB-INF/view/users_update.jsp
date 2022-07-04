<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--Accept-Charset: utf-8, iso-8859-1;q=0.5--%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8"/>
    <title>Update profile</title>
    <style>
        th {
            text-align: right; /* Выравнивание по левому краю */
            background: #ccc; /* Цвет фона ячеек */
            padding: 5px; /* Поля вокруг содержимого ячеек */
        }

        td {
            padding: 5px; /* Поля вокруг содержимого ячеек */
        }
    </style>
</head>
<html>
<body>
<div align="right">
    <%= request.getAttribute("currentUserName") %>
    <button onclick="window.location.href='/api/users/profile'">Profile</button>
    <form action="/logout" method="post" style="float: right">
        <input type="submit" value="Sign Out"/>
    </form>
</div>
<hr>

<h2 align="center">${isAdmin ? 'Update profile' : 'Update your profile'}</h2>
<table align="center">
    <form:form id="update_user" method="post"
               action="${isAdmin ? '/api/admin/users/save_updated_user' : '/api/users/save_updated_user'}"
               modelAttribute="user">

        <form:hidden path="id"/>

        <c:choose>
            <c:when test="${isCredentials}">
                <tr>
                    <th>Username:</th>
                    <td>
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input path="username"/>
                            <form:errors cssStyle="color: red" path="username"></form:errors>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Password:</th>
                    <td>
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="password" path="password"/>
                            <form:errors cssStyle="color: red" path="password"></form:errors>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>Repeat password:</th>
                    <td>
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="password" path="confirmPassword"/>
                            <form:errors cssStyle="color: red" path="confirmPassword"></form:errors>
                        </div>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <form:hidden path="username"/>
                <form:hidden path="password"/>
            </c:otherwise>
        </c:choose>

        <tr>
            <th>First name:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="firstName"/>
                    <form:errors cssStyle="color: red" path="firstName"></form:errors>
                </div>
            </td>
        </tr>
        <tr>
            <th>Last name:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="lastName"/>
                    <form:errors cssStyle="color: red" path="lastName"></form:errors>
                </div>
            </td>
        </tr>
        <tr>
            <th>Phone number:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="phoneNumber"/>
                    <form:errors cssStyle="color: red" path="phoneNumber"></form:errors>
                </div>
            </td>
        </tr>
    </form:form>
    <tr>
        <td colspan="2">
            <c:choose>
            <c:when test="${isCredentialsButtonVisible}">
            <form style="float: left">
                <input type="hidden" value="${isCredentials ? false : true}" name="isCredentials">
                <input type="submit"
                       style="height:35px;width:100px; white-space: normal; font-weight: bold"
                       value="${isCredentials ? 'Without credentials' : 'With credentials'}"/>
            </form>
            </c:when>
            </c:choose>
            <input type="submit" form="update_user" style="height:35px;width:55px; font-weight: bold; float: right"
                   value="OK">
        <td>
    </tr>
</table>
</body>
</html>