package uz.pdp.bookstoreproject.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import uz.pdp.bookstoreproject.entity.Author;
import uz.pdp.bookstoreproject.repo.AuthorRepo;
import uz.pdp.bookstoreproject.util.DbManager;

import java.util.List;

public class AuthorRepoImpl implements AuthorRepo {
    private AuthorRepoImpl(){}
    private static AuthorRepo instance;
    @Override
    public Author save(Author author) {
        return DbManager.persist(Author.class,author);
    }

    @Override
    public Author update(Author author) {
        return DbManager.update(Author.class,author);
    }

    @Override
    public Author getById(Long aLong) {
        return DbManager.find(Author.class,aLong);
    }

    @Override
    public boolean delete(Author author) {
        return DbManager.delete(Author.class,author);
    }

    @Override
    public List<Author> getAll() {
        try (EntityManager entityManager = DbManager.entityManagerFactory.createEntityManager()) {
            return entityManager.createNamedQuery("get_all_authors",Author.class).getResultList();
        }
    }

    public static AuthorRepo getInstance() {
        if (instance == null) instance = new AuthorRepoImpl();
        return instance;
    }

    @Override
    public Author getByFullName(String name) {
        try (EntityManager entityManager = DbManager.entityManagerFactory.createEntityManager()) {
            return entityManager.createNamedQuery("get_by_name",Author.class).setParameter("name",name).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
