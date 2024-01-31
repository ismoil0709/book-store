package uz.pdp.bookstoreproject.service;

import uz.pdp.bookstoreproject.entity.Author;
import uz.pdp.bookstoreproject.entity.Book;

import java.util.List;

public interface BookService {
    Long save(String title, String description, String coverPath, String book_path, Double price, String... authors);
    void update(Long id,Book book);
    void delete(Long id);
    Book getById(Long id);
    List<Book> getByTitle(String title);
    List<Book> getByPrice(Double price);
    List<Book> getByAuthor(String author);
    List<Book> getByRating(Float rating);
    List<Book> getAll();
}
