package uz.pdp.bookstoreproject.repo;

import uz.pdp.bookstoreproject.entity.User;

public interface UserRepo extends CRUDRepo<User,Long>{
    User getByUsername(String username);
    User getByEmail(String email);
}
