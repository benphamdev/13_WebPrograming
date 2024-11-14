<%-- Created by IntelliJ IDEA. User: phamc Date: 10/10/2024 Time: 15:05 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add News</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container my-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">Add News</h2>
            <a href="${pageContext.request.contextPath}/admin/news" class="btn btn-secondary">Back to News List</a>
        </div>

        <form role="form" action="${pageContext.request.contextPath}/admin/news/add" method="post"
              enctype="multipart/form-data">
            <div class="form-group mb-3">
                <label>Category:</label>
                <select class="form-control" name="categoryId" required>
                    <option value="">Select Category</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group mb-3">
                <label>News Title:</label>
                <input class="form-control" placeholder="Enter title" name="title" required/>
            </div>

            <div class="form-group mb-3">
                <label>Short Description:</label>
                <textarea class="form-control" placeholder="Enter short description" name="shortDescription" rows="3"
                          required></textarea>
            </div>

            <div class="form-group mb-3">
                <label>Content:</label>
                <textarea class="form-control" placeholder="Enter content" name="content" rows="5" required></textarea>
            </div>

            <div class="form-group mb-3">
                <label>Status:</label>
                <div>
                    <input type="radio" id="active" name="active" value="1" required>
                    <label for="active">Active</label>
                    <input type="radio" id="inactive" name="active" value="0">
                    <label for="inactive">Inactive</label>
                </div>
            </div>

            <div class="form-group mb-3">
                <label for="image-link">Import Thumbnail using Link:</label>
                <input type="text" class="form-control" name="imageLink" id="image-link" placeholder="Enter image URL"/>
            </div>

            <div class="form-group mb-3">
                <label>Import Thumbnail using File:</label>
                <input type="file" class="form-control-file" name="thumbnail" accept="image/*"/>
            </div>

            <button type="submit" class="btn btn-primary">Add News</button>
            <button type="reset" class="btn btn-secondary">Reset</button>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
