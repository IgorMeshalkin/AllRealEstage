<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <tr>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>