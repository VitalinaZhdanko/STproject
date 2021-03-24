<%@ page import="com.zhdanko.entities.CarGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.zhdanko.entities.Order" %>
<%@ page import="com.zhdanko.entities.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
<h1>Order</h1>
<form action="confirmClientData" method="post">
<table>
    <%
        Client client = (Client) request.getAttribute("client");
        %>
    <tr>
        <td><%=client.getEmail()%></td>
    </tr>
    <tr>
        <td><%=client.getPhoneNumber()%></td>
    </tr>
    <tr>
        <td><%=client.getPassportNumber()%></td>
    </tr>
    <tr>
        <td><%=client.getIdentificationNumber()%></td>
    </tr>
    <tr>
        <td><%=client.getDateOfIssue()%></td>
    </tr>
    <tr>
        <td><%=client.getValidityPeriod()%></td>
    </tr>
    <tr>
        <td><%=client.getIssuedByWhom()%></td>
    </tr>
    <tr>
        <td>Comment</td>
        <td><input type="text" name="comment" /></td>
    </tr>
</table>
    <input type="submit" value="Confirm" formaction="confirmClientData?act=submit"/>
    <input type="submit" value="Cancel" formaction="confirmClientData?act=cancel"/>
</form>

</body>
</html>
