<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Product</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="container my-4">
        <h2 class="text-primary">Edit Product</h2>
        <form action="${pageContext.request.contextPath}/admin/product/edit" method="post"
              enctype="application/x-www-form-urlencoded"
        >
            <input type="hidden" name="id" value="${product.productId}"/>

            <!-- Product Name -->
            <div class="form-group mb-3">
                <label>Product Name:</label>
                <input class="form-control" placeholder="Enter product name" name="productName"
                       value="${product.productName}" required/>
            </div>

            <!-- Price -->
            <div class="form-group mb-3">
                <label>Price:</label>
                <input type="number" class="form-control" placeholder="Enter price" name="price"
                       value="${product.price}" step="0.01" min="0" required/>
            </div>

            <!-- Description -->
            <div class="form-group mb-3">
                <label>Description:</label>
                <textarea class="form-control" placeholder="Enter description" name="description" rows="3"
                          required>${product.description}</textarea>
            </div>

            <!-- Stock Amount -->
            <div class="form-group mb-3">
                <label>Stock Amount:</label>
                <input type="number" class="form-control" placeholder="Enter stock quantity" name="amount"
                       value="${product.amount}" min="0" required/>
            </div>

            <!-- Category -->
            <div class="form-group mb-3">
                <label>Category:</label>
                <select class="form-control" name="categoryId" required>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.categoryId}"
                                <c:if test="${category.categoryId == product.category.categoryId}">selected</c:if>>${category.categoryName}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- Seller ID -->
            <div class="form-group mb-3">
                <label>Seller ID:</label>
                <input type="number" class="form-control" placeholder="Enter seller ID" name="sellerId"
                       value="${seller.userId}" min="1" required/>
            </div>

            <!-- Submit and Back Buttons -->
            <button type="submit" class="btn btn-primary">Save Changes</button>
            <a href="${pageContext.request.contextPath}/admin/products" class="btn btn-secondary">Back to Product
                List</a>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
