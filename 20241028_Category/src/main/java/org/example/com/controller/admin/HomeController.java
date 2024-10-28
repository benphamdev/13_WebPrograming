package org.example.com.controller.admin;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/admin/home")
public class HomeController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
//    {
//        Cookie[] cookies = req.getCookies();
//        if (cookies != null)
//        {
//            for (Cookie cookie : cookies)
//            {
//                if (cookie.getName().equals("username"))
//                {
//                    IUserService service = UserServiceImpl.getInstance();
//                    UserModel user = service.get(cookie.getValue());
//                    // If found user, create a session and save information to the session
//                    if (user != null)
//                    {
//                        System.out.println("add a session");
//                        HttpSession session = req.getSession(true);
//                        session.setAttribute("account", user);
//                    }
//                }
//            }
//        }
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher(Constant.Path.ADMIN_HOME);
//        requestDispatcher.forward(req, resp);
//    }
}
