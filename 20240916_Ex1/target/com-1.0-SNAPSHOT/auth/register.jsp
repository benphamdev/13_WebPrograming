<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: benpham--%>
<%--  Date: 16/09/2024--%>
<%--  Time: 16.06--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Register</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--    <h2>Register</h2>--%>
<%--    <form action="register" method="post">--%>
<%--        <label for="username">Username:</label>--%>
<%--        <input type="text" id="username" name="username" required><br><br>--%>
<%--        <label for="password">Password:</label>--%>
<%--        <input type="password" id="password" name="password" required><br><br>--%>
<%--        <input type="submit" value="Register">--%>
<%--    </form>--%>
<%--    <a href="login.jsp">Login</a>--%>
<%--</body>--%>
<%--</html>--%>


%@ page language="java" contentType="text/html; charset=ISO-885

9-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<div align="center">
    <h1>Employee Register Form</h1>
    <form action="<%= request.getContextPath() %>/register" method="post">
        <table style="with: 80%">
            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstName"/></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastName"/></td>
            </tr>
            <tr>
                <td>UserName</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>Address</td>
                <td><input type="text" name="address"/></td>
            </tr>
            <tr>
                <td>Contact No</td>
                <td><input type="text" name="contact"/></td>
            </tr>
        </table>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>