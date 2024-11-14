package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }


    @PostMapping("/createUser")
    public Optional<User> createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @PutMapping("/updateUser/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user,@PathVariable String username) {
        User userInDb = userService.findByUserName(username);
        if(userInDb!=null || userInDb.toString()!=""){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            Optional<User> savedUser =userService.saveUser(userInDb);
            return new ResponseEntity<>(savedUser, HttpStatus.NOT_FOUND);
        }
      return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }


    @GetMapping("/getById/{myId}")
    public Optional<User> getAll(@PathVariable ObjectId myId) {
        return userService.getUserById(myId);
    }

    @DeleteMapping("/deleteById/{myId}")
    public void userDeleteById(@PathVariable ObjectId myId) {
         userService.userDeleteById(myId);
    }

}
