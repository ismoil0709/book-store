package uz.pdp.bookstoreproject.repo;

import uz.pdp.bookstoreproject.entity.Author;

public interface AuthorRepo extends CRUDRepo<Author,Long>{
    Author getByFullName(String name);
}
