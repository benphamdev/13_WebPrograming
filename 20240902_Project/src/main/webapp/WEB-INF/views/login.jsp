<%--
  Created by IntelliJ IDEA.
  User: benpham
  Date: 14/09/2024
  Time: 12.51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <%! String message = "Login page";%>

    <h1><%= message %>
    </h1>

    <form action="auth/login" method="GET">
        Tên: <label>
        <input name="firstName" type="text">
    </label>
        <br/>
        Họ lót: <label>
        <input name="lastName" type="text"/>
    </label>
        <input type="submit" value="Submit"/>
    </form>

    <br/>
    <a href="auth/login">Login</a>
</body>
</html>
