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
    <title>Add video</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
    <br/>
    <a href="${pageContext.request.contextPath}/admin/categories">Back to Category List</a>
    <form role="form" action="${pageContext.request.contextPath}/admin/video/insert" method="post"
          enctype="multipart/form-data">
        <div class="form-group">
            <label>Category ID:</label>
            <input class="form-control"
                   required
                   value="${categoryId}"
                   placeholder="Please enter Category ID" name="categoryId"/>
        </div>
        <div class="form-group">
            <label>Video description:</label>
            <input class="form-control"
                   placeholder="Please enter description" name="description" required/>
        </div>
        <div class="form-group">
            <label>Status: </label>
            <br/>
            <input type="radio" id="active" name="active" value="1" required>
            <label for="active">Active</label><br>
            <input type="radio" id="lock" name="active" value="0">
            <label for="lock">Locked</label>
        </div>
        <div>
            <label for="image-link">Import image using link: </label>
            <input type="text" name="imageLink" id="image-link">
        </div>
        <div class="form-group">
            <label>Import image using image file: </label>
            <input type="file" name="image" accept="image/*"/>
        </div>
        <button type="submit" class="btn btn-default">Add
        </button>
        <button type="reset" class="btn btn-primary">Reset
        </button>
    </form>
</body>
</html>