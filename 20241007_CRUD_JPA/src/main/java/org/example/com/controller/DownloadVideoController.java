package org.example.com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.example.com.constant.Constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(urlPatterns = {"/video"})
public class DownloadVideoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("filename");
        File file = new File(req.getServletContext().getRealPath(Constant.UPLOAD_DIR) + "/" + fileName);
        resp.setContentType("video/mp4");
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                IOUtils.copy(fileInputStream, resp.getOutputStream());
                resp.getOutputStream().flush();  // Đảm bảo dữ liệu được gửi đi
            }
        } else
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
    }
}