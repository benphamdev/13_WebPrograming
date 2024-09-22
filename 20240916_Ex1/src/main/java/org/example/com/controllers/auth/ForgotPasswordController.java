package org.example.com.controllers.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.com.services.IUserService;
import org.example.com.services.impl.UserServiceImpl;
import org.example.com.utils.Constant;

import java.io.IOException;

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Constant.Path.FORGOT_PASSWORD).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        IUserService service = new UserServiceImpl();
        String email = req.getParameter("email");
        String newPassword = req.getParameter("password");
        String reNewPassword = req.getParameter("re-password");
        String alertMsg = "";
        if (!service.checkExistEmail(email)) {
            alertMsg = "Your email does not exists";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.FORGOT_PASSWORD).forward(req, resp);
        }
        if (!newPassword.equals(reNewPassword)) {
            alertMsg = "Password and retype password do not matches!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.FORGOT_PASSWORD).forward(req, resp);
            return;
        }
        boolean success = service.changePassword(email, newPassword);
        if (success) {
            alertMsg = "You have reset password successfully, please login!";
            // Using session attribute to store attribute when redirect
            req.getSession().setAttribute("success", alertMsg);
            resp.sendRedirect(req.getContextPath() + "/auth/login");
        } else {
            alertMsg = "Unable to reset your password!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher(Constant.Path.FORGOT_PASSWORD).forward(req, resp);
        }
    }
}