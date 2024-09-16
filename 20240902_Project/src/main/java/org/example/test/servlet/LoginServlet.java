package org.example.test.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Login", value = "/auth/login")
public class LoginServlet extends HttpServlet {
    private String message;

    @SuppressWarnings("serial")
    public void init() throws ServletException {
        // Do required initialization
        message = "Hello World";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Get the first name and last name from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // Set the attribute and forward the request to login.html
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);

        // Forward the request to login.html
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    public void destroy() {
        // do nothing.
    }
}
