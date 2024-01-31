package uz.pdp.bookstoreproject.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Optional;

@WebFilter(urlPatterns = "/*")
public class AuthFilter implements Filter {
    private final String[] CLOSED_URI = {"/download","/profile"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        Optional<String> first = Arrays.stream(CLOSED_URI).filter(u -> u.equals(uri)).findFirst();
        Cookie cookie = null;
        for (Cookie c : req.getCookies()) {
            if (c.getName().equals("user")) cookie = c;
        }
        if (cookie != null || first.isEmpty()) filterChain.doFilter(req, resp);
        else {
            resp.sendRedirect("/auth/login");
        }
    }
}
