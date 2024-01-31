package uz.pdp.bookstoreproject.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.bookstoreproject.entity.Book;
import uz.pdp.bookstoreproject.service.BookService;
import uz.pdp.bookstoreproject.service.impl.BookServiceImpl;

import java.io.IOException;

@WebServlet(name = "AboutBookServlet",value = "/books")
public class AboutBookServlet extends HttpServlet {
    private final BookService bookService = BookServiceImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Book byId = bookService.getById(id);
        req.setAttribute("book",byId);
        getServletContext().getRequestDispatcher("/about_book.jsp").forward(req,resp);
    }
}
