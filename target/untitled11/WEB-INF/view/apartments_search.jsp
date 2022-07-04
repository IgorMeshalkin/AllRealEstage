<%--<jsp:useBean id="formBean" class="com.igormeshalkin.util.ApartmentFormBean" scope="session"/>--%>
<%--<jsp:setProperty name="formBean" property="*"/>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<form>
    <input type="hidden" value="${mainFilter}" name="mainFilter">
    <table style="text-align: center">
        <tr>
            <th style="background-color: white; border-color: white; font-weight: normal">Address</th>
            <th style="background-color: white; border-color: white; font-weight: normal; text-align: center">Rooms</th>
            <th style="background-color: white; border-color: white; font-weight: normal; text-align: center">Area</th>
            <th style="background-color: white; border-color: white; font-weight: normal; text-align: center">Floor</th>
            <th style="background-color: white; border-color: white; font-weight: normal; text-align: center">Total
                Floors
            </th>
            <th style="background-color: white; border-color: white; font-weight: normal; text-align: center">Price</th>
            <th style="background-color: white; border-color: white; font-weight: normal; text-align: center">Balcony
            </th>
            <th style="background-color: white; border-color: white; font-weight: normal; text-align: center">Sort by
            </th>
        </tr>
        <tr>
            <td rowspan="2" style="border-color: white">
                <input type="text" style="width: 200px" name="address" value="${param.address != null ? param.address : ''}" placeholder="Search">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="roomsMin"
                       value="${param.roomsMin != null ? param.roomsMin : ''}" placeholder="min">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="areaMin"
                       value="${param.areaMin != null ? param.areaMin : ''}" placeholder="min">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="floorMin"
                       value="${param.floorMin != null ? param.floorMin : ''}" placeholder="min">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="totalFloorsMin"
                       value="${param.totalFloorsMin != null ? param.totalFloorsMin : ''}" placeholder="min">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 70px" name="priceMin"
                       value="${param.priceMin != null ? param.priceMin : ''}" placeholder="min">
            </td>
            <td rowspan="2" style="border-color: white">
                Yes <input type="checkbox" name="balconyTrue">
            </td>
            <td rowspan="2" style="border-color: white">
                <select name="sort">
                    <option value="Created">Created</option>
                    <option value="Popular first">Popular first</option>
                    <option value="New ones first">New ones first</option>
                    <option value="Rooms">Rooms</option>
                    <option value="Area">Area</option>
                    <option value="Price">Price</option>
                </select>
            </td>
            <td rowspan="2" style="border-color: white">
                <input type="submit" style="height: 40px; width: 65px; font-weight: bold" value="Search">
            </td>
            <td rowspan="2" style="border-color: white">
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
                <input type="text" style="width: 40px" name="roomsMax"
                       value="${param.roomsMax != null ? param.roomsMax : ''}" placeholder="max">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="areaMax"
                       value="${param.areaMax != null ? param.areaMax : ''}" placeholder="max">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="floorMax"
                       value="${param.floorMax != null ? param.floorMax : ''}" placeholder="max">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="totalFloorsMax"
                       value="${param.totalFloorsMax != null ? param.totalFloorsMax : ''}" placeholder="max">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 70px" name="priceMax"
                       value="${param.priceMax != null ? param.priceMax : ''}" placeholder="max">
            </td>
        </tr>
    </table>
</form>

</body>
</html>

