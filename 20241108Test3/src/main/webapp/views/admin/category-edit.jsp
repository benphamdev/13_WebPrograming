<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Update Category</title>
</head>
<body>
    <form role="form" action="${pageContext.request.contextPath}/admin/author/edit" method="post"
          enctype="multipart/form-data">
        <input type="text" name="categoryId" hidden="hidden" value="${author.categoryId}">
        <div class="form-group">
            <label>Category name: </label>
            <input class="form-control"
                   placeholder="Please enter author name" name="categoryName" value="${author.categoryName}"/>
        </div>
        <div class="form-group">
            <label>Status: </label>
            <br/>
            <input type="radio" id="active" name="status"
                   value="1" ${author.status == 1 ? 'checked="checked"' : ''} />
            <label for="active">Active</label><br>
            <input type="radio" id="lock" name="status" value="0" ${author.status == 0 ? 'checked="checked"' : ''} />
            <label for="lock">Locked</label>
        </div>
        <div class="form-group">
            <label>Image</label>
            <c:if test="${author.image.length() >= 5 && !author.image.substring(0, 5).equals('https')}">
                <c:url value="/image?filename=${author.image}" var="imgUrl"></c:url>
            </c:if>
            <c:if test="${author.image.length() < 5 || author.image.substring(0, 5).equals('https')}">
                <c:set var="imgUrl" value="${author.image}"/>
            </c:if>
            <img height="150" width="200" src="${imgUrl}"/>
        </div>
        <div>
            <label for="image-link">Change image with url: </label>
            <input type="text" name="imageLink" id="image-link">
        </div>
        <div class="form-group">
            <label>Change image with image file: </label>
            <input type="file" name="image" accept="image/*"/>
        </div>
        <button type="submit" class="btn btn-default">Edit
        </button>
        <button type="reset" class="btn btn-primary">Reset
        </button>
    </form>
</body>
</html>
