<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--Accept-Charset: utf-8, iso-8859-1;q=0.5--%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8"/>
    <title>Update your profile</title>
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

<h2 align="center">Update your profile</h2>
<table align="center">
            <form:form method="post" action="/api/users/save_updated_user" modelAttribute="user">

                <form:hidden path="id"/>

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
                <tr>
                    <td colspan="2">
                        <input type="submit" style="font-weight: bold" value="OK">
                    <td>
                </tr>
            </form:form>
</table>
</body>
</html>