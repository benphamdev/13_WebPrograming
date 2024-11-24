<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product Detail</title>
    <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet" type="text/css"/>
    <style>
        body {
            background-color: #f5f6fa;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin-top: 40px;
            max-width: 900px;
            margin-left: auto;
            margin-right: auto;
        }

        .poster img {
            width: 100%;
            max-width: 250px;
            height: auto;
            border-radius: 10px;
            border: 1px solid #ddd;
            margin: auto;
        }

        .details {
            display: flex;
            flex-direction: column;
            justify-content: center;
            padding-left: 20px;
        }

        .details h4 {
            font-size: 22px;
            color: #2d3436;
            margin-bottom: 10px;
        }

        .details h5 {
            font-size: 16px;
            margin: 5px 0;
            color: #636e72;
        }

        .description {
            margin-top: 30px;
            padding: 20px;
            border-radius: 10px;
            background-color: #f1f2f6;
            color: #2d3436;
        }

        .description h5 {
            font-size: 18px;
            color: #0984e3;
            margin-bottom: 15px;
        }

        .description p {
            font-size: 16px;
            line-height: 1.6;
        }

        .btn-back {
            margin-top: 30px;
            display: inline-block;
            background-color: #6c5ce7;
            color: #ffffff;
            padding: 10px 20px;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
        }

        .btn-back:hover {
            background-color: #4834d4;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-md-4 text-center poster">
                <img src="${pageContext.request.contextPath}/uploads/${product.imageLink}" alt="${product.productName}">
            </div>
            <div class="col-md-8 details">
                <h4>Product Name: ${product.productName}</h4>
                <h5>Product ID: ${product.productId}</h5>
                <h5>Category: ${product.category.categoryId}</h5>
                <h5>Price: $${product.price}</h5>
                <h5>Amount in Stock: ${product.amount}</h5>
                <h5>Seller: ${seller.userId}</h5>
            </div>
        </div>
        <div class="description">
            <h5>Description</h5>
            <p>${product.description}</p>
        </div>
        <a href="${pageContext.request.contextPath}/admin/products" class="btn-back">Back to Product List</a>
    </div>

    <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
