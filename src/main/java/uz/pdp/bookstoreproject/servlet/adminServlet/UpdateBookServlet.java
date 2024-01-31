package uz.pdp.bookstoreproject.servlet.adminServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import uz.pdp.bookstoreproject.entity.Author;
import uz.pdp.bookstoreproject.entity.Book;
import uz.pdp.bookstoreproject.repo.AuthorRepo;
import uz.pdp.bookstoreproject.repo.impl.AuthorRepoImpl;
import uz.pdp.bookstoreproject.service.BookService;
import uz.pdp.bookstoreproject.service.impl.BookServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet(name = "UpdateBookServlet", value = "/admin/update")
public class UpdateBookServlet extends HttpServlet {
    private final BookService bookService = BookServiceImpl.getInstance();
    private final AuthorRepo authorRepo = AuthorRepoImpl.getInstance();
    private final String path = "/home/ismoil_0709/Pictures/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("id", req.getParameter("id"));
        getServletContext().getRequestDispatcher("/admin/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Author> list = null;
            long id = Long.parseLong((String) req.getSession().getAttribute("id"));
            if (req.getParameter("author") != null) {
                String[] authors = req.getParameter("author").split("-");
                if (authors.length != 0) {
                    list = new ArrayList<>();
                    for (String a : authors) {
                        Author author = new Author();
                        Author byFullName = authorRepo.getByFullName(a);
                        if (byFullName != null) author = authorRepo.update(byFullName);
                        else author.setFull_name(a);
                        list.add(author);
                    }
                }
            }
            Book book = Book.builder()
                    .title(req.getParameter("title"))
                    .description(req.getParameter("description"))
                    .authors(list).build();
            try {
                Part new_cover = req.getPart("cover");
                Files.copy(new_cover.getInputStream(), Paths.get(path + new_cover.getSubmittedFileName()), StandardCopyOption.REPLACE_EXISTING);
                book.setCover_path(new_cover.getSubmittedFileName());
            } catch (Exception ignored){

            }
            try {
                Part new_file = req.getPart("file");
                if (!new_file.getContentType().contains("pdf")) {
                    req.getSession().setAttribute("message", "please input only pdf file");
                    resp.sendRedirect("/admin/update");
                    return;
                }
                Files.copy(new_file.getInputStream(), Paths.get(path + new_file.getSubmittedFileName()), StandardCopyOption.REPLACE_EXISTING);
                book.setPath(new_file.getSubmittedFileName());
            }catch (Exception ignored){

            }
            if (req.getParameter("price") != null) book.setPrice(Double.parseDouble(req.getParameter("price")));
            bookService.update(id, book);
            resp.sendRedirect("/books?id=" + id);
        } catch (Exception e) {
            req.getSession().setAttribute("message", e.getMessage());
            resp.sendRedirect("/admin/update");
        }
    }
}
