<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <%! String message = "Index page";%>

    <h1><%= message %>
    </h1>

    <%--    <form action="auth/login" method="GET">--%>
    <%--        Tên: <label>--%>
    <%--        <input name="firstName" type="text">--%>
    <%--    </label>--%>
    <%--        <br/>--%>
    <%--        Họ lót: <label>--%>
    <%--        <input name="lastName" type="text"/>--%>
    <%--    </label>--%>
    <%--        <input type="submit" value="Submit"/>--%>
    <%--    </form>--%>

    <%--    <br/>--%>
    <a href="auth/login">Login</a>
</body>
</html>
