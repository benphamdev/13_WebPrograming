<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Administrator's Home Page</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
    <style>
        .container {
            margin-top: 50px;
            max-width: 600px;
        }

        .alert {
            padding: 20px;
        }

        .btn {
            margin: 10px 5px;
        }
    </style>
</head>

<body>
<div class="container mt-4">
    <c:choose>
        <c:when test="${sessionScope.account == null}">
            <div class="alert alert-warning text-center">
                <h3>You haven't logged in, please login or register</h3>
                <div>
                    <a href="${pageContext.request.contextPath}/auth/login" class="btn btn-primary">Login</a>
                    <a href="${pageContext.request.contextPath}/auth/register"
                       class="btn btn-secondary">Register</a>
                </div>
            </div>
        </c:when>

        <%--        <c:when test="${sessionScope.account.isAdmin == false}">--%>
        <%--            <div class="alert alert-danger text-center">--%>
        <%--                <h3>You don't have administrator role access</h3>--%>
        <%--            </div>--%>
        <%--        </c:when>--%>


        <c:otherwise>
            <div class="alert alert-success text-center">
                <h3>Welcome to the Administrator's Home Page</h3>
            </div>
            <div class="text-center">
                <a href="${pageContext.request.contextPath}/member/my-account" class="btn btn-info">
                    My Account: ${sessionScope.account.fullname}
                </a>
                <a href="${pageContext.request.contextPath}/auth/logout" class="btn btn-danger">Logout</a>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
