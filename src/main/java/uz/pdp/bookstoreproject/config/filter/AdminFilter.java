package uz.pdp.bookstoreproject.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.bookstoreproject.service.UserService;
import uz.pdp.bookstoreproject.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Optional<Cookie> user = Arrays.stream(req.getCookies()).filter(c -> c.getName().equals("user")).findFirst();
        if (user.isEmpty()) resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        else {
            String username = user.get().getValue();
            if (!userService.getByUserName(username).isAdmin()) resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            else filterChain.doFilter(req,resp);
        }
    }
}
