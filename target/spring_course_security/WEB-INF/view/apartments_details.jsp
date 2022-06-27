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

<h2 align="center">Apartment</h2>

<table align="center">
    <tr>
        <td style="background: #ccc"><b>Address</b></td>
        <td>${apartment.addressFormat}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Number of rooms</b></td>
        <td>${apartment.numberOfRooms}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Area</b></td>
        <td>${apartment.area}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Floor</b></td>
        <td>${apartment.floor}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Total floors</b></td>
        <td>${apartment.totalFloors}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Balcony availability</b></td>
        <td>${apartment.balconyAvailability ? 'Yes' : 'No'}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Price</b></td>
        <td>${apartment.priceFormat}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Created</b></td>
        <td>${apartment.createdFormat}</td>
    </tr>
    <tr><td style="border-right-color: white; border-left-color: white"><b>Contacts:</b></td></tr>
    <tr>
        <td style="background: #ccc"><b>Name</b></td>
        <td>${apartment.user.firstName}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Phone number</b></td>
        <td>${apartment.user.phoneNumber}</td>
    </tr>
    <tr>
        <c:url var="likeButton" value="/api/likes/apartments_by_details">
            <c:param name="apartmentId" value="${apartment.id}"/>
        </c:url>
        <td style="border: white"><b>Add to favorites:</b>
        <td style="border: white">
        <input type="button" value="${apartment.likedByCurrentUser ? '&#128077' : '    '}"
               onclick="window.location.href = '${likeButton}'">
    </td>
    </tr>
</table>

<%--<p align="center"><a href="<c:url value="/api/users/update_user"/>"><b>Update</b></a></p>--%>

<p align="center">
    <button style="height:30px;width:50px" onclick="window.location.href='/'"><b>Main</b></button>
</p>
</body>
</html>