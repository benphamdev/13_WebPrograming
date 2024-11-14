<%-- Created by IntelliJ IDEA. User: phamc Date: 10/10/2024 Time: 15:05 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit News</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container my-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">Edit News</h2>
            <a href="${pageContext.request.contextPath}/admin/news" class="btn btn-secondary">Back to News List</a>
        </div>

        <form role="form" action="${pageContext.request.contextPath}/admin/news/edit" method="post"
              enctype="multipart/form-data">
            <!-- Hidden field to hold the news ID -->
            <input type="hidden" name="id" value="${news.id}"/>

            <div class="form-group mb-3">
                <label>Category:</label>
                <select class="form-control" name="categoryId" required>
                    <option value="">Select Category</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}" ${category.id == news.category.id ? "selected" : ""}>
                                ${category.name}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group mb-3">
                <label>News Title:</label>
                <input class="form-control" placeholder="Enter title" name="title" value="${news.title}" required/>
            </div>

            <div class="form-group mb-3">
                <label>Short Description:</label>
                <textarea class="form-control" placeholder="Enter short description" name="shortDescription" rows="3"
                          required>${news.shortDescription}</textarea>
            </div>

            <div class="form-group mb-3">
                <label>Content:</label>
                <textarea class="form-control" placeholder="Enter content" name="content" rows="5"
                          required>${news.content}</textarea>
            </div>

            <div class="form-group mb-3">
                <label>Thumbnail:</label>
                <div class="mb-2">
                    <c:set var="imgUrl" value="${news.thumbnail}"/>
                    <c:if test="${news.thumbnail.length() >= 5 && !news.thumbnail.substring(0, 5).equals('https')}">
                        <c:url value="/image?filename=${news.thumbnail}" var="imgUrl"/>
                    </c:if>
                    <img src="${imgUrl}" alt="${news.title} Thumbnail" class="img-thumbnail"
                         style="max-height: 100px;"/>
                </div>
            </div>

            <div class="form-group mb-3">
                <label for="image-link">Change Thumbnail using Link:</label>
                <input type="text" class="form-control" name="imageLink" id="image-link"
                       placeholder="Enter new image URL"/>
            </div>

            <div class="form-group mb-3">
                <label>Change Thumbnail using File:</label>
                <input type="file" class="form-control-file" name="thumbnail" accept="image/*"/>
            </div>

            <button type="submit" class="btn btn-primary">Save Changes</button>
            <button type="reset" class="btn btn-secondary">Reset</button>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
