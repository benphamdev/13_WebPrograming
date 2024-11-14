<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Book Detail</title>
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

        .rating-box {
            margin-top: 20px;
            padding: 10px;
            background-color: #ffffff;
            border: 1px solid #c8e6c9;
        }
    </style>
</head>
<body>
    <div class="container my-4">
        <h2 class="text-primary">Book Detail</h2>

        <!-- Book Details Section -->
        <div class="row mb-4">
            <!-- Thumbnail Image -->
            <div class="col-md-4 text-center">
                <img
                        src="${pageContext.request.contextPath}/uploads/${book.coverImage}"
                        alt="${book.title} Thumbnail"
                        class="img-fluid"
                        height="80"
                        width="80"
                />
            </div>

            <!-- Book Information -->
            <div class="col-md-8">
                <h4>Title: ${book.title}</h4>
                <p><strong>Short Description:</strong> ${book.description}</p>
                <p><strong>Authors:</strong>
                    <c:forEach items="${book.authors}" var="author">
                        ${author.authorName}<c:if test="${!author.equals(book.authors[book.authors.size() - 1])}">, </c:if>
                    </c:forEach>
                </p>

            <%--                <div class="form-group mb-3">--%>
                <%--                    <label>Price:</label>--%>
                <%--                    <input class="form-control" placeholder="Enter price" name="price" value="${book.price}" required/>--%>
                <%--                </div>--%>

                <%--                <div class="form-group mb-3">--%>
                <%--                    <label>Quantity:</label>--%>
                <%--                    <input class="form-control" placeholder="Enter quantity" name="quantity" value="${book.quantity}"--%>
                <%--                           required/>--%>
                <%--                </div>--%>
            </div>
        </div>

        <!-- Comments Section -->
        <h5 class="text-success">Comments</h5>
        <div class="rating-box">
            <c:forEach items="${book.ratings}" var="rating">
                <div class="border-bottom py-2">
                    <p><strong></strong> ${rating.reviewText}</p>
<%--                    <p class="text-muted small">Created Date: ${rating.createdDate}</p>--%>
                </div>
            </c:forEach>
        </div>

        <!-- Add Comment Form -->
        <div class="mt-4">
            <h5 class="text-success">Add Comment</h5>
            <form action="${pageContext.request.contextPath}/rating/add" method="post">
                <input type="hidden" name="bookId" value="${book.id}"/>
                <input type="hidden" name="rating" value="5"/>
                <div class="form-group">
                    <label for="reviewText">Your Comment:</label>
                    <textarea class="form-control" id="reviewText" name="reviewText" rows="3" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary mt-2">Submit</button>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
