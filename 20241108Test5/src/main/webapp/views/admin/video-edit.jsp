<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Book</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container my-4">
        <h2 class="text-primary">Edit Video</h2>
        <form action="${pageContext.request.contextPath}/admin/video/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${video.id}"/>

            <div class="form-group mb-3">
                <label>Title:</label>
                <input class="form-control" placeholder="Enter title" name="title" value="${video.title}" required/>
            </div>

            <div class="form-group mb-3">
                <label>Poster:</label>
                <input type="file" class="form-control-file" name="poster" accept="image/*"/>
            </div>

            <div class="form-group mb-3">
                <label>Views:</label>
                <input class="form-control" placeholder="Enter views" name="views" value="${video.views}" required/>
            </div>

            <div class="form-group mb-3">
                <label>Description:</label>
                <textarea class="form-control" placeholder="Enter description" name="description" rows="3"
                          required>${video.description}</textarea>
            </div>

            <div class="form-group mb-3">
                <label>Active:</label>
                <select class="form-control" name="isActive" required>
                    <option value="true" <c:if test="${video.active}">selected</c:if>>Yes</option>
                    <option value="false" <c:if test="${!video.active}">selected</c:if>>No</option>
                </select>
            </div>

            <div class="form-group mb-3">
                <label>Category ID:</label>
                <select class="form-control" name="categoryId" required>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}"
                                <c:if test="${category.id == video.category.id}">selected</c:if>>${category.categoryName}</option>
                    </c:forEach>
                </select>
            </div>

            <button type="submit" class="btn btn-primary">Save Changes</button>
            <a href="${pageContext.request.contextPath}/admin/video" class="btn btn-secondary">Back to Video List</a>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
