<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--Accept-Charset: utf-8, iso-8859-1;q=0.5--%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8"/>
    <title>${action.equals("create") ? 'Create new apartment' : 'Update apartment'}</title>
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
<h2 align="center">${action.equals("create") ? 'Create new apartment' : 'Update apartment'}</h2>
<table align="center">

    <form:form method="post" action="${action}" modelAttribute="apartment">
        <form:hidden path="id"/>
        <tr>
            <th>Street:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="street"/>
                    <form:errors cssStyle="color: red" path="street"></form:errors>
                </div>
            </td>
        </tr>
        <tr>
            <th>House number:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="houseNumber"/>
                    <form:errors cssStyle="color: red" path="houseNumber"></form:errors>
                </div>
            </td>
        </tr>
        <tr>
            <th>Number of rooms:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="numberOfRooms"/>
                    <form:errors cssStyle="color: red" path="numberOfRooms"></form:errors>
                </div>
            </td>
        </tr>
        <tr>
            <th>Area:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="area"/>
                    <form:errors cssStyle="color: red" path="area"></form:errors>
                </div>
            </td>
        </tr>
        <tr>
            <th>Floor:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="floor"/>
                    <form:errors cssStyle="color: red" path="floor"></form:errors>
                </div>
            </td>
        </tr>
        <tr>
            <th>Total floors:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="totalFloors"/>
                    <form:errors cssStyle="color: red" path="totalFloors"></form:errors>
                </div>
            </td>
        </tr>
        <tr>
            <th>Balcony availability:</th>
            <td>
                <form:checkbox path="balconyAvailability" value="true"/>
            </td>
        </tr>
        <tr>
            <th>Price:</th>
            <td>
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="price"/>
                    <form:errors cssStyle="color: red" path="price"></form:errors>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" style="font-weight: bold;" value="OK">
            </td>
        </tr>
    </form:form>


    </tr>
</table>
</body>
</html>