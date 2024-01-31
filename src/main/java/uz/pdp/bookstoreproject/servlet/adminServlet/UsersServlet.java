package uz.pdp.bookstoreproject.servlet.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.bookstoreproject.service.UserService;
import uz.pdp.bookstoreproject.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "UsersServlet",value = "/admin/users")
public class UsersServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin/users.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isAdmin = req.getParameter("isAdmin");
        if (isAdmin == null) userService.deleteAdmin(Long.parseLong(req.getParameter("id")));
        else if (isAdmin.equals("on"))userService.makeAdmin(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect("/admin/users");
    }
}
