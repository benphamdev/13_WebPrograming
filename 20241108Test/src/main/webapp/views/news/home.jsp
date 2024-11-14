<%--
  Created by IntelliJ IDEA.
  User: phamc
  Date: 10/11/2024
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../commons/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>All News</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"/>
</head>
<body>
    <div class="container mt-5">
        <h1>All News</h1>
        <div class="row">
            <c:forEach var="news" items="${newsList}">
                <div class="col-md-4">
                    <div class="card mb-4">
                        <img src="${news.thumbnail}" class="card-img-top" alt="Thumbnail">
                        <div class="card-body">
                            <h5 class="card-title">${news.title}</h5>
                            <p class="card-text">${news.shortDescription}</p>
                            <a href="newsDetail?id=${news.id}" class="btn btn-primary">View Details</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
