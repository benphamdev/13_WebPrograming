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
    <h1>${video22162006.title}</h1>
    <img src="${video22162006.thumbnail}" alt="Thumbnail"/>
    <p>${video22162006.content}</p>
    <p>Published on: ${video22162006.createdDate}</p>
    <h3>Comments</h3>
    <c:forEach var="share22162006" items="${commentList}">
        <p><strong>${share22162006.user22162006.username}</strong>: ${share22162006.content}
            - ${share22162006.createdDate}</p>
    </c:forEach>
    <form action="addComment" method="post">
        <input type="hidden" name="newsId" value="${video22162006.id}"/>
        <textarea name="content"></textarea>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
