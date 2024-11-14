package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;


    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> saveUser(User journalEntry) {
        return Optional.of(userRepo.save(journalEntry));

    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepo.findById(id);

    }

    public void userDeleteById(ObjectId id) {
        userRepo.deleteById(id);

    }

    public User findByUserName(String name) {
        return userRepo.findByUserName(name);


    }
}
