package org.example.com.controllers;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "forgotPasswordServlet", value = "/auth/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String newPassword = request.getParameter("newPassword");

        // Assume updating new password in database
        // ...

        response.sendRedirect("login.jsp");
    }
}