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
    <c:choose>
        <c:when test="${apartment.ownedByCurrentUser}">
            <tr>
                <td style="border-color: white" colspan="2" ><b>This is your apartment</b></td>
            </tr>
            <tr>
                <td style="border-color: white" ><b>Added to favorites:</b></td>
                <td style="border-color: white; color: red" ><b>${apartment.likes.size()}</b></td>
            </tr>
            <tr>
                <c:url var="updateButton" value="/update_apartment">
                    <c:param name="apartmentId" value="${apartment.id}"/>
                </c:url>

                <c:url var="deleteButton" value="/delete">
                    <c:param name="apartmentId" value="${apartment.id}"/>
                </c:url>

                <td style="border: white" colspan="2">
                    <input type="button" style="height:30px;width:60px" value="Update"
                           onclick="window.location.href = '${updateButton}'">

                    <input type="button" style="height:30px;width:60px" value="Delete"
                           onclick="window.location.href = '${deleteButton}'">

                    <button style="height:30px;width:100px; float: right" onclick="window.location.href='${lastPage}'"><b>Back to main</b></button>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <tr>
                <td style="border-right-color: white; border-left-color: white"><b>Contacts:</b></td>
            </tr>
            <tr>
                <td style="background: #ccc"><b>Name</b></td>
                <td>${apartment.user.firstName}</td>
            </tr>
            <tr>
                <td style="background: #ccc"><b>Phone number</b></td>
                <td>${apartment.user.phoneNumber}</td>
            </tr>

            <tr>
                <c:url var="likeButton" value="/api/likes">
                    <c:param name="apartmentId" value="${apartment.id}"/>
                </c:url>
                <td style="border: white"><b>Add to favorites:</b>
                <td style="border: white">
                    <input type="button" value="${apartment.likedByCurrentUser ? '&#128077' : '    '}"
                           onclick="window.location.href = '${likeButton}'">
                </td>
            </tr>
            <tr>
                <td colspan="2" style="border-color: white">
                    <p align="center">
                        <button style="height:30px;width:50px" onclick="window.location.href='${lastPage}'"><b>Main</b></button>
                    </p>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
</body>
</html>