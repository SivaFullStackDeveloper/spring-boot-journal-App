//package net.engineeringdigest.journalApp.service;
//
//import net.engineeringdigest.journalApp.entity.User;
//import net.engineeringdigest.journalApp.repository.UserRepo;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTest {
//    @Autowired
//    private UserRepo userRepo ;
//    @Disabled
//    @Test
//   public void testFindUserByUserName(){
//
//        assertNotNull(userRepo.findByUserName("siva"));
//       User siva = userRepo.findByUserName("siva");
//
//       assertTrue(!siva.getJournalList().isEmpty());
//    }
//
//
//    @ParameterizedTest
//    @ValueSource(strings={
//            "siva",
//            "rqaj",
//            "siva1",
//    })
//    public void testFindUserByUserNameWithValuesLikesStringIntsetc(String username){
//
//        assertNotNull(userRepo.findByUserName(username));
//    }
//
//
//
////    @ParameterizedTest
////    @ArgumentsSource(strings={
////            "siva",
////            "rqaj",
////            "siva1",
////    })
////    public void testFindUserByUserNameWithCustomArguments(String username){
////
////        assertNotNull(userRepo.findByUserName(username));
////    }
//
//
//    @ParameterizedTest
//    @CsvSource({
//            "siva",
//            "rqaj",
//            "siva1",
//    })
//    public void testFindUserByUserNameWithCsv(String username){
//
//        assertNotNull(userRepo.findByUserName(username));
//    }
//
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,2,4",
//            "2,2,5"
//    })
//    public void testAdd(int a, int b,int expected){
//       assertEquals(expected,a+b);
//
//    }
//}
