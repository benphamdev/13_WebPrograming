<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
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
</head>
<body>
    <div class="container my-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="text-primary">News List</h2>
            <form action="${pageContext.request.contextPath}/admin/news" method="get" class="form-inline">
                <input
                        type="text"
                        name="title"
                        placeholder="Search by title"
                        class="form-control mr-2"
                        value="${param.title}"
                />
                <button type="submit" class="btn btn-info">Search</button>
            </form>
        </div>

        <div class="table-responsive">
            <table class="table table-bordered table-hover table-striped">
                <thead class="thead-dark">
                <tr>
                    <th style="text-align: center">ID</th>
                    <th style="text-align: center">Title</th>
                    <th style="text-align: center">Category</th>
                    <th style="text-align: center">Thumbnail</th>
                    <th style="text-align: center">Short Description</th>
                    <th style="text-align: center">Comments</th>
                    <th style="text-align: center">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${newsList}" var="news">
                    <tr>
                        <td style="text-align: center">${news.id}</td>
                        <td>${news.title}</td>
                        <td>${news.category.name}</td>
                        <td class="text-center">
                            <img
                                    src="${news.thumbnail}"
                                    alt="${news.title} Thumbnail"
                                    class="img-fluid"
                                    style="max-height: 100px"
                            />
                        </td>
                        <td>${news.shortDescription}</td>
                        <td class="text-center">${news.comments.size()} Comments</td>
                        <td class="text-center">
                            <a
                                    href="${pageContext.request.contextPath}/admin/news/details?id=${news.id}"
                                    class="btn btn-sm btn-primary"
                            >
                                <i class="fa fa-edit"></i> Detail
                            </a>
                            <a
                                    href="${pageContext.request.contextPath}/admin/news/edit?id=${news.id}"
                                    class="btn btn-sm btn-primary"
                            >
                                <i class="fa fa-edit"></i> Edit
                            </a>
                            <a
                                    href="${pageContext.request.contextPath}/admin/news/delete?id=${news.id}"
                                    class="btn btn-sm btn-danger"
                                    onclick="return confirm('Are you sure you want to delete this news item?');"
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
                                href="${pageContext.request.contextPath}/admin/news?page=${currentPage - 1}&title=${param.title}"
                                aria-label="Previous"
                        >
                            &laquo;
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a
                                class="page-link"
                                href="${pageContext.request.contextPath}/admin/news?page=${i}&title=${param.title}"
                        >${i}</a
                        >
                    </li>
                </c:forEach>

                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a
                                class="page-link"
                                href="${pageContext.request.contextPath}/admin/news?page=${currentPage + 1}&title=${param.title}"
                                aria-label="Next"
                        >
                            &raquo;
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
