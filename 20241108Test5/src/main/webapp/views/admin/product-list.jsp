<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Product List</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet" type="text/css"/>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa;
        }

        .container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .header-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }

        .header-section h2 {
            color: #007bff;
            font-weight: bold;
        }

        .header-section .btn {
            font-size: 14px;
            padding: 8px 20px;
        }

        .search-section {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .search-form input[type="text"] {
            width: 300px;
        }

        .table thead {
            background-color: #007bff;
            color: #ffffff;
        }

        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }

        .table img {
            max-height: 100px;
            width: auto;
            border-radius: 4px;
        }

        .pagination {
            margin-top: 20px;
        }

        .pagination .page-item.active .page-link {
            background-color: #007bff;
            border-color: #007bff;
            color: #ffffff;
        }

        .pagination .page-link {
            color: #007bff;
        }

        .pagination .page-item:hover .page-link {
            background-color: #e9ecef;
        }
    </style>
</head>
<body>
    <div class="container my-4">
        <!-- Header Section -->
        <div class="header-section">
            <h2>Product List</h2>
            <a href="${pageContext.request.contextPath}/admin/product/add" class="btn btn-primary">
                <i class="fa fa-plus"></i> Add Product
            </a>
        </div>

        <!-- Search and Filter Section -->
        <div class="search-section">
            <!-- Search Form -->
            <form action="${pageContext.request.contextPath}/admin/product" method="get"
                  class="search-form d-flex align-items-center">
                <input type="text" name="productName" placeholder="Search by name" class="form-control me-2"
                       value="${param.productName}"/>
                <button type="submit" class="btn btn-info text-white">
                    <i class="fa fa-search"></i> Search
                </button>
            </form>

            <!-- Filter Form -->
            <form action="${pageContext.request.contextPath}/admin/product" method="get"
                  class="d-flex align-items-center">
                <select name="sellerId" class="form-select me-2">
                    <option value="">All UserId</option>
                    <c:forEach items="${users}" var="user">
                        <option value="${user.userId}" ${param.userId == user.userId ? 'selected' : ''}>
                                ${user.username}
                        </option>
                    </c:forEach>
                </select>
                <button type="submit" class="btn btn-info text-white">
                    <i class="fa fa-filter"></i> Filter
                </button>
            </form>
        </div>

        <!-- Product Table -->
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Image</th>
                    <th>Stock</th>
                    <th>Category</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${productList}" var="product">
                    <tr>
                        <td>${product.productId}</td>
                        <td>${product.productName}</td>
                        <td>${product.description}</td>
                        <td>$${product.price}</td>
                        <td>
                            <img src="${pageContext.request.contextPath}/uploads/${product.imageLink}"
                                 alt="${product.productName}" class="img-fluid"/>
                        </td>
                        <td>${product.amount}</td>
                            <%--                        <td>${}</td>--%>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/product/details?id=${product.productId}"
                               class="btn btn-sm btn-primary">
                                <i class="fa fa-eye"></i> View
                            </a>
                            <a href="${pageContext.request.contextPath}/admin/product/edit?id=${product.productId}"
                               class="btn btn-sm btn-warning">
                                <i class="fa fa-edit"></i> Edit
                            </a>
                            <a href="${pageContext.request.contextPath}/admin/product/delete?id=${product.productId}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('Are you sure you want to delete this product?');">
                                <i class="fa fa-trash"></i> Delete
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Pagination -->
        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/admin/product?page=${currentPage - 1}&size=${pageSize}"
                           aria-label="Previous">&laquo;</a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/admin/product?page=${i}&size=${pageSize}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/admin/product?page=${currentPage + 1}&size=${pageSize}"
                           aria-label="Next">&raquo;</a>
                    </li>
                </c:if>
            </ul>
        </nav>

        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">Total Product: ${totalProduct}</h2>
        </div>

    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
