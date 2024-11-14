<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>News Detail</title>
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

        .comment-box {
            margin-top: 20px;
            padding: 10px;
            background-color: #ffffff;
            border: 1px solid #c8e6c9;
        }
    </style>
</head>
<body>
    <div class="container my-4">
        <h2 class="text-primary">News Detail</h2>

        <!-- News Details Section -->
        <div class="row mb-4">
            <!-- Thumbnail Image -->
            <div class="col-md-4 text-center">
                <img src="${news.thumbnail}" alt="${news.title} Thumbnail" class="img-fluid" style="max-height: 200px"/>
            </div>

            <!-- News Information -->
            <div class="col-md-8">
                <h4>Tiêu đề: ${news.title}</h4>
                <p><strong>Short Description:</strong> ${news.shortDescription}</p>
                <p><strong>Thể loại:</strong> ${news.category.name}</p>
            </div>
        </div>

        <!-- Comments Section -->
        <h5 class="text-success">Comments</h5>
        <div class="comment-box">
            <c:forEach items="${news.comments}" var="comment">
                <div class="border-bottom py-2">
                    <p><strong>${comment.user.username}:</strong> ${comment.content}</p>
                    <p class="text-muted small">Ngày tạo: ${comment.createdDate} | Status: ${comment.status}</p>
                </div>
            </c:forEach>
        </div>

        <!-- Add Comment Form -->
        <div class="mt-4">
            <h5 class="text-success">Form thêm comments</h5>
            <form action="${pageContext.request.contextPath}/comment/add" method="post">
                <input type="hidden" name="newsId" value="${news.id}"/>
                <div class="form-group">
                    <label for="commentContent">Your Comment:</label>
                    <textarea class="form-control" id="commentContent" name="content" rows="3" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary mt-2">Submit</button>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>