package org.com.controller.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.com.entity.User_22162006;
import org.com.service.IUserService_22162006;
import org.com.service.impl.UserService_22162006Impl;
import org.com.util.Constant_22162006;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/auth/login")
public class LoginController_22162006 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }
        // Check cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    session = req.getSession(true);
                    session.setAttribute("username", cookie.getValue());
                    resp.sendRedirect(req.getContextPath() + "/waiting");
                    return;
                }
            }
        }
        req.getRequestDispatcher(Constant_22162006.Path.LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isRememberMe = false;
        String remember = req.getParameter("remember");

        if ("on".equals(remember))
            isRememberMe = true;
        String alertMsg = "";
        if (username.isEmpty() || password.isEmpty()) {
            alertMsg = "Username or password should not be null";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant_22162006.Path.LOGIN).forward(req, resp);
            return;
        }

        IUserService_22162006 service = new UserService_22162006Impl();

        User_22162006 user22162006 = service.login(username, password);
        if (user22162006 != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user22162006);
            if (isRememberMe) {
                saveRememberMe(resp, username);
            }

            resp.sendRedirect(req.getContextPath() + "/waiting");
        } else {
            alertMsg = "Username or password is incorrect!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant_22162006.Path.LOGIN).forward(req, resp);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(
                Constant_22162006.COOKIE_REMEMBER,
                username
        );
        cookie.setMaxAge(30 * 60);
        cookie.setPath("/");    // Root for entire web
        response.addCookie(cookie);
    }
}
