<%--<jsp:useBean id="formBean" class="com.igormeshalkin.util.ApartmentFormBean" scope="session"/>--%>
<%--<jsp:setProperty name="formBean" property="*"/>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<form>
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
        <%--            value="<%=formBean.getRoomsMin()%>"--%>
        <tr>
            <td rowspan="2" style="border-color: white">
                <input type="text" style="width: 200px" name="search" placeholder="Search">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="roomsMin" placeholder="min">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="areaMin" placeholder="min">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="floorMin" placeholder="min">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="totalFloorsMin" placeholder="min">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 70px" name="priceMin" placeholder="min">
            </td>
            <td rowspan="2" style="border-color: white">
                Yes <input type="checkbox" name="balconyTrue">
            </td>
            <td rowspan="2" style="border-color: white">
                <select name="sort">
                    <option value="Created">Created</option>
                    <option value="New ones first">New ones first</option>
                    <option value="Rooms">Rooms</option>
                    <option value="Area">Area</option>
                    <option value="Price">Price</option>
                </select>
            </td>
            <td rowspan="2" style="border-color: white">
                <input type="submit" style="height: 40px; width: 65px; font-weight: bold" value="Search">
            </td>
        </tr>
        <tr>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="roomsMax" placeholder="max">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="areaMax" placeholder="max">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="floorMax" placeholder="max">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 40px" name="totalFloorsMax" placeholder="max">
            </td>
            <td style="border-color: white">
                <input type="text" style="width: 70px" name="priceMax" placeholder="max">
            </td>
        </tr>
    </table>
</form>

</body>
</html>

