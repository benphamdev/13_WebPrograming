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
        <h2 class="text-primary">Edit Book</h2>
        <form action="${pageContext.request.contextPath}/admin/book/edit" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${book.id}"/>

            <div class="form-group mb-3">
                <label>Author:</label>
                <select class="form-control" name="authorIds" multiple required>
                    <c:forEach items="${authors}" var="author">
                        <option value="${author.id}"
                                <c:forEach var="selectedAuthor" items="${book.authors}">
                                    <c:if test="${author.id == selectedAuthor.id}">selected</c:if>
                                </c:forEach>>
                                ${author.authorName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group mb-3">
                <label>Price:</label>
                <input class="form-control" placeholder="Enter price" name="price" value="${book.price}" required/>
            </div>

            <div class="form-group mb-3">
                <label>Quantity:</label>
                <input class="form-control" placeholder="Enter quantity" name="quantity" value="${book.quantity}"
                       required/>
            </div>

            <div class="form-group mb-3">
                <label>Title:</label>
                <input class="form-control" placeholder="Enter title" name="title" value="${book.title}" required/>
            </div>

            <div class="form-group mb-3">
                <label>Description:</label>
                <textarea class="form-control" placeholder="Enter description" name="description" rows="3"
                          required>${book.description}</textarea>
            </div>

            <div class="form-group mb-3">
                <label for="image-link">Thumbnail URL:</label>
                <input type="text" class="form-control" name="imageLink" id="image-link"
                       placeholder="Enter new image URL" value="${book.coverImage}"/>
            </div>

            <div class="form-group mb-3">
                <label>Change Thumbnail using File:</label>
                <input type="file" class="form-control-file" name="thumbnail" accept="image/*"/>
            </div>

            <button type="submit" class="btn btn-primary">Save Changes</button>
            <a href="${pageContext.request.contextPath}/admin/book" class="btn btn-secondary">Back to Book List</a>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
