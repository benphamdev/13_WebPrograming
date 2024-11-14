package org.com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.com.entity.User;

import java.io.IOException;

@WebServlet("/waiting")
public class WaitingController extends HttpServlet {
    private static final long ADMIN_ROLE_ID = 1;
    private static final long USER_ROLE_ID = 2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            req.setAttribute("username", user.getUsername());
            redirectToHomePage(user, req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/auth/login");
        }
    }

    private void redirectToHomePage(User user, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long roleId = user.getRole().getId();
        String contextPath = req.getContextPath();

        switch ((int) roleId) {
            case (int) ADMIN_ROLE_ID -> resp.sendRedirect(contextPath + "/admin/home");
            case (int) USER_ROLE_ID -> resp.sendRedirect(contextPath + "/user/home");
            default -> resp.sendRedirect(contextPath + "/home");
        }
    }
}