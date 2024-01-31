package uz.pdp.bookstoreproject.service.impl;

import uz.pdp.bookstoreproject.entity.User;
import uz.pdp.bookstoreproject.repo.UserRepo;
import uz.pdp.bookstoreproject.repo.impl.UserRepoImpl;
import uz.pdp.bookstoreproject.service.UserService;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService{
    private UserServiceImpl(){}
    private static UserService instance;
    private final UserRepo userRepo = UserRepoImpl.getInstance();
    public void register(String name,String surname,String email,String username,String password){
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name is null");
        if (email == null || email.isEmpty()) throw new IllegalArgumentException("Email is null");
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("Username is null");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password is null");
        if (userRepo.getByUsername(username) != null) throw new IllegalArgumentException("Username already exist");
        if (userRepo.getByEmail(email) != null) throw new IllegalArgumentException("Email already exist");
        if (surname == null) surname = "unknown";
        User user = User.builder()
                .username(username)
                .email(email)
                .password(password)
                .name(name)
                .surname(surname)
                .build();
        userRepo.save(user);
    }
    public User login(String username,String password){
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("Username is null");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password is null");
        User byUsername = userRepo.getByUsername(username);
        if (byUsername == null) throw new IllegalArgumentException("User not found");
        if (!byUsername.getPassword().equals(password)) throw new IllegalArgumentException("Incorrect password");
        return byUsername;
    }
    public void editProfile(Long id,User user){
        if (user.getUsername() != null && userRepo.getByUsername(user.getUsername()) != null) throw new IllegalArgumentException("Username already exist");
        if (user.getEmail() != null && userRepo.getByEmail(user.getEmail()) != null) throw new IllegalArgumentException("Email already exist");
        User byId = userRepo.getById(id);
        byId.setName(Objects.requireNonNullElse(user.getName(),byId.getName()));
        byId.setSurname(Objects.requireNonNullElse(user.getSurname(),byId.getSurname()));
        byId.setEmail(Objects.requireNonNullElse(user.getEmail(),byId.getEmail()));
        byId.setUsername(Objects.requireNonNullElse(user.getUsername(),byId.getUsername()));
        byId.setPassword(Objects.requireNonNullElse(user.getPassword(),byId.getPassword()));
        byId.setAdmin(user.isAdmin());
        userRepo.update(byId);
    }

    @Override
    public void deleteAccount(Long id) {
        User user = userRepo.getById(id);
        if (user == null) throw new IllegalArgumentException("Invalid id");
        userRepo.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userRepo.getAll();
    }

    @Override
    public User getById(Long id) {
        User user = userRepo.getById(id);
        if (user == null) throw new IllegalArgumentException("User not found");
        return user;
    }

    @Override
    public User getByUserName(String username) {
        return userRepo.getByUsername(username);
    }

    @Override
    public User getByEmail(String email) {
        return userRepo.getByEmail(email);
    }

    @Override
    public void makeAdmin(Long id) {
        User user = new User();
        user.setId(id);
        user.setAdmin(true);
        editProfile(id,user);
    }

    @Override
    public void deleteAdmin(Long id) {
        User user = new User();
        user.setId(id);
        user.setAdmin(false);
        editProfile(id,user);
    }

    public static UserService getInstance() {
        if (instance == null) instance = new UserServiceImpl();
        return instance;
    }
}
