package org.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.com.entity.Category_22162006;
import org.com.entity.Share_22162006;
import org.com.entity.Video_22162006;
import org.com.service.ICategoryService_22162006;
import org.com.service.IShareService_22162006;
import org.com.service.IVideoService_22162006;
import org.com.service.impl.CategoryService_22162006Impl;
import org.com.service.impl.ShareService_22162006Impl;
import org.com.service.impl.VideoService_22162006Impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@MultipartConfig
@WebServlet(
        urlPatterns = {
                "/admin/video",
                "/admin/video/add",
                "/admin/video/edit",
                "/admin/video/delete",
                "/admin/video/details"
        }
)
public class VideoController_22162006 extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";
    private final IVideoService_22162006 videoService = new VideoService_22162006Impl();
    private final ICategoryService_22162006 categoryService = new CategoryService_22162006Impl();
    private final IShareService_22162006 shareService = new ShareService_22162006Impl();
    System.Logger logger = System.getLogger(VideoController_22162006.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/video/add" -> showAddForm(req, resp);
            case "/admin/video/edit" -> showEditForm(req, resp);
            case "/admin/video/delete" -> deleteVideo(req, resp);
            case "/admin/video/details" -> showVideoDetails(req, resp);
            default -> listVideos(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/admin/video/add" -> addVideo(req, resp);
            case "/admin/video/edit" -> updateVideo(req, resp);
            default -> listVideos(req, resp);
        }
    }

    private void listVideos(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.log(System.Logger.Level.INFO, "List videos");
        String title = req.getParameter("title");
        String categoryIdParam = req.getParameter("categoryId");
        Integer categoryId =
                categoryIdParam != null && !categoryIdParam.isEmpty() ? Integer.parseInt(categoryIdParam) : null;
        int page = Optional.ofNullable(req.getParameter("page")).map(Integer::parseInt).orElse(1);
        int pageSize = Optional.ofNullable(req.getParameter("size")).map(Integer::parseInt).orElse(6);
        List<Category_22162006> categories = categoryService.findAll();
        req.setAttribute("categories", categories);

        List<Video_22162006> video22162006List;
        int totalItems;
        if (title != null && !title.isEmpty()) {
            video22162006List = videoService.searchPaginated(title, page, pageSize);
            totalItems = videoService.countByTitle(title);
            req.setAttribute("title", title);
        } else if (categoryId != null) {
            video22162006List = videoService.findByCategoryId(categoryId, page, pageSize);
            totalItems = videoService.countByCategoryId(categoryId);
            req.setAttribute("categoryId", categoryId);
        } else {
            video22162006List = videoService.findAll(page, pageSize);
            totalItems = videoService.count();
        }

        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        req.setAttribute("videoList", video22162006List);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("totalVideo", totalItems);
        req.setAttribute("pageSize", pageSize);

        req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
    }

    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category_22162006> categories = categoryService.findAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Video_22162006 existingVideo22162006 = videoService.findById(id);
        List<Category_22162006> categories = categoryService.findAll();
        List<Category_22162006> videoCategories =
                categoryService.findCategoriesByVideoId(existingVideo22162006.getId());

        req.setAttribute("video", existingVideo22162006);
        req.setAttribute("categories", categories);
        req.setAttribute("videoCategories", videoCategories);

        req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
    }

    private void showVideoDetails(
            HttpServletRequest req, HttpServletResponse resp
    ) throws ServletException, IOException {
        String id = req.getParameter("id");
        Video_22162006 video22162006 = videoService.findById(id);

        if (video22162006 == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
            return;
        }
        List<Category_22162006> videoCategories = categoryService.findCategoriesByVideoId(video22162006.getId());

        req.setAttribute("categories", videoCategories.get(0));
        List<Share_22162006> share22162006s = shareService.findAllWithVideoId(video22162006.getId());
        video22162006.setShare22162006s(share22162006s);
        req.setAttribute("sizeShare", share22162006s.size());
        req.setAttribute("video", video22162006);
        req.getRequestDispatcher("/views/admin/video-details.jsp").forward(req, resp);
    }

    private void addVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int views = Integer.parseInt(req.getParameter("views"));
        boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
        Part posterPart = req.getPart("poster");
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

//        String[] categoryIds = req.getParameterValues("categoryIds[]");
//        List<Category> categories = new ArrayList<>();
//        if (categoryIds != null) {
//            for (String categoryIdStr : categoryIds) {
//                Long categoryId = Long.parseLong(categoryIdStr);
//                Category category = categoryService.findById(categoryId);
//                if (category != null) {
//                    categories.add(category);
//                }
//            }
//        }

        String poster = uploadPoster(req, posterPart);

        Video_22162006 video22162006 = Video_22162006.builder()
                                                     .title(title)
                                                     .description(description)
                                                     .views(views)
                                                     .isActive(isActive)
                                                     .poster(poster)
                                                     .categoryId(categoryId)  // Assuming one category per video
                                                     .favorite22162006s(new ArrayList<>())
                                                     .share22162006s(new ArrayList<>())
                                                     .build();

        videoService.insert(video22162006);

        resp.sendRedirect(req.getContextPath() + "/admin/video");
    }

    private void updateVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int views = Integer.parseInt(req.getParameter("views"));
        boolean isActive = Boolean.parseBoolean(req.getParameter("isActive"));
        Part posterPart = req.getPart("poster");

        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

//        String[] categoryIds = req.getParameterValues("categoryIds");
//        List<Category> selectedCategories = new ArrayList<>();
//        if (categoryIds != null) {
//            for (String categoryIdStr : categoryIds) {
//                Long categoryId = Long.parseLong(categoryIdStr);
//                Category category = categoryService.findById(categoryId);
//                if (category != null) {
//                    selectedCategories.add(category);
//                }
//            }
//        }

        String poster = uploadPoster(req, posterPart);

        Video_22162006 video22162006 = videoService.findById(id);
        video22162006.setTitle(title);
        video22162006.setDescription(description);
        video22162006.setViews(views);
        video22162006.setActive(isActive);
        if (poster != null) {
            video22162006.setPoster(poster);
        }
        video22162006.setCategoryId(categoryId);  // Assuming one category per video

        videoService.update(video22162006);

        resp.sendRedirect(req.getContextPath() + "/admin/video");
    }

    private void deleteVideo(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        try {
            videoService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/video");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String uploadPoster(HttpServletRequest req, Part posterPart) throws IOException {
        String uploadPath = req.getServletContext().getRealPath("/" + UPLOAD_DIR);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists() && !uploadDir.mkdir()) {
            throw new IOException("Failed to create upload directory.");
        }

        if (posterPart != null && posterPart.getSize() > 0) {
            String fileName = Paths.get(posterPart.getSubmittedFileName()).getFileName().toString();
            String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
            String posterName = System.currentTimeMillis() + "." + ext;
            File fileToSave = new File(uploadPath, posterName);
            posterPart.write(fileToSave.getAbsolutePath());
            return posterName;
        }
        return null;
    }
}
