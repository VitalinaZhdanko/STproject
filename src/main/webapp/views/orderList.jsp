<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.zhdanko.entities.Order" %>

<html>
<head>
    <title>Orders</title>
    <meta charset="UTF-8" content="text/html">
</head>
<body>
<h1>List order</h1>
<table>
    <tr>
        <th><b>Name      </b></th>
        <th><b>Description    </b></th>
        <th><b> </b></th>
    </tr>
    <%
        List<Order> orderList = (ArrayList<Order>) request.getAttribute("orders");
        int nOfPages = (int) request.getAttribute("nOfPages");
        int currentPage = (int) request.getAttribute("currentPage");
        int recordsPerPage = (int) request.getAttribute("recordsPerPage");

        for(Order order:orderList){%>
    <tr>
        <td><%=order.getCost()%></td>
        <td><%=order.getDateTimeStartRent()%></td>
        <td><%=order.getDateTimeFinishRent()%></td>
        <% int id = order.getId(); %>
        <td>&nbsp;&nbsp;<a href="/confirmClientData?id=<%=id%>">Enter</a></td>
    </tr>
    <%}%>
</table>

<%
    if(currentPage != 1){ %>
<a href="admin?name=admin&recordsPerPage=<%=recordsPerPage%>&currentPage=<%=currentPage-1%>">Previous</a>
<%}%>

<%
    if(currentPage < nOfPages){ %>
<a href="admin?name=admin&recordsPerPage=<%=recordsPerPage%>&currentPage=<%=currentPage+1%>">Next</a>
<%}%>
</body>
</html>
