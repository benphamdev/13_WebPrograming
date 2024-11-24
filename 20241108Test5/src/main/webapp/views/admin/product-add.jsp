<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add Product</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container my-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">Add Product</h2>
            <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-secondary">Back to Product
                List</a>
        </div>

        <form role="form" action="${pageContext.request.contextPath}/admin/product/add" method="post"
              enctype="multipart/form-data"
        >
            <!-- Product Name Field -->
            <div class="form-group mb-3">
                <label>Product Name:</label>
                <input class="form-control" placeholder="Enter product name" name="productName" required/>
            </div>

            <!-- Price Field -->
            <div class="form-group mb-3">
                <label>Price:</label>
                <input type="number" class="form-control" placeholder="Enter price" name="price" step="0.01" min="0"
                       required/>
            </div>

            <!-- Description Field -->
            <div class="form-group mb-3">
                <label>Description:</label>
                <textarea class="form-control" placeholder="Enter description" name="description" rows="3"
                          required></textarea>
            </div>

            <!-- Stock Field -->
            <div class="form-group mb-3">
                <label>Stock:</label>
                <input class="form-control" placeholder="Enter stock quantity" name="amount" min="0"
                       required/>
            </div>

            <!-- Category Field -->
            <div class="form-group mb-3">
                <label>Category:</label>
                <select class="form-control" name="categoryId" required>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.categoryId}">${category.categoryName}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Seller ID Field -->
            <div class="form-group mb-3">
                <label>Seller ID:</label>
                <input type="number" class="form-control" placeholder="Enter seller ID" name="sellerId" min="1"
                       required/>
            </div>

            <!-- Product Image Field -->
            <div class="form-group mb-3">
                <label>Product Image:</label>
                <input type="file" class="form-control-file" name="imageLink" accept="image/*"/>
            </div>

            <!-- Submit and Reset Buttons -->
            <button type="submit" class="btn btn-primary">Add Product</button>
            <button type="reset" class="btn btn-secondary">Reset</button>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
