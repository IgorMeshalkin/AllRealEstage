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

<h2 align="center">Your profile</h2>
<table align="center">
    <tr>
        <td style="background: #ccc"><b>Username</b></td>
        <td>${currentUser.username}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Firstname</b></td>
        <td>${currentUser.firstName}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Lastname</b></td>
        <td>${currentUser.lastName}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Rating</b></td>
        <td>${currentUser.rating}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Phone number</b></td>
        <td>${currentUser.phoneNumber}</td>
    </tr>
    <tr>
        <td style="background: #ccc"><b>Created</b></td>
        <td>${currentUser.createdFormat}</td>
    </tr>
    <tr>
        <td colspan="2" style="border-color: white">
            <button style="height:35px;width:65px; font-weight: bold; float: left"
                    onclick="window.location.href='/api/users/update_user'">Update
            </button>
        <button style="height:35px;width:55px; font-weight: bold; float: right" onclick="window.location.href='/'">
            Main
        </button>
        </td>
    </tr>
</table>

<%--<p align="center"><a href="<c:url value="/api/users/update_user"/>"><b>Update</b></a></p>--%>

<%--<p align="center">--%>
<%--    <button style="height:30px;width:50px" onclick="window.location.href='/'"><b>Main</b></button>--%>
<%--</p>--%>
</body>
</html>