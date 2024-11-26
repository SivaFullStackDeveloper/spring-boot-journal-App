package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.beans.Encoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    //private static  final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.getAllUsers();
    }


    @PostMapping("/createUser")
    public Optional<User> createUser(@RequestBody User user) {
       // user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("user"));
        return userService.saveUser(user);
    }


    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      // String userName =  authentication.getName();
        User userInDb = userService.findByUserName(user.getUserName());
        if(userInDb!=null || userInDb.toString()!=""){
            userInDb.setUserName(user.getUserName());
            //user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // userRepo.deleteByUserName(authentication.getName());
         userService.userDeleteById(myId);
    }

}
