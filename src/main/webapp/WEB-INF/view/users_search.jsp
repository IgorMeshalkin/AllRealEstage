<%--<jsp:useBean id="formBean" class="com.igormeshalkin.util.ApartmentFormBean" scope="session"/>--%>
<%--<jsp:setProperty name="formBean" property="*"/>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<body style="font-family: Calibri">
<table style="text-align: center">
    <tr>
        <td rowspan="2" style="border-color: white">
            <form style="float: left; vertical-align:bottom">
                <input type="hidden"
                <c:choose>
                <c:when test="${mainFilter.equals('only_admins')}">
                       value="only_users"
                </c:when>
                <c:when test="${mainFilter.equals('only_users')}">
                       value="all_users"
                </c:when>
                <c:otherwise>
                       value="only_admins"
                </c:otherwise>
                </c:choose>
                       name="mainFilter">
                <input type="submit"
                       style="height:45px;width:100px; white-space: normal; font-weight: bold; margin-right: 10px"
                        <c:choose>
                            <c:when test="${mainFilter.equals('only_admins')}">
                                value="Only USER's"
                            </c:when>
                            <c:when test="${mainFilter.equals('only_users')}">
                                value="All users"
                            </c:when>
                            <c:otherwise>
                                value="Only ADMIN's"
                            </c:otherwise>
                        </c:choose>
                />
            </form>
        </td>


        <form>
            <input type="hidden" value="${mainFilter}" name="mainFilter">

            <th style="background-color: white; border-color: white; font-weight: normal">Search</th>

            <th style="background-color: white; border-color: white; font-weight: normal; text-align: center">Only
                active
            </th>

            <th style="background-color: white; border-color: white; font-weight: normal; text-align: center">Sort by
            </th>
            <td style="border-color: white" rowspan="2">
                <input type="submit" style="height: 40px; width: 65px; font-weight: bold" value="Search">
            </td>
            <td style="border-color: white" rowspan="2">
                <c:choose>
                    <c:when test="${!queryIsEmpty}">
                        <button type="button" style="height: 40px; width: 65px"
                                onclick="window.location.href='${addressForReset}'">Reset
                        </button>
                    </c:when>
                    <c:otherwise>
                        <input type="reset" style="height: 40px; width: 65px" value="Reset">
                    </c:otherwise>
                </c:choose>
            </td>
    </tr>
    <tr>
        <td style="border-color: white">
            <input type="text" style="width: 300px" name="searchLine"
                   value="${param.searchLine != null ? param.searchLine : ''}"
                   placeholder="Enter first name, last name or phone number">
        </td>

        <td style="border-color: white">
            Yes <input type="checkbox" name="onlyActive">
        </td>

        <td style="border-color: white">
            <select name="sort">
                <option value="Created">Created</option>
                <option value="Rating">Rating</option>
                <option value="First name">First name</option>
                <option value="Last name">Last name</option>
            </select>
        </td>
    </tr>
</table>
</form>

</body>
</html>

