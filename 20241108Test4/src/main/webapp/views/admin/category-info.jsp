<%--
  Created by IntelliJ IDEA.
  User: phamc
  Date: 10/10/2024
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Category ${author.categoryId}</title>
</head>
<body>
    <a href="<c:url value="/admin/categories"/>">Back to Category list</a>
    <h4>Category ID: ${author.categoryId}</h4>
    <h4>Category name: ${author.categoryName }</h4>
    <c:set var="imgUrl" value="${author.image}"/>
    <c:if test="${author.image.length() >= 5 && !author.image.substring(0, 5).equals('https')}">
        <c:url value="/image?filename=${author.image}" var="imgUrl"></c:url>
    </c:if>
    <h4>Image:</h4>
    <img height="150" width="200" src="${imgUrl}"/>
    <h4>
        Status:
        <!-- Hiển thị trạng thái dựa trên giá trị status -->
        <c:choose>
            <c:when test="${author.status == 1}">
                Active
            </c:when>
            <c:otherwise>
                Locked
            </c:otherwise>
        </c:choose>
    </h4>
    <h4>Video of this author: </h4>
    <a href="<c:url value="/admin/video22162006/add?categoryId=${author.categoryId}"/>">Add more video22162006 for this
        author</a>
    <table border="1">
        <tr>
            <th style="text-align: center;">ID</th>
            <th style="text-align: center;">Poster</th>
            <th style="text-align: center;">Description</th>
            <th style="text-align: center;">Active</th>
            <th style="text-align: center;">Action</th>
        </tr>
        <c:forEach items="${author.video22162006s}" var="video22162006" varStatus="STT">
            <tr class="odd gradeX">
                <td width="50" style="text-align: center;">${video22162006.videoId}</td>
                <c:set var="imgUrl" value="${video22162006.poster}"/>
                <c:if test="${video22162006.poster.length() >= 5 && !video22162006.poster.substring(0, 5).equals('https')}">
                    <c:url value="/image?filename=${video22162006.poster}" var="imgUrl"></c:url>
                </c:if>
                <td style="text-align: center;"><img height="150" width="200" src="${imgUrl}"/></td>
                <td style="text-align: center;">${video22162006.description}</td>
                <td style="text-align: center;">
                    <!-- Hiển thị trạng thái dựa trên giá trị status -->
                    <c:choose>
                        <c:when test="${video22162006.active == 1}">
                            Active
                        </c:when>
                        <c:otherwise>
                            Locked
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="text-align: center;" width="200">
                    <a style="color: blue" href="<c:url value='/admin/video22162006/edit?id=${video22162006.videoId}'/>"
                       class="center">Edit</a>
                    <a style="color: red"
                       href="<c:url value='/admin/video22162006/delete?id=${video22162006.videoId}'/>"
                       class="center">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>