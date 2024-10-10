<%--
  Created by IntelliJ IDEA.
  User: phamc
  Date: 10/10/2024
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Videos list</title>
</head>

<body>
    <a href="${pageContext.request.contextPath}">Back to Home</a>
    <br/>
    <a href="${pageContext.request.contextPath}/admin/video/add">Add more video</a>
    <table border="1">
        <tr>
            <th style="text-align: center;">ID</th>
            <th style="text-align: center;">Category ID</th>
            <th style="text-align: center;">Poster</th>
            <th style="text-align: center;">Description</th>
            <th style="text-align: center;">Active</th>
            <th style="text-align: center;">Action</th>
        </tr>
        <c:forEach items="${videoList}" var="video" varStatus="STT">
            <tr class="odd gradeX">
                <td width="50" style="text-align: center;">${video.videoId}</td>
                <td style="text-align: center;"><a
                        href="<c:url value='/admin/category?id=${video.category.categoryId}'/>"
                        class="center">${video.category.categoryId}</a></td>
                <c:set var="imgUrl" value="${video.poster}"/>
                <c:if test="${video.poster.length() >= 5 && !video.poster.substring(0, 5).equals('https')}">
                    <c:url value="/image?filename=${video.poster}" var="imgUrl"></c:url>
                </c:if>
                <td style="text-align: center;"><img height="150" width="200" src="${imgUrl}"/></td>
                <td style="text-align: center;">${video.description}</td>
                <td style="text-align: center;">
                    <!-- Hiển thị trạng thái dựa trên giá trị status -->
                    <c:choose>
                        <c:when test="${video.active == 1}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Locked
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="text-align: center;" width="200">
                    <a style="color: blue" href="<c:url value='/admin/video/edit?id=${video.videoId}'/>" class="center">Edit</a>
                    <a style="color: red;" href="<c:url value='/admin/video/delete?id=${video.videoId}'/>"
                       class="center">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>