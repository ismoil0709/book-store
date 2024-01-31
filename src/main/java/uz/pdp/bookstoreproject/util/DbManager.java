package uz.pdp.bookstoreproject.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DbManager {
    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-orm");

    public static <T> T persist(Class<T> tClass, Object o) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(o);
            entityManager.getTransaction().commit();
            return tClass.cast(o);
        }
    }

    public static <T> T find(Class<T> tClass, Object o) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.find(tClass, o);
        }
    }
    public static <T> T update(Class<T> tClass,Object o){
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.merge(o);
            entityManager.getTransaction().commit();
            return tClass.cast(o);
        }
    }
    public static <T> boolean delete(Class<T> tClass,Object o){
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            if (!entityManager.contains(o)) {
                o = entityManager.merge(o);
            }
            entityManager.remove(o);
            entityManager.getTransaction().commit();
            return true;
        }
    }
}
