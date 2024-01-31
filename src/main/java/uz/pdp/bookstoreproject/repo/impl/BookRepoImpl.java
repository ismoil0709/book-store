package uz.pdp.bookstoreproject.repo.impl;

import jakarta.persistence.EntityManager;
import uz.pdp.bookstoreproject.entity.Author;
import uz.pdp.bookstoreproject.entity.Book;
import uz.pdp.bookstoreproject.repo.BookRepo;
import uz.pdp.bookstoreproject.util.DbManager;

import java.util.List;

public class BookRepoImpl implements BookRepo {
    private BookRepoImpl() {
    }

    private static BookRepo instance;

    @Override
    public Book save(Book book) {
        return DbManager.persist(Book.class, book);
    }

    @Override
    public Book update(Book book) {
        return DbManager.update(Book.class, book);
    }

    @Override
    public Book getById(Long aLong) {
        return DbManager.find(Book.class, aLong);
    }

    @Override
    public boolean delete(Book book) {
        return DbManager.delete(Book.class, book);
    }

    @Override
    public List<Book> getAll() {
        try (EntityManager entityManager = DbManager.entityManagerFactory.createEntityManager()) {
            return entityManager.createNamedQuery("get_all_books", Book.class).getResultList();
        }
    }

    public static BookRepo getInstance() {
        if (instance == null) instance = new BookRepoImpl();
        return instance;
    }

    @Override
    public List<Book> getByTitle(String title) {
        try (EntityManager entityManager = DbManager.entityManagerFactory.createEntityManager()) {
            return entityManager.createNamedQuery("get_by_title", Book.class).setParameter("title", title).getResultList();
        }
    }

    @Override
    public List<Book> getByPrice(Double price) {
        try (EntityManager entityManager = DbManager.entityManagerFactory.createEntityManager()) {
            return entityManager.createNamedQuery("get_by_price", Book.class).setParameter("price", price).getResultList();
        }
    }

    @Override
    public List<Book> getByAuthor(Author author) {
        return AuthorRepoImpl.getInstance().getById(author.getId()).getBooks();
    }

    @Override
    public List<Book> getByRating(Float rating) {
        try (EntityManager entityManager = DbManager.entityManagerFactory.createEntityManager()) {
            return entityManager.createNamedQuery("get_by_rating", Book.class).setParameter("rating", rating).getResultList();
        }
    }
}
