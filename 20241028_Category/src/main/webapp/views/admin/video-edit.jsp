<%--
  Created by IntelliJ IDEA.
  User: phamc
  Date: 10/10/2024
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>Update Video</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
    <br/>
    <a href="${pageContext.request.contextPath}/admin/categories">Back to Category List</a>
    <form role="form" action="${pageContext.request.contextPath}/admin/video/edit" method="post"
          enctype="multipart/form-data">
        <input type="text" name="videoId" hidden="hidden" value="${video.videoId}">
        <input type="text" name="categoryId" hidden="hidden" value="${video.category.categoryId}">
        <div class="form-group">
            <label style="color: red">Category ID: </label>
            <span>${video.category.categoryId}</span>
        </div>
        <div class="form-group">
            <label style="color: red">Category name: </label>
            <span>${video.category.categoryName}</span>
        </div>
        <div class="form-group">
            <label>Video description:</label>
            <input class="form-control"
                   value="${video.description}"
                   required
                   placeholder="Please enter description" name="description"/>
        </div>
        <div class="form-group">
            <label>Status: </label>
            <br/>
            <input type="radio" id="active" name="active" value="1" ${video.active == 1 ? 'checked="checked"' : ''}
                   required/>
            <label for="active">Active</label><br>
            <input type="radio" id="lock" name="active" value="0" ${video.active == 0 ? 'checked="checked"' : ''} />
            <label for="lock">Locked</label>
        </div>
        <div class="form-group">
            <label>Poster: </label>
            <c:set var="imgUrl" value="${video.poster}"/>
            <c:if test="${video.poster.length() >= 5 && !video.poster.substring(0, 5).equals('https')}">
                <c:url value="/image?filename=${video.poster}" var="imgUrl"></c:url>
            </c:if>
            <img height="150" width="200" src="${imgUrl}"/>
        </div>
        <div>
            <label for="image-link">Change poster with url: </label>
            <input type="text" name="imageLink" id="image-link">
        </div>
        <div class="form-group">
            <label>Change poster with image file: </label>
            <input type="file" name="image" accept="image/*"/>
        </div>
        <button type="submit" class="btn btn-default">Edit
        </button>
        <button type="reset" class="btn btn-primary">Reset
        </button>
    </form>
</body>
</html>