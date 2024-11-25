<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1><%= "Home" %>
    </h1>
    <br/>
    <a href="admin/categories">All Categories</a>
    
    <body>
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
    </body>
</body>
</html>

