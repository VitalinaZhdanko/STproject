<%@ page import="com.zhdanko.entities.CarGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
<h1>List category</h1>
<table>
    <tr>
        <th><b>Name     </b></th>
        <th><b>Description  </b></th>
        <th><b> </b></th>
    </tr>
    <%
        List<CarGroup> groupList = (ArrayList<CarGroup>) request.getAttribute("groups");
        for(CarGroup group:groupList){%>
    <tr>
        <td><%=group.getName()%></td>
        <td><%=group.getDescription()%></td>
        <% int id = group.getId(); %>
        <td>&nbsp;&nbsp;<a href="/listCar?id=<%=id%>">Enter</a></td>
    </tr>
    <%}%>
</table>
<hr/>
<div>
    <button onclick="location.href='/'">Back to main</button>
</div>

</body>
</html>
