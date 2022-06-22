<%@ page import="com.igormeshalkin.entity.User" %>
<%@ page import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
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
<div align="right">
    <%= request.getAttribute("currentUserName") %>
    <button onclick="window.location.href='/api/users/profile'">Profile</button>
    <form action="/logout" method="post" style="float: right">
        <input type="submit" value="Sign Out"/>
    </form>
</div>
<hr>

<h2 align="center">All Apartments</h2>

<table align="center">
    <tr>
        <th>Address</th>
        <th>Number of rooms</th>
        <th>Area</th>
        <th>Floor</th>
        <th>Total floors</th>
        <th>Balcony availability</th>
        <th>Price</th>
        <th>Contacts</th>
        <th>Created</th>
    </tr>
    <c:forEach var="apartment" items="${allApartments}">

        <c:url var="updateButton" value="/api/admin/users/update_user">
            <c:param name="userId" value="${user.id}"/>
        </c:url>

        <tr>
            <td>${apartment.addressFormat}</td>
            <td>${apartment.numberOfRooms}</td>
            <td>${apartment.area}</td>
            <td>${apartment.floor}</td>
            <td>${apartment.totalFloors}</td>
            <td>${apartment.balconyAvailability ? 'Yes' : 'No'}</td>
            <td>${apartment.priceFormat}</td>
            <td>${apartment.user.phoneNumber}</td>
            <td>${apartment.createdFormat}</td>
        </tr>
    </c:forEach>
</table>

<p align="center">
    <button style="height:60px;width:100px" onclick="window.location.href='/create_apartment'"><b>Add new apartment</b></button>
</p>

<p align="center">
    <button style="height:60px;width:100px" onclick="window.location.href='/api/admin/users'"><b>All Users</b></button>
</p>
</body>
</html>