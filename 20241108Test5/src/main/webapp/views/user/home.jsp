<%--
  Created by IntelliJ IDEA.
  User: benpham
  Date: 19/09/2024
  Time: 15.00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <c:choose>
        <%-- All users who have logged in can access this page  --%>
        <c:when test="${sessionScope.account == null}">
            <div class="homepage">
                <h1>You haven't login, please login or register</h1>
            </div>
            <div class="col-sm-6">
                <ul class="list-inline right-topbar pull-right">
                    <li>
                        <a href="${pageContext.request.contextPath }/auth/login">Login</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath }/auth/register">Register</a>
                    </li>
                    <li>
                        <i class="search fa fa-search search-button"></i>
                    </li>
                </ul>
            </div>
        </c:when>
        <c:otherwise>
            <div class="homepage">
                <h1>Home Page</h1>
            </div>
            <div class="col-sm-6">
                <ul class="list-inline right-topbar pull-right">
                    <li>
                        <a href="${pageContext.request.contextPath}/member/my-account">My
                            account: ${sessionScope.account.username}</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath }/logout">Logout</a>
                    </li>
                </ul>


                <h1>Top 10 Best Sellers</h1>
                <ul>
                    <c:forEach items="${topSellers}" var="product">
                        <li>${product.name} - Sold: ${product.nSold}</li>
                    </c:forEach>
                </ul>

                <h1>Top 10 Most Viewed</h1>
                <ul>
                    <c:forEach items="${mostViewed}" var="product">
                        <li>${product.name} - Views: ${product.nVisit}</li>
                    </c:forEach>
                </ul>

                <h1>Top 10 Most Liked</h1>
                <ul>
                    <c:forEach items="${mostLiked}" var="product">
                        <li>${product.name} - Likes:
                            <c:out value="${product.comments.size()}"/>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </c:otherwise></c:choose>
</body>
</html>

