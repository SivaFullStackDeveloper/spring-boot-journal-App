package net.engineeringdigest.journalApp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import net.engineeringdigest.journalApp.service.RedisService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private RedisService redisService;
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
        if (userInDb == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        userInDb.setUserName(user.getUserName());
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        Optional<User> savedUser =userService.saveUser(userInDb);
        return new ResponseEntity<>(savedUser, HttpStatus.NOT_FOUND);
    }



    @GetMapping("/getById/{myId}")
    public Optional<User> getAll(@PathVariable ObjectId myId) throws JsonProcessingException {
        // Try to get the user from Redis (this will return null if not found)
        User userFromRedis = redisService.getToRedis(myId.toString(), User.class);

        if (userFromRedis == null) {
            // If not found in Redis, fetch from the database
            Optional<User> userFromDb = userService.getUserById(myId);

            // If user found in DB, set to Redis and return
            userFromDb.ifPresent(user -> {
                try {
                    redisService.setToRedis(myId.toString(), user, 1L);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });

            return userFromDb;  // Return the user from the DB (wrapped in Optional)
        }

        // If user found in Redis, wrap it in Optional and return
        return Optional.of(userFromRedis);
    }




    @DeleteMapping("/deleteById/{myId}")
    public void userDeleteById(@PathVariable ObjectId myId) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // userRepo.deleteByUserName(authentication.getName());
         userService.userDeleteById(myId);
    }


    @GetMapping("/greetings")
    public ResponseEntity<?> greeting(){

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
