//package org.com.controller.admin;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.com.entity.Comment;
//import org.com.entity.Product;
//import org.com.entity.User;
//import org.com.service.ICommentService;
//import org.com.service.IProductService;
//import org.com.service.impl.CommentServiceServiceImpl;
//import org.com.service.impl.ProductServiceImpl;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@WebServlet(
//        urlPatterns = {
//                "/comment",
//                "/comment/add",
//                "/comment/edit",
//                "/comment/delete",
//                "/comment/details"
//        }
//)
//public class CommentController extends HttpServlet {
//    private final ICommentService commentService = new CommentServiceServiceImpl();
//    private final IProductService productService = new ProductServiceImpl();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getServletPath();
//        switch (action) {
//            case "/admin/comment/add" -> showAddForm(req, resp);
//            case "/admin/comment/delete" -> deleteComment(req, resp);
//            case "/admin/comment/details" -> showCommentDetails(req, resp);
//            default -> listComments(req, resp);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getServletPath();
//        switch (action) {
//            case "/admin/comment/add" -> addComment(req, resp);
//            case "/admin/comment/edit" -> updateComment(req, resp);
//            default -> listComments(req, resp);
//        }
//    }
//
//    private void listComments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String productId = req.getParameter("productId");
//        List<Comment> comments;
//
//        if (productId != null && !productId.isEmpty()) {
//            comments = commentService.findAllWithProductId(Long.parseLong(productId));
//            req.setAttribute("productId", productId);
//        } else {
//            comments = commentService.findAllWithUser();
//        }
//
//        req.setAttribute("comments", comments);
//        req.getRequestDispatcher("/views/admin/comment-list.jsp").forward(req, resp);
//    }
//
//    private void showAddForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String productId = req.getParameter("productId");
//        req.setAttribute("productId", productId);
//        req.getRequestDispatcher("/views/admin/comment-add.jsp").forward(req, resp);
//    }
//
//    private void addComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String content = req.getParameter("content");
//        String productId = req.getParameter("productId");
//        int rating = Integer.parseInt(req.getParameter("rating"));
//
//        User user = (User) req.getSession().getAttribute("account");
//        Product product = productService.findById(productId);
//
//        Comment comment = Comment.builder()
//                                 .rating(rating)
//                                 .time(LocalDateTime.now())
//                                 .user(user)
//                                 .product(product)
//                                 .build();
//
//        commentService.save(comment);
//
//        resp.sendRedirect(req.getContextPath() + "/admin/comment?productId=" + productId);
//    }
//
//    private void updateComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Long id = Long.parseLong(req.getParameter("id"));
//        String content = req.getParameter("content");
//        int rating = Integer.parseInt(req.getParameter("rating"));
//
//        Comment comment = commentService.findById(id);
//        comment.setComment(content);
//        comment.setRating(rating);
//
//        commentService.update(comment);
//
//        resp.sendRedirect(req.getContextPath() + "/admin/comment/details?id=" + id);
//    }
//
//    private void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Long id = Long.parseLong(req.getParameter("id"));
//        commentService.delete(id);
//
//        resp.sendRedirect(req.getContextPath() + "/admin/comment");
//    }
//
//    private void showCommentDetails(
//            HttpServletRequest req, HttpServletResponse resp
//    ) throws ServletException, IOException {
//        Long id = Long.parseLong(req.getParameter("id"));
//        Comment comment = commentService.findById(id);
//
//        req.setAttribute("comment", comment);
//        req.getRequestDispatcher("/views/admin/comment-details.jsp").forward(req, resp);
//    }
//}