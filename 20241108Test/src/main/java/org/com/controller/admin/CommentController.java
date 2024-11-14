package org.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.com.entity.Comment;
import org.com.entity.News;
import org.com.entity.User;
import org.com.service.ICommentService;
import org.com.service.INewsService;
import org.com.service.impl.CommentServiceImpl;
import org.com.service.impl.NewsServiceImpl;

import java.io.IOException;

@WebServlet(
        urlPatterns = {
                "/comment",
                "/comment/add",
                "/comment/edit",
                "/comment/insert",
                "/comment/delete"
        }
)
public class CommentController extends HttpServlet {
    private final ICommentService commentService = new CommentServiceImpl();
    private final INewsService newsService = new NewsServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getParameter("content");
        String newsId = req.getParameter("newsId");
        User user = (User) req.getSession().getAttribute("account");

        News news = newsService.findById(newsId);

        Comment comment = Comment.builder()
                                 .content(content)
                                 .status(1) // Assuming 1 means active
                                 .user(user)
                                 .news(news)
                                 .build();

        commentService.save(comment);

        resp.sendRedirect(req.getContextPath() + "/admin/news/details?id=" + newsId);
    }
}