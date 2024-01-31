package uz.pdp.bookstoreproject.repo;

import java.util.List;

public interface CRUDRepo<T,I> {
    T save(T t);
    T update(T t);
    T getById(I i);
    boolean delete(T i);
    List<T> getAll();
}
