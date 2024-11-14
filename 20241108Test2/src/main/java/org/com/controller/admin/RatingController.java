package org.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.com.entity.Book;
import org.com.entity.Rating;
import org.com.entity.User;
import org.com.service.IBookService;
import org.com.service.IRatingService;
import org.com.service.impl.BookServiceImpl;
import org.com.service.impl.RatingServiceImpl;

import java.io.IOException;

@WebServlet(
        urlPatterns = {
                "/rating",
                "/rating/add",
                "/rating/edit",
                "/rating/insert",
                "/rating/delete"
        }
)
public class RatingController extends HttpServlet {
    private final IRatingService commentService = new RatingServiceImpl();
    private final IBookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reviewText = req.getParameter("reviewText");
        String rating1 = req.getParameter("rating");
        String bookId = req.getParameter("bookId");
        User user = (User) req.getSession().getAttribute("account");

        Book book = bookService.findById(bookId);

        Rating rating = Rating.builder()
                              .reviewText(reviewText)
                              .rating(rating1)
                              .book(book)
                              .user(user)
                              .build();

        commentService.save(rating);

        resp.sendRedirect(req.getContextPath() + "/admin/book/details?id=" + bookId);
    }
}