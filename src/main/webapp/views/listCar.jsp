<%@ page import="com.zhdanko.entities.Car" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<h1>List available cars</h1>
<table>
    <tr>
        <th><b>Brand </b></th>
        <th><b>Model  </b></th>
        <th><b>Number  </b></th>
        <th><b>Year of release </b></th>
        <th><b>Cost minute</b></th>
        <th><b>Сondition </b></th>
    </tr>
    <%
        List<Car> cars= (ArrayList<Car>) request.getAttribute("cars");
        for(Car c:cars){%>
    <tr>
        <td><%=c.getBrandName()%></td>
        <td><%=c.getName()%></td>
        <td><%=c.getNumber()%></td>
        <td><%=c.getYearOfRelease()%></td>
        <td><%=c.getCostOfMinute()%></td>
        <td><%=c.getStatusName()%></td>
        <% int id = c.getId();
        %>
        <td>&nbsp;<a href="/clientData?id=<%=id%>">Enter</a></td>
    </tr>
    <%}%>
</table>
<hr/>
<div>
    <button onclick="location.href='/'">Back to main</button>
</div>

</body>
</html>
