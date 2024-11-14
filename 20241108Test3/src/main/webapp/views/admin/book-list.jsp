<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>News List</title>
        <link
            href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <link
            href="${pageContext.request.contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css"
            rel="stylesheet"
            type="text/css"
        />
        <style>
            /* Custom Styles */
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
            }

            .container {
                background-color: #fff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .table th,
            .table td {
                text-align: center;
                vertical-align: middle;
            }

            .table img {
                max-height: 100px;
                width: auto;
            }

            .pagination .page-item.active .page-link {
                background-color: #007bff;
                border-color: #007bff;
            }

            .pagination .page-link {
                color: #007bff;
            }

            .pagination .page-item:hover .page-link {
                background-color: #f8f9fa;
            }

            .btn-group button {
                margin: 0 5px;
            }

            .search-form input[type="text"] {
                margin-right: 10px;
                width: 250px;
            }

            .search-form button {
                min-width: 90px;
            }
        </style>
    </head>
    <body>
        <div class="container my-4">
            <div class="d-flex justify-content-between align-items-center mb-4">
                <h2 class="text-primary">News List</h2>
                <form
                    action="${pageContext.request.contextPath}/admin/book"
                    method="get"
                    class="form-inline search-form"
                >
                    <input
                        type="text"
                        name="title"
                        placeholder="Search by title"
                        class="form-control"
                        value="${param.title}"
                    />
                    <button type="submit" class="btn btn-info">Search</button>
                </form>
            </div>

            <div class="table-responsive" style="margin-top: 12px">
                <table class="table table-bordered table-hover table-striped">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Authors</th>
                            <th>Thumbnail</th>
                            <th>Short Description</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${bookList}" var="book">
                            <tr>
                                <td>${book.id}</td>
                                <td>${book.title}</td>
                                <td>
                                    <c:forEach items="${book.authors}" var="author">
                                        ${author.authorName}<br />
                                    </c:forEach>
                                </td>
                                <td>
                                    <img
                                        src="${pageContext.request.contextPath}/uploads/${book.coverImage}"
                                        alt="${book.title} Thumbnail"
                                        class="img-fluid"
                                        height="80"
                                        width="80"
                                    />
                                </td>
                                <td>${book.description}</td>
                                <td class="btn-group">
                                    <a
                                        href="${pageContext.request.contextPath}/admin/book/details?id=${book.id}"
                                        class="btn btn-sm btn-primary"
                                    >
                                        <i class="fa fa-eye"></i> View
                                    </a>
                                    <a
                                        href="${pageContext.request.contextPath}/admin/book/edit?id=${book.id}"
                                        class="btn btn-sm btn-warning"
                                    >
                                        <i class="fa fa-edit"></i> Edit
                                    </a>
                                    <a
                                        href="${pageContext.request.contextPath}/admin/book/delete?id=${book.id}"
                                        class="btn btn-sm btn-danger"
                                        onclick="return confirm('Are you sure you want to delete this book item?');"
                                    >
                                        <i class="fa fa-trash"></i> Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- Pagination Controls -->
            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item">
                            <a
                                class="page-link"
                                href="${pageContext.request.contextPath}/admin/book?page=${currentPage - 1}&title=${param.title}"
                                aria-label="Previous"
                                >&laquo;</a
                            >
                        </li>
                    </c:if>

                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a
                                class="page-link"
                                href="${pageContext.request.contextPath}/admin/book?page=${i}&title=${param.title}"
                                >${i}</a
                            >
                        </li>
                    </c:forEach>

                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item">
                            <a
                                class="page-link"
                                href="${pageContext.request.contextPath}/admin/book?page=${currentPage + 1}&title=${param.title}"
                                aria-label="Next"
                                >&raquo;</a
                            >
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>

        <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
