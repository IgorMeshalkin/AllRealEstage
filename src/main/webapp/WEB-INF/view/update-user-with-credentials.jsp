<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--Accept-Charset: utf-8, iso-8859-1;q=0.5--%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8"/>
    <title>Registration</title>
    <style>
        table {
            /*width: 100%; !* Ширина таблицы *!*/
            border: 2px /*double*/ black; /* Рамка вокруг таблицы */
            border-collapse: collapse; /* Отображать только одинарные линии */
        }

        th {
            text-align: left; /* Выравнивание по левому краю */
            background: #ccc; /* Цвет фона ячеек */
            padding: 5px; /* Поля вокруг содержимого ячеек */
            border: 1px solid black; /* Граница вокруг ячеек */
        }

        td {
            padding: 5px; /* Поля вокруг содержимого ячеек */
            border: 1px solid black; /* Граница вокруг ячеек */
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
    <tr>
        <th>
            <form:form method="get" action="/api/users/save_updated_user" modelAttribute="user">

                <form:hidden path="id"/>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Username: <form:input path="username"/>
                    <form:errors cssStyle="color: red" path="username"></form:errors>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Password: <form:input type="password" path="password"/>
                    <form:errors cssStyle="color: red" path="password"></form:errors>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Repeat password: <form:input type="password" path="confirmPassword"/>
                    <form:errors cssStyle="color: red" path="confirmPassword"></form:errors>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    First name: <form:input path="firstName"/>
                    <form:errors cssStyle="color: red" path="firstName"></form:errors>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Last name: <form:input path="lastName"/>
                    <form:errors cssStyle="color: red" path="lastName"></form:errors>
                </div>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    Phone number: <form:input path="phoneNumber"/>
                    <form:errors cssStyle="color: red" path="phoneNumber"></form:errors>
                </div>
                <input type="submit" value="OK">
            </form:form>
        </th>
    </tr>
</table>
</body>
</html>