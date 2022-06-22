<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
>
<head>
    <title>Login</title>
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
<body>
<h2 align="center">Login</h2>
<table align="center">
    <tr>
        <th>
            <form action="/login" method="post">
                <div align="center"><label> Username : <input type="text" name="username"/> </label></div><br>
                <div align="center"><label> Password: <input type="password" name="password"/> </label></div><br>
                <div align="center"><input type="submit" value="Sign In"/></div>
            </form>
        </th>
    </tr>
</table>
<%--<input type="text" value="Registration"/>--%>
<p align="center"><a href="<c:url value="/api/users/registration"/>"><b>Registration</b></a></p>
</center>
</body>
</html>