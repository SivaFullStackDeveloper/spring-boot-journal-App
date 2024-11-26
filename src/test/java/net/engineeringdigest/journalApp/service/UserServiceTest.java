package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepo userRepo ;
   @Test
   public void testAdd(){

        assertNotNull(userRepo.findByUserName("siva"));
    }
}
