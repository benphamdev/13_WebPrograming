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
    <title>News Detail</title>
</head>
<body>
    <h1>${book.title}</h1>
    <img src="${book.thumbnail}" alt="Thumbnail"/>
    <p>${book.content}</p>
    <p>Published on: ${book.createdDate}</p>
    <h3>Comments</h3>
    <c:forEach var="rating" items="${commentList}">
        <p><strong>${rating.user.username}</strong>: ${rating.content} - ${rating.createdDate}</p>
    </c:forEach>
    <form action="addComment" method="post">
        <input type="hidden" name="newsId" value="${book.id}"/>
        <textarea name="content"></textarea>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
