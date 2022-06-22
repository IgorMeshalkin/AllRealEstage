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
<h2 align="center">Create new apartment</h2>
<table align="center">
    <tr>
        <th>
            <form:form method="post" action="/save_created_apartment" modelAttribute="apartment">

                <form:hidden path="id"/>

                <%--                <div class="form-group ${status.error ? 'has-error' : ''}">--%>
                Address: <form:input path="street"/>
                <br>
                <%--                    <form:errors cssStyle="color: red" path="username"></form:errors>--%>
                <%--                </div>--%>
                <%--                <div class="form-group ${status.error ? 'has-error' : ''}">--%>
                Address: <form:input path="houseNumber"/>
                <br>
                <%--                    <form:errors cssStyle="color: red" path="username"></form:errors>--%>
                <%--                </div>--%>
                <%--                <div class="form-group ${status.error ? 'has-error' : ''}">--%>
                Number of rooms: <form:input path="numberOfRooms"/>
                <br>
                <%--                    <form:errors cssStyle="color: red" path="password"></form:errors>--%>
                <%--                </div>--%>
                <%--                <div class="form-group ${status.error ? 'has-error' : ''}">--%>
                Area: <form:input path="area"/>
                <br>
                <%--                    <form:errors cssStyle="color: red" path="confirmPassword"></form:errors>--%>
                <%--                </div>--%>
                <%--                <div class="form-group ${status.error ? 'has-error' : ''}">--%>
                Floor: <form:input path="floor"/>
                <br>
                <%--                    <form:errors cssStyle="color: red" path="firstName"></form:errors>--%>
                <%--                </div>--%>
                <%--                <div class="form-group ${status.error ? 'has-error' : ''}">--%>
                Total floors: <form:input path="totalFloors"/>
                <br>
                <%--                    <form:errors cssStyle="color: red" path="lastName"></form:errors>--%>
                <%--                </div>--%>
                <%--                <div class="form-group ${status.error ? 'has-error' : ''}">--%>
                Balcony availability: <form:checkbox path="balconyAvailability" value="true"/>
                <br>
                <%--                    <form:errors cssStyle="color: red" path="phoneNumber"></form:errors>--%>
                <%--                </div>--%>
                <%--                <div class="form-group ${status.error ? 'has-error' : ''}">--%>
                Price: <form:input path="price"/>
                <br>
                <%--                    <form:errors cssStyle="color: red" path="firstName"></form:errors>--%>
                <%--                </div>--%>
                <br>
                <input type="submit" value="OK">
            </form:form>
        </th>
    </tr>
</table>
</body>
</html>