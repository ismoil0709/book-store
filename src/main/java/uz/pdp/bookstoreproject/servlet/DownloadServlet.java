package uz.pdp.bookstoreproject.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.bookstoreproject.entity.Book;
import uz.pdp.bookstoreproject.repo.BookRepo;
import uz.pdp.bookstoreproject.repo.impl.BookRepoImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "DownloadServlet",value = "/download")
public class DownloadServlet extends HttpServlet {
    private final BookRepo bookRepo = BookRepoImpl.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Book book = bookRepo.getById(id);
        byte[] bytes = Files.readAllBytes(Paths.get(book.getPath()));
        resp.addHeader("Content-Type","application/pdf");
        resp.addHeader("Content-Disposition","attachment;filename=" + book.getTitle() + ".pdf");
        resp.getOutputStream().write(bytes);
    }
}
