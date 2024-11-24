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
    <h1>${video.title}</h1>
    <img src="${video.thumbnail}" alt="Thumbnail"/>
    <p>${video.content}</p>
    <p>Published on: ${video.createdDate}</p>
    <h3>Comments</h3>
    <c:forEach var="share" items="${commentList}">
        <p><strong>${share.user.username}</strong>: ${share.content} - ${share.createdDate}</p>
    </c:forEach>
    <form action="addComment" method="post">
        <input type="hidden" name="newsId" value="${video.id}"/>
        <textarea name="content"></textarea>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
