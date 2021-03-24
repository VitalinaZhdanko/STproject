<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm</title>
</head>
<body>
    <table>
        <%
            String status = (String) request.getAttribute("status");
            String comment = (String) request.getAttribute("comment");
            if (comment == null) {
                comment = "Expect confirm";
            }
        %>
        <tr>
            <td>Statuc order</td>
            <td><%=status%></td>
        </tr>
        <tr>
            <td>Comment</td>
            <td><%=comment%></td>
        </tr>
    </table>
</body>
</html>
