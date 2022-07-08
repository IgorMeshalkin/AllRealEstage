<%@ page import="com.igormeshalkin.entity.User" %>
<%@ page import="org.apache.taglibs.standard.lang.jstl.test.PageContextImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<title>All users</title>
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
<body style="font-family: Calibri">

<div align="right">
    <%= request.getAttribute("currentUserName") %>
    <button style="margin-right: 5px" onclick="window.location.href='/api/users/profile'">Profile</button>
    <form action="/logout" method="post" style="float: right">
        <input type="submit" value="Sign Out"/>
    </form>
</div>
<hr>

<h2 align="center">
    <c:choose>
        <c:when test="${mainFilter.equals('only_admins')}">
            Users with role: "ADMIN"
        </c:when>
        <c:when test="${mainFilter.equals('only_users')}">
            Users with role: "USER"
        </c:when>
        <c:otherwise>
            All users
        </c:otherwise>
    </c:choose>
</h2>

<table align="center">
    <tr>
        <td style="border: white; padding: 0px" colspan="8">
            <jsp:include page="users_search.jsp"/>
        </td>
        <td style="border-color: white; border-bottom-color: black">
            <button style="height:40px;width:65px; float: right" onclick="window.location.href='/'"><b>Main</b></button>
        </td>
    </tr>
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Phone number</th>
        <th>Role</th>
        <th>Active</th>
        <th>Rating</th>
        <th>Created</th>
        <th>Updated</th>
        <th>Update/Delete</th>
    </tr>
    <c:forEach var="user" items="${allUsers}">

        <c:url var="updateButton" value="/api/admin/users/update_user">
            <c:param name="userId" value="${user.id}"/>
        </c:url>

        <c:url var="changeRoleButton" value="/api/admin/users/change_role">
            <c:param name="userId" value="${user.id}"/>
        </c:url>

        <c:url var="blockButton" value="/api/admin/users/block">
            <c:param name="userId" value="${user.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/api/admin/users/delete">
            <c:param name="userId" value="${user.id}"/>
        </c:url>

        <tr style="color:${user.active ? 'black' : 'grey'}">
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.role}
                <input type="button" value="Change Role" style="float: right"
                       onclick="window.location.href = '${changeRoleButton}'">
            </td>
            <td>${user.active}
                <input type="button" style="float: right" value="${user.active ? 'Block' : 'Unlock'}"
                       onclick="window.location.href = '${blockButton}'">
            </td>
            <td>${user.rating}</td>
            <td>${user.createdFormat}</td>
            <td>${user.updatedFormat}</td>
            <td>
                <input type="button" value="Update"
                       onclick="window.location.href = '${updateButton}'">

                <input type="button" value="Delete"
                       onclick="window.location.href = '${deleteButton}'">
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>