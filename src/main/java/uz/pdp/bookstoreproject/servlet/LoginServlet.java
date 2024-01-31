package uz.pdp.bookstoreproject.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.bookstoreproject.entity.User;
import uz.pdp.bookstoreproject.service.UserService;
import uz.pdp.bookstoreproject.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/auth/login")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User login = userService.login(
                    req.getParameter("username"),
                    req.getParameter("password")
            );
            Cookie cookie = new Cookie("user",login.getId().toString());
            cookie.setMaxAge(1000);
            cookie.setPath("/");
            resp.addCookie(cookie);
            resp.sendRedirect("/");
        } catch (IllegalArgumentException e) {
            req.setAttribute("message", e.getMessage());
            getServletContext().getRequestDispatcher("/auth/login.jsp").forward(req, resp);
        }
    }

}
