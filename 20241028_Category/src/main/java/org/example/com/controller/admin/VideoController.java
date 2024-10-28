package org.example.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.example.com.entity.Category;
import org.example.com.entity.Video;
import org.example.com.service.ICategoryService;
import org.example.com.service.IVideoService;
import org.example.com.service.impl.CategoryServiceImpl;
import org.example.com.service.impl.VideoServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig()
@WebServlet(
        urlPatterns = {
                "/admin/videos",
                "/admin/video/add",
                "/admin/video/edit",
                "/admin/video/insert",
                "/admin/video/delete"
        }
)
public class VideoController extends HttpServlet {
    private final IVideoService videoService = new VideoServiceImpl();
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        if (url.contains("/admin/videos")) {
            List<Video> videos = videoService.findAll();
            req.setAttribute("videoList", videos);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/add")) {
            if (req.getParameter("categoryId") != null) {
                int id = Integer.parseInt(req.getParameter("categoryId"));
                req.setAttribute("categoryId", id);
            }
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/edit")) {
            String videoId = req.getParameter("id");
            Video video = videoService.findById(videoId);
            if (video == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
                return;
            }
            req.setAttribute("video", video);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/delete")) {
            String videoId = req.getParameter("id");
            try {
                videoService.delete(videoId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        if (url.contains("/admin/video/insert")) {
            // Get data from view
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            Category category = categoryService.findById(categoryId);
            String description = req.getParameter("description");
            int active = Integer.parseInt(req.getParameter("active"));
            String imageLink = req.getParameter("imageLink");
            Video video = Video.builder()
                               .category(category)
                               .description(description)
                               .active(active)
                               .build();
            // Handle upload file
            String fname = "";
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();
            try {
                Part part = req.getPart("image");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    // upload
                    part.write(uploadPath + "/" + fname);
                    video.setPoster(fname);
                } else if (!imageLink.isBlank()) {
                    video.setPoster(imageLink);
                } else {
                    video.setPoster("avatar.png");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Service
            videoService.insert(video);
            //
            resp.sendRedirect(req.getContextPath() + "/admin/category?id=" + categoryId);
        } else if (url.contains("/admin/video/edit")) {
            // Get data from view
            String videoId = req.getParameter("videoId");
            Video oldVideo = videoService.findById(videoId);
            if (oldVideo == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Video not found");
                return;
            }
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            Category category = categoryService.findById(categoryId);
            String description = req.getParameter("description");
            int active = Integer.parseInt(req.getParameter("active"));
            String imageLink = req.getParameter("imageLink");
            Video video = Video.builder()
                               .videoId(videoId)
                               .category(category)
                               .description(description)
                               .active(active)
                               .build();
            // Handle upload file
            String fname = "";
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists())
                uploadDir.mkdir();
            try {
                Part part = req.getPart("image");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    // upload
                    part.write(uploadPath + "/" + fname);
                    video.setPoster(fname);
                } else if (!imageLink.isBlank()) {
                    video.setPoster(imageLink);
                } else {
                    // Keep the same poster
                    video.setPoster(oldVideo.getPoster());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            // Service
            videoService.update(video);
            //
            resp.sendRedirect(req.getContextPath() + "/admin/category?id=" + categoryId);
        }
    }
}