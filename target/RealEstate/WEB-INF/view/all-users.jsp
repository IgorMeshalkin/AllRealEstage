<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<h2>All Users</h2>
<br>
<table>
    <tr>
        <th>First name</th>
        <th>Last name</th>
    </tr>
    <c:forEach var="user" items="${allUsers}">

        <c:url var="updateButton" value="/updateUser">
            <c:param name="userId" value="${user.id}"/>
        </c:url>

        <c:url var="deleteButton" value="/delete">
            <c:param name="userId" value="${user.id}"/>
        </c:url>

        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
        <td>
            <input type="button" value="Update"
                   onclick="window.location.href = '${updateButton}'">

            <input type="button" value="Delete"
                   onclick="window.location.href = '${deleteButton}'">
        </td>
        </tr>
    </c:forEach>
</table>
<br>
<input type="button" value="Add" onclick="window.location.href='addNewUser'"/>
</body>
</html>