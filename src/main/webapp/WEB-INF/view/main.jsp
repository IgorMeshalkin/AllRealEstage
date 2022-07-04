<%@ page import="com.igormeshalkin.entity.User" %>
<%@ page import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main</title>
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
<h2 align="center">
    <c:choose>
        <c:when test="${mainFilter.equals('favorites')}">
            Your favorite apartments
        </c:when>
        <c:when test="${mainFilter.equals('my_apartments')}">
            Your apartments
        </c:when>
        <c:otherwise>
            All apartments
        </c:otherwise>
    </c:choose>
</h2>

<table align="center">
    <tr style="padding-bottom: 0px">
        <td style="border: white"></td>
        <td style="border: white" colspan="9">

            <form style="float: left">
                <input type="hidden" value="${mainFilter.equals("my_apartments") ? 'all_apartments' : 'my_apartments'}" name="mainFilter">
                <input type="submit" style="height:45px;width:100px; white-space: normal; font-weight: bold; margin-right: 10px" value="${mainFilter.equals("my_apartments") ? 'All apartments' : 'My apartments'}"/>
            </form>

            <form style="float: left">
                <input type="hidden" value="${mainFilter.equals("favorites") ? 'all_apartments' : 'favorites'}" name="mainFilter">
                <input type="submit" style="height:45px;width:100px; white-space: normal; font-weight: bold; margin-right: 10px" value="${mainFilter.equals("favorites") ? 'All apartments' : 'Only favorite apartments'}"/>
            </form>

            <button style="height:45px;width:100px" onclick="window.location.href='/create_apartment'"><b>Add new
                apartment</b></button>

            <security:authorize access="hasAuthority('users:show all')">
                <button style="height:45px;width:100px; vertical-align: bottom; float: right"
                        onclick="window.location.href='/api/admin/users'"><b>All Users</b></button>
            </security:authorize>

        </td>
    </tr>
    <tr>
        <td style="border: white; padding: 0px"></td>
        <td style="border: white; padding: 0px" colspan="8">
            <jsp:include page="apartments_search.jsp"/>
        </td>
    </tr>
    <tr>
        <th style="background-color: white; border-color: white; border-right-color: black"></th>
        <th>Address</th>
        <th>Number of rooms</th>
        <th>Area</th>
        <th>Floor</th>
        <th>Total floors</th>
        <th>Balcony availability</th>
        <th>Price</th>
        <th>Created</th>
        <c:choose>
            <c:when test="${mainFilter.equals('my_apartments')}">
                <th>Update/Delete</th>
            </c:when>
            <c:otherwise>
                <security:authorize access="hasAuthority('real estate:update any')">
                    <th>Update/Delete</th>
                </security:authorize>
            </c:otherwise>
        </c:choose>
    </tr>
    <c:forEach var="apartment" items="${allApartments}">

        <c:url var="updateButton" value="/update_apartment">
            <c:param name="apartmentId" value="${apartment.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/delete">
            <c:param name="apartmentId" value="${apartment.id}"/>
        </c:url>

        <c:url var="detailsButton" value="/details">
            <c:param name="apartmentId" value="${apartment.id}"/>
        </c:url>

        <c:url var="likeButton" value="/api/likes">
            <c:param name="apartmentId" value="${apartment.id}"/>
        </c:url>

        <tr>
            <td style="border-color: white; border-right-color: black">
                <input type="button" value="Details"
                       onclick="window.location.href = '${detailsButton}'">
            </td>
            <td>${apartment.addressFormat}</td>
            <td>${apartment.numberOfRooms}</td>
            <td>${apartment.area}</td>
            <td>${apartment.floor}</td>
            <td>${apartment.totalFloors}</td>
            <td>${apartment.balconyAvailability ? 'Yes' : 'No'}</td>
            <td>${apartment.priceFormat}</td>
            <td>${apartment.createdFormat}</td>

            <c:choose>
                <c:when test="${mainFilter.equals('my_apartments')}">
                    <td>
                        <input type="button" value="Update"
                               onclick="window.location.href = '${updateButton}'">

                        <input type="button" value="Delete"
                               onclick="window.location.href = '${deleteButton}'">
                    </td>
                </c:when>
                <c:otherwise>
                    <security:authorize access="hasAuthority('real estate:update any')">
                        <td>
                            <input type="button" value="Update"
                                   onclick="window.location.href = '${updateButton}'">

                            <input type="button" value="Delete"
                                   onclick="window.location.href = '${deleteButton}'">
                        </td>
                    </security:authorize>
                </c:otherwise>
            </c:choose>

            <td style="border-color: white; align: center; color: ${apartment.likedByCurrentUser ? 'red': 'grey'}">
                <input type="button" value="${apartment.likedByCurrentUser ? '&#128077' : '    '}"
                       onclick="window.location.href = '${likeButton}'">
                    ${apartment.likes.size()}
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>