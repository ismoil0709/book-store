package uz.pdp.bookstoreproject.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.bookstoreproject.repo.BookRepo;
import uz.pdp.bookstoreproject.repo.impl.BookRepoImpl;
import uz.pdp.bookstoreproject.service.BookService;
import uz.pdp.bookstoreproject.service.impl.BookServiceImpl;

import java.io.IOException;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    private final BookService bookService = BookServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String searchBy = req.getParameter("search_by");
            switch (searchBy) {
                case "title" ->
                        req.getSession().setAttribute("books", bookService.getByTitle(req.getParameter("search")));
                case "price" ->
                        req.getSession().setAttribute("books", bookService.getByPrice(Double.parseDouble(req.getParameter("search"))));
                case "author" ->
                        req.getSession().setAttribute("books", bookService.getByAuthor(req.getParameter("search")));
                case "rating" ->
                        req.getSession().setAttribute("books", bookService.getByRating(Float.parseFloat(req.getParameter("search"))));
            }
        } catch (Exception e) {
            req.getSession().setAttribute("message", e.getLocalizedMessage());
        }
        resp.sendRedirect("/");
    }
}
