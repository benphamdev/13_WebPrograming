package org.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.com.entity.Category;
import org.com.entity.Product;
import org.com.entity.User;
import org.com.service.ICategoryService;
import org.com.service.IProductService;
import org.com.service.IUserService;
import org.com.service.impl.CategoryServiceImpl;
import org.com.service.impl.ProductServiceImpl;
import org.com.service.impl.UserServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@MultipartConfig
@WebServlet(
        urlPatterns = {
                "/admin/product",
                "/admin/product/add",
                "/admin/product/edit",
                "/admin/product/delete",
                "/admin/product/details"
        }
)
public class ProductController extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    private final IProductService productService = new ProductServiceImpl();
    private final ICategoryService categoryService = new CategoryServiceImpl();
    private final IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/product/add" -> showAddForm(req, resp);
            case "/admin/product/edit" -> showEditForm(req, resp);
            case "/admin/product/delete" -> deleteProduct(req, resp);
            case "/admin/product/details" -> showProductDetails(req, resp);
            default -> listProducts(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/product/add" -> addProduct(req, resp);
            case "/admin/product/edit" -> updateProduct(req, resp);
            default -> listProducts(req, resp);
        }
    }

//    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Product> products = productService.findAll();
//        req.setAttribute("productList", products);
//
//        req.getRequestDispatcher("/views/admin/product-list.jsp").forward(req, resp);
//    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sellerIdParam = req.getParameter("sellerId");
        Integer sellerId = sellerIdParam != null && !sellerIdParam.isEmpty() ? Integer.parseInt(sellerIdParam) : null;
        int page = Optional.ofNullable(req.getParameter("page")).map(Integer::parseInt).orElse(1);
        int pageSize = Optional.ofNullable(req.getParameter("size")).map(Integer::parseInt).orElse(3);
        List<User> users = userService.getAllUsers();

        List<Product> productList;
        int totalItems;
        if (sellerId != null) {
            productList = productService.findBySellerId(Long.valueOf(sellerId));
            totalItems = productService.countBySellerId(Long.valueOf(sellerId));
            req.setAttribute("sellerId", sellerId);

        } else {
            productList = productService.findAll(page, pageSize);
            totalItems = productService.count();
        }

        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        req.setAttribute("productList", productList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalProduct", totalItems);
        req.setAttribute("pageSize", pageSize);
        req.setAttribute("users", users);

        req.getRequestDispatcher("/views/admin/product-list.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        List<User> sellers = userService.getAllUsers();
        req.setAttribute("categories", categories);
        req.setAttribute("sellers", sellers);
        req.getRequestDispatcher("/views/admin/product-add.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        Product product = productService.findById(productId);
        List<Category> categories = categoryService.findAll();
        List<User> sellers = userService.getAllUsers();
        req.setAttribute("product", product);
        req.setAttribute("categories", categories);
        req.setAttribute("sellers", sellers);
        req.setAttribute("seller", sellers.get(0));
        req.getRequestDispatcher("/views/admin/product-edit.jsp").forward(req, resp);
    }

    private void showProductDetails(
            HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        String productId = req.getParameter("id");

        Product product = productService.findById(productId);

        if (product == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
            return;
        }

        req.setAttribute("product", product);
        req.getRequestDispatcher("/views/admin/product-details.jsp").forward(req, resp);
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Integer amount = Integer.parseInt(req.getParameter("amount"));
        Long categoryId = Long.parseLong(req.getParameter("categoryId"));
        Long sellerId = Long.parseLong(req.getParameter("sellerId"));
        Part imagePart = req.getPart("imageLink");

        String imageLink = uploadImage(req, imagePart);

        Category category = categoryService.findById(categoryId);
        User seller = userService.findById(sellerId.intValue());

        Product product = Product.builder()
                                 .productName(productName)
                                 .description(description)
                                 .price(price)
                                 .amount(amount)
                                 .imageLink(imageLink)
                                 .category(category)
                                 .seller(seller)
                                 .build();

        productService.insert(product);
        resp.sendRedirect(req.getContextPath() + "/admin/product");
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        String productName = req.getParameter("productName");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        Integer amount = Integer.parseInt(req.getParameter("amount"));
        Long categoryId = Long.parseLong(req.getParameter("categoryId"));
        Long sellerId = Long.parseLong(req.getParameter("sellerId"));
//        Part imagePart = req.getPart("imageLink");

        Product product = productService.findById(productId);
//        String imageLink = uploadImage(req, imagePart);

        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setAmount(amount);
        product.setCategory(categoryService.findById(categoryId));
        product.setSeller(userService.findById(sellerId.intValue()));
//        if (imageLink != null) {
//            product.setImageLink(imageLink);
//        }

        productService.update(product);
        resp.sendRedirect(req.getContextPath() + "/admin/product");
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        String productId = req.getParameter("id");
        try {
            productService.delete(productId);
            resp.sendRedirect(req.getContextPath() + "/admin/product");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String uploadImage(HttpServletRequest req, Part imagePart) throws IOException {
        if (imagePart != null && imagePart.getSize() > 0) {
            String uploadPath = req.getServletContext().getRealPath("/" + UPLOAD_DIR);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists() && !uploadDir.mkdir()) {
                throw new IOException("Failed to create upload directory.");
            }

            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String filePath = uploadPath + File.separator + fileName;
            imagePart.write(filePath);
            return fileName;
        }
        return null;
    }
}
