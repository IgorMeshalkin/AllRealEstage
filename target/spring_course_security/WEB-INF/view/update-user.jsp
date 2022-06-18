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
<h2 align="center">Update User</h2>
<table align="center">
    <tr>
        <th>
            <form:form method="get" action="/api/users/save_updated_user" modelAttribute="user">

                <form:hidden path="id"/>

                <form:hidden path="password"/>

                Username: <form:input path="username"/>
                <br>
                First name: <form:input path="firstName"/>
                <br>
                Last name: <form:input path="lastName"/>
                <br>
                <input type="submit" value="OK">
            </form:form>
        </th>
    </tr>
</table>
</body>
</html>