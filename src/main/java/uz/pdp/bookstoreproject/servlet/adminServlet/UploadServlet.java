package uz.pdp.bookstoreproject.servlet.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.bookstoreproject.service.BookService;
import uz.pdp.bookstoreproject.service.impl.BookServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@WebServlet(name = "UploadServlet", value = "/admin/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private final String path = "/home/ismoil_0709/Pictures/";
    private final BookService bookService = BookServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin/upload.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part cover = req.getPart("cover");
        Part file = req.getPart("file");
        if (!file.getContentType().contains("pdf")) {
            req.getSession().setAttribute("message", "please input only pdf file");
            resp.sendRedirect("/admin/upload");
            return;
        }
        Files.copy(cover.getInputStream(), Paths.get(path + cover.getSubmittedFileName()), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(file.getInputStream(), Paths.get(path + file.getSubmittedFileName()), StandardCopyOption.REPLACE_EXISTING);
        String author = req.getParameter("author");
        String[] authors = author.split("-");

        try {
            Long id = bookService.save(
                    req.getParameter("title"),
                    req.getParameter("description"),
                    path + cover.getSubmittedFileName(),
                    path + file.getSubmittedFileName(),
                    Double.parseDouble(req.getParameter("price")),
                    authors
            );
            resp.sendRedirect("/books?id=" + id);
        } catch (Exception e) {
            req.getSession().setAttribute("message", e.getMessage());
            resp.sendRedirect("/admin/upload");
        }
    }
}