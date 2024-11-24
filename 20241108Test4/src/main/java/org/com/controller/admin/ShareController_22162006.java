package org.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.com.entity.Share_22162006;
import org.com.entity.User_22162006;
import org.com.entity.Video_22162006;
import org.com.service.IShareService_22162006;
import org.com.service.IVideoService_22162006;
import org.com.service.impl.ShareService_22162006Impl;
import org.com.service.impl.VideoService_22162006Impl;

import java.io.IOException;
import java.sql.Date;

@WebServlet(
        urlPatterns = {
                "/share",
                "/share/add",
                "/share/edit",
                "/share/insert",
                "/share/delete"
        }
)
public class ShareController_22162006 extends HttpServlet {
    private final IShareService_22162006 commentService = new ShareService_22162006Impl();
    private final IVideoService_22162006 bookService = new VideoService_22162006Impl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emails = req.getParameter("emails");
        String videoId = req.getParameter("videoId");
        User_22162006 user22162006 = (User_22162006) req.getSession().getAttribute("account");

        Video_22162006 video22162006 = bookService.findById(videoId);

        Share_22162006 share22162006 = Share_22162006.builder()
                                                     .emails(emails)
                                                     .videoId(Long.parseLong(videoId))
                                                     .username(user22162006.getUsername())
                                                     .build();

        commentService.save(share22162006);

        resp.sendRedirect(req.getContextPath() + "/admin/video/details?id=" + videoId);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String videoId = req.getParameter("id");
        User_22162006 user22162006 = (User_22162006) req.getSession().getAttribute("account");

        Video_22162006 video22162006 = bookService.findById(videoId);

        Share_22162006 share22162006 = Share_22162006.builder()
                                                     .emails(user22162006.getEmail())
                                                     .videoId(Long.parseLong(videoId))
                                                     .username(user22162006.getUsername())
                                                     .sharedDate(new Date(System.currentTimeMillis()))
                                                     .build();

        commentService.save(share22162006);

        resp.sendRedirect(req.getContextPath() + "/admin/video/details?id=" + videoId);
    }

}