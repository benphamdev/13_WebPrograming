<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Book</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container my-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary">Add Book</h2>
        <a href="${pageContext.request.contextPath}/admin/book" class="btn btn-secondary">Back to Book List</a>
    </div>

    <form role="form" action="${pageContext.request.contextPath}/admin/book/add" method="post"
          enctype="multipart/form-data">
        <!-- ISBN Field -->
        <div class="form-group mb-3">
            <label>ISBN:</label>
            <input class="form-control" placeholder="Enter ISBN" name="isbn" required/>
        </div>

        <!-- Title Field -->
        <div class="form-group mb-3">
            <label>Title:</label>
            <input class="form-control" placeholder="Enter title" name="title" required/>
        </div>

        <!-- Publisher Field -->
        <div class="form-group mb-3">
            <label>Publisher:</label>
            <input class="form-control" placeholder="Enter publisher" name="publisher" required/>
        </div>

        <!-- Price Field -->
        <div class="form-group mb-3">
            <label>Price:</label>
            <input type="number" step="0.01" class="form-control" placeholder="Enter price" name="price" required/>
        </div>

        <!-- Description Field -->
        <div class="form-group mb-3">
            <label>Description:</label>
            <textarea class="form-control" placeholder="Enter description" name="description" rows="3"
                      required></textarea>
        </div>

        <!-- Quantity Field -->
        <div class="form-group mb-3">
            <label>Quantity:</label>
            <input type="number" class="form-control" placeholder="Enter quantity" name="quantity" required/>
        </div>

        <!-- Authors Field -->
        <div class="form-group mb-3">
            <label>Authors:</label>
            <select class="form-control" name="authorIds" multiple required>
                <c:forEach items="${authors}" var="author">
                    <option value="${author.id}">${author.authorName}</option>
                </c:forEach>
            </select>
            <small class="form-text text-muted">Hold Ctrl (Windows) or Command (Mac) to select multiple
                authors.</small>
        </div>

        <!-- Cover Image Link Field -->
        <div class="form-group mb-3">
            <label>Import Cover Image using Link:</label>
            <input type="text" class="form-control" name="coverImageLink" placeholder="Enter image URL"/>
        </div>

        <!-- Cover Image File Field -->
        <div class="form-group mb-3">
            <label>Import Cover Image using File:</label>
            <input type="file" class="form-control-file" name="coverImageFile" accept="image/*"/>
        </div>

        <!-- Submit and Reset Buttons -->
        <button type="submit" class="btn btn-primary">Add Book</button>
        <button type="reset" class="btn btn-secondary">Reset</button>
    </form>
</div>

<script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
