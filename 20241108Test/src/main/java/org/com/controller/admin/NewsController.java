package org.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.com.entity.Category;
import org.com.entity.News;
import org.com.service.ICategoryService;
import org.com.service.INewsService;
import org.com.service.impl.CategoryServiceImpl;
import org.com.service.impl.NewsServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@MultipartConfig
@WebServlet(
        urlPatterns = {
                "/admin/news",
                "/admin/news/add",
                "/admin/news/edit",
                "/admin/news/delete",
                "/admin/news/details"
        }
)
public class NewsController extends HttpServlet {
    private final INewsService newsService = new NewsServiceImpl();
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/news/add" -> showAddForm(req, resp);
            case "/admin/news/edit" -> showEditForm(req, resp);
            case "/admin/news/delete" -> deleteNews(req, resp);
            case "/admin/news/details" -> showNewsDetails(req, resp);
            default -> listNews(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/news/add" -> addNews(req, resp);
            case "/admin/news/edit" -> updateNews(req, resp);
            default -> listNews(req, resp);
        }
    }

    private void listNews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        int page = Optional.ofNullable(req.getParameter("page")).map(Integer::parseInt).orElse(1);
        int pageSize = Optional.ofNullable(req.getParameter("size")).map(Integer::parseInt).orElse(5);

        List<News> newsList;
        int totalItems;
        if (title != null && !title.isEmpty()) {
            newsList = newsService.searchPaginated(title, page, pageSize);
            totalItems = newsService.countByTitle(title);
            req.setAttribute("title", title);
        } else {
            newsList = newsService.findAll(page, pageSize);
            totalItems = newsService.count();
        }

        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        req.setAttribute("newsList", newsList);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/views/admin/news-list.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/admin/news-add.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        News existingNews = newsService.findById(id);
        List<Category> categories = categoryService.findAll();
        req.setAttribute("news", existingNews);
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/admin/news-edit.jsp").forward(req, resp);
    }

    private void showNewsDetails(
            HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        String id = req.getParameter("id");
        News news = newsService.findById(id);
        req.setAttribute("news", news);
        req.getRequestDispatcher("/views/admin/news-details.jsp").forward(req, resp);
    }

    private void addNews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String shortDescription = req.getParameter("shortDescription");
        String content = req.getParameter("content");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Part thumbnailPart = req.getPart("thumbnail");
        String thumbnail = Paths.get(thumbnailPart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        thumbnailPart.write(uploadPath + File.separator + thumbnail);

        Category category = categoryService.findById(categoryId);
        News news = News.builder()
                        .title(title)
                        .shortDescription(shortDescription)
                        .content(content)
                        .thumbnail(thumbnail)
                        .category(category)
                        .build();
        newsService.insert(news);
        resp.sendRedirect(req.getContextPath() + "/admin/news");
    }

    private void updateNews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String shortDescription = req.getParameter("shortDescription");
        String content = req.getParameter("content");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        Part thumbnailPart = req.getPart("thumbnail");
        String thumbnail = Paths.get(thumbnailPart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        thumbnailPart.write(uploadPath + File.separator + thumbnail);

        Category category = categoryService.findById(categoryId);
        News news = newsService.findById(id);
        news.setTitle(title);
        news.setShortDescription(shortDescription);
        news.setContent(content);
        news.setThumbnail(thumbnail);
        news.setCategory(category);
        newsService.update(news);
        resp.sendRedirect(req.getContextPath() + "/admin/news");
    }

    private void deleteNews(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        try {
            newsService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/news");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}