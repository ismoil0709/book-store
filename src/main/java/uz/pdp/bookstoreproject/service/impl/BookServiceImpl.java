package uz.pdp.bookstoreproject.service.impl;

import uz.pdp.bookstoreproject.entity.Author;
import uz.pdp.bookstoreproject.entity.Book;
import uz.pdp.bookstoreproject.repo.AuthorRepo;
import uz.pdp.bookstoreproject.repo.BookRepo;
import uz.pdp.bookstoreproject.repo.impl.AuthorRepoImpl;
import uz.pdp.bookstoreproject.repo.impl.BookRepoImpl;
import uz.pdp.bookstoreproject.service.BookService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BookServiceImpl implements BookService {
    private BookServiceImpl() {
    }

    private static BookService instance;
    private final BookRepo bookRepo = BookRepoImpl.getInstance();
    private final AuthorRepo authorRepo = AuthorRepoImpl.getInstance();

    @Override
    public Long save(String title, String description, String coverPath, String book_path, Double price, String... authors) {
        if (title == null || title.isEmpty()) throw new IllegalArgumentException("Title is null");
        if (description == null || description.isEmpty()) throw new IllegalArgumentException("Description is null");
        if (book_path == null || book_path.isEmpty()) throw new IllegalArgumentException("Book is null");
        if (price == null || price.isNaN()) throw new IllegalArgumentException("Price is null");
        if (authors == null) throw new IllegalArgumentException("Authors is null");
        List<Author> authorList = new ArrayList<>();
        for (String a:authors){
            Author author = new Author();
            Author byFullName = authorRepo.getByFullName(a);
            if (byFullName != null) author = authorRepo.update(byFullName);
            else author.setFull_name(a);
            authorList.add(author);
        }
        Book book = Book.builder()
                .title(title)
                .description(description)
                .cover_path(coverPath)
                .path(book_path)
                .price(price)
                .authors(authorList)
                .build();
        Book save = bookRepo.save(book);
        return save.getId();
    }

    @Override
    public void update(Long id, Book book) {
        Book byId = bookRepo.getById(id);
        if (byId == null) throw new IllegalArgumentException("Invalid book id");
        byId.setTitle(Objects.requireNonNullElse(book.getTitle(), byId.getTitle()));
        byId.setDescription(Objects.requireNonNullElse(book.getDescription(), byId.getDescription()));
        byId.setPath(Objects.requireNonNullElse(book.getPath(), byId.getPath()));
        byId.setCover_path(Objects.requireNonNullElse(book.getCover_path(), byId.getCover_path()));
        byId.setAuthors(Objects.requireNonNullElse(book.getAuthors(), byId.getAuthors()));
        byId.setPrice(Objects.requireNonNullElse(book.getPrice(), byId.getPrice()));
        bookRepo.update(byId);
    }

    @Override
    public void delete(Long id) {
        Book byId = bookRepo.getById(id);
        if (byId == null) throw new IllegalArgumentException("Invalid book id");
        bookRepo.delete(byId);
    }

    @Override
    public Book getById(Long id) {
        Book byId = bookRepo.getById(id);
        if (byId == null) throw new IllegalArgumentException("Book not found");
        return byId;
    }

    @Override
    public List<Book> getByTitle(String title) {
        List<Book> byTitle = bookRepo.getByTitle(title);
        if (byTitle == null) throw new IllegalArgumentException("Not found");
        return byTitle;
    }

    @Override
    public List<Book> getByPrice(Double price) {
        List<Book> byPrice = bookRepo.getByPrice(price);
        if (byPrice == null) throw new IllegalArgumentException("Not found");
        return byPrice;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        Author byFullName = authorRepo.getByFullName(author);
        if (byFullName == null) throw new IllegalArgumentException("Author not found");
        return bookRepo.getByAuthor(byFullName);
    }

    @Override
    public List<Book> getByRating(Float rating) {
        return bookRepo.getByRating(rating);
    }

    public List<Book> getAll() {
        return bookRepo.getAll();
    }

    public static BookService getInstance() {
        if (instance == null) instance = new BookServiceImpl();
        return instance;
    }
}
