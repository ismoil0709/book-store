package uz.pdp.bookstoreproject.repo.impl;

import jakarta.persistence.EntityManager;
import uz.pdp.bookstoreproject.entity.User;
import uz.pdp.bookstoreproject.repo.UserRepo;
import uz.pdp.bookstoreproject.util.DbManager;

import java.util.List;

public class UserRepoImpl implements UserRepo{
    private UserRepoImpl(){}
    private static UserRepo instance;

    public static UserRepo getInstance() {
        if (instance == null) instance = new UserRepoImpl();
        return instance;
    }

    @Override
    public User save(User user) {
        return DbManager.persist(User.class,user);
    }

    @Override
    public User update(User user) {
        return DbManager.update(User.class,user);
    }

    @Override
    public User getById(Long aLong) {
        return DbManager.find(User.class,aLong);
    }

    @Override
    public boolean delete(User aLong) {
        return DbManager.delete(User.class,aLong);
    }

    @Override
    public List<User> getAll() {
        try (EntityManager entityManager = DbManager.entityManagerFactory.createEntityManager()) {
            return entityManager.createNamedQuery("get_all_users",User.class).getResultList();
        }
    }

    @Override
    public User getByUsername(String username) {
        try (EntityManager entityManager = DbManager.entityManagerFactory.createEntityManager()) {
            return entityManager.createNamedQuery("get_by_username", User.class).setParameter("username", username).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public User getByEmail(String email) {
        try (EntityManager entityManager = DbManager.entityManagerFactory.createEntityManager()) {
            return entityManager.createNamedQuery("get_by_email",User.class).setParameter("email",email).getSingleResult();
        }catch (Exception e){
            return null;
        }
    }
}
