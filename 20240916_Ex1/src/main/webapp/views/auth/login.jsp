<%--
  Created by IntelliJ IDEA.
  User: benpham
  Date: 16/09/2024
  Time: 16.06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/auth/login.css">
</head>

<body>
    <div class="main">
        <h1>Welcome</h1>
        <h5 style="color: mediumseagreen">
            <%
                // Get notify from session attribute
                Object success = request.getSession().getAttribute("success");
                if (success != null) {
                    out.print(success);
                    request.getSession().removeAttribute("success");
                }
            %>
        </h5>
        <form action="login" method="POST">
            <label for="username">
            </label>
            <input class="input-custom" type="text"
                   id="username"
                   name="username"
                   placeholder="Email address or username" required>

            <label for="password">
            </label>
            <input class="input-custom" type="password"
                   id="password"
                   name="password"
                   placeholder="Password" required>

            <div class="checkbox-group">
                <input type="checkbox" id="remember" name="remember">
                <label for="remember">Remember me</label>
            </div>

            <div class="wrap">
                <button type="submit"
                        onclick="solve()">
                    Log in
                </button>
            </div>
        </form>
        <h5 style="color: red">
            <%
                Object alert = request.getAttribute("alert");
                if (alert != null) {
                    out.print(alert);
                }
            %>
        </h5>
        <p>
            <a href="forgot-password">
                Forgotten password?
            </a>
        </p>
        <p>Not registered?
            <a href="register">
                Create new account
            </a>
        </p>
    </div>
</body>
</html>
