package uz.pdp.bookstoreproject.servlet.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.bookstoreproject.service.BookService;
import uz.pdp.bookstoreproject.service.impl.BookServiceImpl;

import java.io.IOException;

@WebServlet(name = "DeleteBookServlet", value = "/admin/delete")
public class DeleteBookServlet extends HttpServlet {
    private final BookService bookService = BookServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        long id = Long.parseLong(req.getParameter("id"));
        bookService.delete(id);
        resp.sendRedirect("/admin/books");
    }
        catch (Exception e){
            req.getSession().setAttribute("message",e.getMessage());
            resp.sendRedirect("/admin/books");
        }
}}
