<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Categories List</title>
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
            <h2>Categories List</h2>
            <a href="${pageContext.request.contextPath}/admin/category/add" class="btn btn-primary">
                <i class="fa fa-plus"></i> Add Category
            </a>
        </div>

        <!-- Category Table -->
        <div class="table-responsive">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Image</th>
                    <th>Category Name</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${categoryList}" var="cate">
                    <tr>
                        <td>${cate.categoryId}</td>
                            <%--                        <td>--%>
                            <%--                            <img src="${pageContext.request.contextPath}/uploads/${cate.image}"--%>
                            <%--                                 alt="${cate.categoryName}"--%>
                            <%--                                 class="img-fluid"/>--%>
                            <%--                        </td>--%>
                        <td>${cate.categoryName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${cate.isActive == true}">
                                    Active
                                </c:when>
                                <c:otherwise>
                                    Locked
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/category/edit?id=${cate.categoryId}"
                               class="btn btn-sm btn-warning">
                                <i class="fa fa-edit"></i> Edit
                            </a>
                            <a href="${pageContext.request.contextPath}/admin/category/delete?id=${cate.categoryId}"
                               class="btn btn-sm btn-danger"
                               onclick="return confirm('Are you sure you want to delete this category?');">
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
                           href="${pageContext.request.contextPath}/admin/categories?page=${currentPage - 1}"
                           aria-label="Previous">&laquo;</a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/admin/categories?page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/admin/categories?page=${currentPage + 1}"
                           aria-label="Next">&raquo;</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
