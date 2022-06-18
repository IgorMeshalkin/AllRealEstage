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

<form action="/logout" method="post" align="right">
    <%= request.getAttribute("currentUser") %>
    <input type="submit" value="Sign Out"/>
</form>
<hr>

<h2 align="center">All Users</h2>

<table align="center">
    <tr>
        <th>First name</th>
        <th>Last name</th>
        <th>Role</th>
        <th>Active</th>
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
            <td>${user.role}
                <input type="button" value="Change Role"
                       onclick="window.location.href = '${changeRoleButton}'">
            </td>
            <td>${user.active}
                <input type="button" value="${user.active ? 'Block' : 'Unlock'}"
                       onclick="window.location.href = '${blockButton}'">
            </td>
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

<p align="center">
    <button style="height:30px;width:50px" onclick="window.location.href='/'"><b>Main</b></button>
</p>
</body>
</html>