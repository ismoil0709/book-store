package uz.pdp.bookstoreproject.service;

import uz.pdp.bookstoreproject.entity.User;

import java.util.List;

public interface UserService {
    void register(String name,String surname,String email,String username,String password);
    User login(String username,String password);
    void editProfile(Long id,User user);
    void deleteAccount(Long id);
    List<User> getAll();
    User getById(Long id);
    User getByUserName(String username);
    User getByEmail(String email);
    void makeAdmin(Long id);
    void deleteAdmin(Long id);
}
