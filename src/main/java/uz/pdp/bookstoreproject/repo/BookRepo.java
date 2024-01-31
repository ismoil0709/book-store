package uz.pdp.bookstoreproject.repo;

import uz.pdp.bookstoreproject.entity.Author;
import uz.pdp.bookstoreproject.entity.Book;

import java.util.List;

public interface BookRepo extends CRUDRepo<Book,Long> {
    List<Book> getByTitle(String title);
    List<Book> getByPrice(Double price);
    List<Book> getByAuthor(Author author);
    List<Book> getByRating(Float rating);}
