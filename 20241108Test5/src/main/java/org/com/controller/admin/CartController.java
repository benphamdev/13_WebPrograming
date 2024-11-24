package org.com.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.com.entity.Cart;
import org.com.entity.User;
import org.com.service.ICartService;
import org.com.service.IProductService;
import org.com.service.impl.CartServiceImpl;
import org.com.service.impl.ProductServiceImpl;

import java.io.IOException;

@WebServlet(
        urlPatterns = {
                "/cart",
                "/cart/add",
                "/cart/edit",
                "/cart/insert",
                "/cart/delete"
        }
)
public class CartController extends HttpServlet {
    private final ICartService commentService = new CartServiceImpl();
    private final IProductService bookService = new ProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cartId = req.getParameter("emails");
        String cart1 = req.getParameter("cart");
        String productId = req.getParameter("productId");
        User user = (User) req.getSession().getAttribute("account");

//        Cart video = commentService.findById(cartId);

        Cart cart = Cart.builder()
                        .product(bookService.findById(productId))
                        .user(user)
                        .build();

        commentService.save(cart);

        resp.sendRedirect(req.getContextPath() + "/admin/product/details?id=" + cartId);
    }
}