<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Video Detail</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css"/>
    <style>
        body {
            background-color: #e8f5e9;
        }

        .container {
            background-color: #f1f8e9;
            padding: 20px;
            border: 1px solid #c8e6c9;
        }

        .share22162006-box {
            margin-top: 20px;
            padding: 10px;
            background-color: #ffffff;
            border: 1px solid #c8e6c9;
        }
    </style>
</head>
<body>
    <div class="container my-4">
        <h2 class="text-primary">Video Detail</h2>

        <%--        <!-- Video Details Section -->--%>
        <%--        <div class="row mb-4">--%>
        <%--            <!-- Poster Image -->--%>
        <%--            <div class="col-md-4 text-center">--%>
        <%--                <img--%>
        <%--                        src="${pageContext.request.contextPath}/uploads/${video.poster}"--%>
        <%--                        alt="${video.title} Poster"--%>
        <%--                        class="img-fluid"--%>
        <%--                        height="80"--%>
        <%--                        width="80"--%>
        <%--                />--%>
        <%--            </div>--%>

        <%--            <!-- Video Information -->--%>
        <%--            <div class="col-md-8">--%>
        <%--                <h4>ID: ${video.id}</h4>--%>
        <%--                <p><strong>Views:</strong> ${video.views}</p>--%>
        <%--                <p><strong>Category Name:</strong> ${categories.categoryName}</p>--%>
        <%--                <p><strong>Shares:</strong> ${sizeShare}</p>--%>
        <%--                <p><strong>Description:</strong> ${video.description}</p>--%>
        <%--            </div>--%>
        <%--        </div>--%>

        <!-- Video Details Section -->
        <div style="border: 1px solid black; padding: 20px; max-width: 600px; margin: auto;">
            <div style="display: flex; margin-bottom: 20px;">
                <!-- Poster Image -->
                <div style="flex: 1; text-align: center;">
                    <img
                            src="${pageContext.request.contextPath}/uploads/${video.poster}"
                            alt="${video.title} Poster"
                            style="width: 100px; height: 100px; border: 1px solid #ddd; padding: 5px;"
                    />
                </div>

                <!-- Video Information -->
                <div style="flex: 2; padding-left: 20px;">
                    <p><strong>Tiêu đề:</strong> ${video.title}</p>
                    <p><strong>Mã video:</strong> ${video.id}</p>
                    <p><strong>Category name:</strong> ${categories.categoryName}</p>
                    <p><strong>View:</strong> ${video.views}</p>
                    <p><strong>Share:</strong> ${sizeShare}</p>
                    <p><strong>Like:</strong>10</p>
                </div>
            </div>

            <!-- Video Description -->
            <div>
                <p><strong>Description:</strong></p>
                <p>${video.description}</p>
            </div>
        </div>


        <%--        <!-- Comments Section -->--%>
        <%--        <h5 class="text-success">Comments</h5>--%>
        <%--        <div class="share22162006-box">--%>
        <%--            <c:forEach items="${video.comments}" var="comment">--%>
        <%--                <div class="border-bottom py-2">--%>
        <%--                    <p><strong></strong> ${comment.reviewText}</p>--%>
        <%--                </div>--%>
        <%--            </c:forEach>--%>
        <%--        </div>--%>

        <%--        <!-- Add Comment Form -->--%>
        <%--        <div class="mt-4">--%>
        <%--            <h5 class="text-success">Add Comment</h5>--%>
        <%--            <form action="${pageContext.request.contextPath}/comment/add" method="post">--%>
        <%--                <input type="hidden" name="videoId" value="${video.id}"/>--%>
        <%--                <div class="form-group">--%>
        <%--                    <label for="reviewText">Your Comment:</label>--%>
        <%--                    <textarea class="form-control" id="reviewText" name="reviewText" rows="3" required></textarea>--%>
        <%--                </div>--%>
        <%--                <button type="submit" class="btn btn-primary mt-2">Submit</button>--%>
        <%--            </form>--%>
        <%--        </div>--%>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>