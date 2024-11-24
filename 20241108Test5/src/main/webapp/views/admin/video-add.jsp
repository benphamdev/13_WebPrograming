<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Video</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container my-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">Add Video</h2>
            <a href="${pageContext.request.contextPath}/admin/video" class="btn btn-secondary">Back to Video List</a>
        </div>

        <form role="form" action="${pageContext.request.contextPath}/admin/video/add" method="post"
              enctype="multipart/form-data">
            <!-- Title Field -->
            <div class="form-group mb-3">
                <label>Title:</label>
                <input class="form-control" placeholder="Enter title" name="title" required/>
            </div>

            <!-- Views Field -->
            <div class="form-group mb-3">
                <label>Views:</label>
                <input type="number" class="form-control" placeholder="Enter views" name="views" required/>
            </div>

            <!-- Description Field -->
            <div class="form-group mb-3">
                <label>Description:</label>
                <textarea class="form-control" placeholder="Enter description" name="description" rows="3"
                          required></textarea>
            </div>

            <!-- Active Field -->
            <div class="form-group mb-3">
                <label>Active:</label>
                <select class="form-control" name="isActive" required>
                    <option value="true">Yes</option>
                    <option value="false">No</option>
                </select>
            </div>

            <%--            <!-- Authors Field -->--%>
            <%--            <div class="form-group mb-3">--%>
            <%--                <label>Authors:</label>--%>
            <%--                <select class="form-control" name="authorIds[]" multiple required>--%>
            <%--                    <c:forEach items="${authors}" var="author">--%>
            <%--                        <option value="${author.id}">${author.authorName}</option>--%>
            <%--                    </c:forEach>--%>
            <%--                </select>--%>
            <%--                <small class="form-text text-muted">Hold Ctrl (Windows) or Command (Mac) to select multiple--%>
            <%--                    authors.</small>--%>
            <%--            </div>--%>

            <!-- Category ID Field -->
            <div class="form-group mb-3">
                <label>Category ID:</label>
                <select class="form-control" name="categoryId" required>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.categoryName}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Cover Image Link Field -->
            <div class="form-group mb-3">
                <label>Import Cover Image using Link:</label>
                <input type="text" class="form-control" name="coverImageLink" placeholder="Enter image URL"/>
            </div>


            <!-- Poster File Field -->
            <div class="form-group mb-3">
                <label>Poster:</label>
                <input type="file" class="form-control-file" name="poster" accept="image/*"/>
            </div>

            <!-- Submit and Reset Buttons -->
            <button type="submit" class="btn btn-primary">Add Video</button>
            <button type="reset" class="btn btn-secondary">Reset</button>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
