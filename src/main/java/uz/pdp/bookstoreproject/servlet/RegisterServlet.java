package uz.pdp.bookstoreproject.servlet;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.bookstoreproject.entity.User;
import uz.pdp.bookstoreproject.service.UserService;
import uz.pdp.bookstoreproject.service.impl.UserServiceImpl;
import uz.pdp.bookstoreproject.util.ConfigUtils;
import uz.pdp.bookstoreproject.util.MailService;
import uz.pdp.bookstoreproject.util.TokenManager;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@WebServlet(name = "RegisterServlet", value = "/auth/register")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();
    private User user = new User();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("token") != null){
            final String token = req.getParameter("token");
            System.out.println(token);

            String decrypt = TokenManager.decrypt(token);
            String[] params = decrypt.split(";");

            String date = params[1];
            LocalDateTime tokenDate = LocalDateTime.from(
                    ConfigUtils.dateTimeFormatter().parse(date)
            );
            System.out.println(tokenDate);
            long diff = Duration.between(tokenDate, LocalDateTime.now()).get(ChronoUnit.SECONDS);
            if (0 <= diff && diff < 3600) {
                try {
                    userService.register(
                            user.getName(),
                            user.getSurname(),
                            user.getEmail(),
                            user.getUsername(),
                            user.getPassword()
                    );
                    resp.sendRedirect("/auth/login");
                } catch (IllegalArgumentException e) {
                    req.setAttribute("message", e.getMessage());
                    getServletContext().getRequestDispatcher("/auth/register.jsp").forward(req, resp);
                }
            } else {
                resp.getWriter().write("Token has expired");
            }
        }else getServletContext().getRequestDispatcher("/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            user = User.builder().name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .email(req.getParameter("email"))
                    .username(req.getParameter("username"))
                    .password(req.getParameter("password")).build();
            MailService.send(req.getParameter("email"));
            resp.getWriter().println("<h3>Email verification has been sent.</h3>");
        }catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
