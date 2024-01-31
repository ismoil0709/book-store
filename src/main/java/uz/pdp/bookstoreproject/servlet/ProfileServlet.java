package uz.pdp.bookstoreproject.servlet;

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
import java.util.Arrays;
import java.util.Optional;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/admin/profile", "/profile"})
public class ProfileServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Cookie> optionalCookie = Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("user")).findFirst();
        if (optionalCookie.isEmpty()) resp.sendRedirect("/auth/login");
        else {
            long id = Long.parseLong(req.getParameter("id"));
            User byId = userService.getById(Long.parseLong(optionalCookie.get().getValue()));
            if (!byId.isAdmin() && byId.getId() != id) resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            else {
                User user = userService.getById(id);
                req.setAttribute("user", user);
                getServletContext().getRequestDispatcher("/profile.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            User user = User.builder()
                    .name(req.getParameter("new_name"))
                    .surname(req.getParameter("new_surname"))
                    .username(req.getParameter("new_username"))
                    .email(req.getParameter("new_email"))
                    .build();
            String password = req.getParameter("new_password");
            String confirmPassword = req.getParameter("confirm_password");
            if (password!=null && confirmPassword!=null && !password.equals(confirmPassword)) req.getSession().setAttribute("message", "Incorrect password");
            else {user.setPassword(password);
            userService.editProfile(id, user);}
        } catch (Exception e) {
            req.getSession().setAttribute("message", e.getMessage());
        } finally {
            resp.sendRedirect("/profile?id=" + req.getParameter("id"));
        }
    }
}
