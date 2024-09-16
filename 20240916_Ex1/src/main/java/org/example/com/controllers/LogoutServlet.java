package org.example.com.controllers;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "logoutServlet", value = "/auth/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie userCookie = new Cookie("username", "");
        userCookie.setMaxAge(0);
        response.addCookie(userCookie);

        response.sendRedirect("login.jsp");
    }
}