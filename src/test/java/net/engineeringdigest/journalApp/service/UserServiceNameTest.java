package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mockito.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

public class UserServiceNameTest {
    @MockBean
    private UserService userService;

    void  getUserByUserNameTest(){
        when(userService.findByUserName(ArgumentMatchers.anyString())).thenReturn(new User());
        userService.findByUserName("siva");
    }
}
