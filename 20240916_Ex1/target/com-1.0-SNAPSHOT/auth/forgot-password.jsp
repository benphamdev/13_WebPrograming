<%--
  Created by IntelliJ IDEA.
  User: benpham
  Date: 16/09/2024
  Time: 16.06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body>
    <h2>Forgot Password</h2>
    <form action="forgot-password" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br><br>
        <label for="newPassword">New Password:</label>
        <input type="password" id="newPassword" name="newPassword" required><br><br>
        <input type="submit" value="Reset Password">
    </form>
    <a href="login.jsp">Login</a>
</body>
</html>
