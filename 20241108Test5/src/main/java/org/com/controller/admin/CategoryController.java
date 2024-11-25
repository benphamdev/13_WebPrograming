package org.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.com.entity.Category;
import org.com.service.ICategoryService;
import org.com.service.impl.CategoryServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@MultipartConfig
@WebServlet(
        urlPatterns = {
                "/admin/categories",
                "/admin/category/add",
                "/admin/category/edit",
                "/admin/category/insert",
                "/admin/category/delete"
        }
)
public class CategoryController extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads/categories";

    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/category/add" -> showAddForm(req, resp);
            case "/admin/category/edit" -> showEditForm(req, resp);
            case "/admin/category/delete" -> deleteCategory(req, resp);
            default -> listCategories(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/category/insert" -> addCategory(req, resp);
            case "/admin/category/edit" -> updateCategory(req, resp);
            default -> listCategories(req, resp);
        }
    }

    private void listCategories(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = Optional.ofNullable(req.getParameter("page")).map(Integer::parseInt).orElse(0);
        int pageSize = Optional.ofNullable(req.getParameter("size")).map(Integer::parseInt).orElse(3);

        List<Category> categoryList = categoryService.findAll(page, pageSize);
        int totalItems = categoryService.count();
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        req.setAttribute("categoryList", categoryList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalItems", totalItems);
        req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Category category = categoryService.findById(id);

        if (category == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Category not found");
            return;
        }

        req.setAttribute("category", category);
        req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
    }

    private void addCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("categoryName");
        Part imagePart = req.getPart("image");

        String imageLink = uploadImage(req, imagePart);

        Category category = Category.builder()
                                    .categoryName(categoryName)
                                    .icon(imageLink)
                                    .isActive(true) // Default to active
                                    .build();

        categoryService.insert(category);
        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }

    private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("categoryId"));
        String categoryName = req.getParameter("categoryName");
        Part imagePart = req.getPart("image");

        Category category = categoryService.findById(id);

        if (category == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Category not found");
            return;
        }

        String imageLink = (imagePart != null && imagePart.getSize() > 0) ? uploadImage(
                req,
                imagePart
        ) : category.getIcon();
        category.setCategoryName(categoryName);
        category.setIcon(imageLink);

        categoryService.update(category);
        resp.sendRedirect(req.getContextPath() + "/admin/categories");
    }

    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            categoryService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete category", e);
        }
    }

    private String uploadImage(HttpServletRequest req, Part imagePart) throws IOException {
        if (imagePart != null && imagePart.getSize() > 0) {
            String uploadPath = req.getServletContext().getRealPath("/" + UPLOAD_DIR);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                throw new IOException("Failed to create upload directory.");
            }

            String fileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String filePath = uploadPath + File.separator + fileName;
            imagePart.write(filePath);
            return UPLOAD_DIR + "/" + fileName;
        }
        return null;
    }
}
