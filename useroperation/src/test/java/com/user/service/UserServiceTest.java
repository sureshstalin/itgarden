package com.user.service;


import com.user.entity.User;
import com.user.entity.UserPassword;
import com.user.exception.DuplicateResourceFoundException;
import com.user.exception.InvalidInputException;
import com.user.exception.ResourceNotFoundException;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    public void saveNewUserTest() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            User user = new User();
//            user.setPassword("133");
            user.setDateCreated(LocalDateTime.now());
            user.setEmail("suresh@gmail.com");
            user.setFirstName("Suresh");
            user.setLastName("Kesavan");
            user.setMobileNo("83838833");
            List<UserPassword> userPasswords = new ArrayList<>();
            UserPassword userPassword = new UserPassword();
            userPassword.setPassword("123");
            userPassword.setUser(user);
            userPassword.setAppName("test");
            userPassword.setId(1l);
            userPasswords.add(userPassword);
            user.setUserPasswords(userPasswords);

            UserRepository userRepository = mock(UserRepository.class);
            when(userRepository.findUserByEmail(anyString())).thenReturn(null);
//            when(passwordEncoder.encode(anyString())).thenReturn(encoder.encode(user.getPassword()));
            UserService userService = new UserService(userRepository, passwordEncoder);
            when(userRepository.save(user)).thenReturn(user);
            User newUser = userService.save(user);
            Assertions.assertNotNull(newUser);
            Assertions.assertEquals(user.getEmail(), newUser.getEmail());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void duplicateUserTest() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            User user = new User();
//            user.setPassword("133");
            user.setDateCreated(LocalDateTime.now());
            user.setEmail("suresh@gmail.com");
            user.setFirstName("Suresh");
            user.setLastName("Kesavan");
            user.setMobileNo("83838833");
            List<UserPassword> userPasswords = new ArrayList<>();
            UserPassword userPassword = new UserPassword();
            userPassword.setPassword("123");
            userPassword.setUser(user);
            userPassword.setAppName("test");
            userPassword.setId(1l);
            userPasswords.add(userPassword);
            user.setUserPasswords(userPasswords);
            UserRepository userRepository = mock(UserRepository.class);
            User dupUser = new User();
            dupUser.setId(133l);
            user.setEmail("suresh@gmail.com");
            when(userRepository.findUserByEmail(anyString())).thenReturn(dupUser);
//            when(passwordEncoder.encode(anyString())).thenReturn(encoder.encode(user.getPassword()));
            UserService userService = new UserService(userRepository, passwordEncoder);
//            when(userRepository.save(user)).thenReturn(user);
            User newUser = userService.save(user);
            Assertions.assertThrows(DuplicateResourceFoundException.class, () -> {
//                Integer.parseInt("One");
            });
//            Assertions.assertEquals(user.getEmail(),newUser.getEmail());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals("Email id already exist", e.getMessage());
        }
    }

    @Test
    public void saveExistingUserTest() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            User user = new User();
            user.setId(22l);
//            user.setPassword("133");
            user.setDateCreated(LocalDateTime.now());
            user.setEmail("suresh@gmail.com");
            user.setFirstName("Suresh");
            user.setLastName("Kesavan");
            user.setMobileNo("83838833");
            List<UserPassword> userPasswords = new ArrayList<>();
            UserPassword userPassword = new UserPassword();
            userPassword.setPassword("123");
            userPassword.setUser(user);
            userPassword.setAppName("test");
            userPassword.setId(1l);
            userPasswords.add(userPassword);
            user.setUserPasswords(userPasswords);
            UserRepository userRepository = mock(UserRepository.class);
            when(userRepository.findUserByEmail(anyString())).thenReturn(null);
            UserService userService = new UserService(userRepository, passwordEncoder);
            when(userRepository.save(user)).thenReturn(user);
            User existingUser = userService.save(user);
            Assertions.assertNotNull(existingUser);
            Assertions.assertNotNull(user.getId());
            Assertions.assertEquals(user.getEmail(), existingUser.getEmail());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getUserTest() {
        User user = new User();
//            user.setPassword("133");
        user.setId(2l);
        user.setDateCreated(LocalDateTime.now());
        user.setEmail("suresh@gmail.com");
        user.setFirstName("Suresh");
        user.setLastName("Kesavan");
        user.setMobileNo("83838833");
        List<UserPassword> userPasswords = new ArrayList<>();
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword("123");
        userPassword.setUser(user);
        userPassword.setAppName("test");
        userPassword.setId(1l);
        userPasswords.add(userPassword);
        user.setUserPasswords(userPasswords);
        Optional<User> optionalUser = Optional.of(user);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findById(anyLong())).thenReturn(optionalUser);
        UserService userService = new UserService(userRepository, passwordEncoder);
        User myUser = userService.getUser(2l);
        Assertions.assertNotNull(myUser);
        Assertions.assertEquals("suresh@gmail.com",myUser.getEmail());
    }

    @Test
    public void getUserTestInvalid() {
        try {
            UserRepository userRepository = mock(UserRepository.class);
            when(userRepository.findById(anyLong())).thenReturn(null);
            UserService userService = new UserService(userRepository, passwordEncoder);
            User myUser = userService.getUser(2l);
            Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals("Resource not found", e.getMessage());
        }
    }

    @Test
    public void getUserByEmailTest() {
        User user = new User();
//            user.setPassword("133");
        user.setId(2l);
        user.setDateCreated(LocalDateTime.now());
        user.setEmail("suresh@gmail.com");
        user.setFirstName("Suresh");
        user.setLastName("Kesavan");
        user.setMobileNo("83838833");
        List<UserPassword> userPasswords = new ArrayList<>();
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword("123");
        userPassword.setUser(user);
        userPassword.setAppName("test");
        userPassword.setId(1l);
        userPasswords.add(userPassword);
        user.setUserPasswords(userPasswords);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findUserByEmail(anyString())).thenReturn(user);
        UserService userService = new UserService(userRepository, passwordEncoder);
        User myUser = userService.getUserByEmail("suresh@gmail.com");
        Assertions.assertNotNull(myUser);
        Assertions.assertNotNull(myUser.getId());
        Assertions.assertEquals(user.getEmail(), myUser.getEmail());
    }

    @Test
    public void getUserByEmailIvalidTest() {
        try {
            User user = new User();
//            user.setPassword("133");
            user.setId(2l);
            user.setDateCreated(LocalDateTime.now());
            user.setEmail("suresh@gmail.com");
            user.setFirstName("Suresh");
            user.setLastName("Kesavan");
            user.setMobileNo("83838833");
            List<UserPassword> userPasswords = new ArrayList<>();
            UserPassword userPassword = new UserPassword();
            userPassword.setPassword("123");
            userPassword.setUser(user);
            userPassword.setAppName("test");
            userPassword.setId(1l);
            userPasswords.add(userPassword);
            user.setUserPasswords(userPasswords);
            UserRepository userRepository = mock(UserRepository.class);
            when(userRepository.findUserByEmail(anyString())).thenReturn(null);
            UserService userService = new UserService(userRepository, passwordEncoder);
            User myUser = userService.getUserByEmail("suresh1@gmail.com");
            Assertions.assertThrows(InvalidInputException.class, () -> {
            });
        }catch (InvalidInputException e) {
            System.out.println("Exception " + e.getErrorList());
        }
    }

    @Test
    public void getAllUserTest() {

        List<User> userList = new ArrayList<>();
        User user1 = new User();
//            user.setPassword("133");
        user1.setDateCreated(LocalDateTime.now());
        user1.setEmail("suresh1@gmail.com");
        user1.setFirstName("Suresh");
        user1.setLastName("Kesavan");
        user1.setMobileNo("83838833");
        List<UserPassword> userPasswords = new ArrayList<>();
        UserPassword userPassword = new UserPassword();
        userPassword.setPassword("123");
        userPassword.setUser(user1);
        userPassword.setAppName("test");
        userPassword.setId(1l);
        userPasswords.add(userPassword);
        user1.setUserPasswords(userPasswords);

        User user2 = new User();
//            user.setPassword("133");
        user2.setDateCreated(LocalDateTime.now());
        user2.setEmail("suresh2@gmail.com");
        user2.setFirstName("Suresh");
        user2.setLastName("Kesavan");
        user2.setMobileNo("83838833");
        List<UserPassword> userPasswords2 = new ArrayList<>();
        UserPassword userPassword2 = new UserPassword();
        userPassword2.setPassword("123");
        userPassword2.setUser(user2);
        userPassword2.setAppName("test");
        userPassword2.setId(1l);
        userPasswords2.add(userPassword);
        user2.setUserPasswords(userPasswords);
        userList.add(user1);
        userList.add(user2);

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(userList);
        UserService userService = new UserService(userRepository, passwordEncoder);
        List<User> users = userService.getAllUser();
        Assertions.assertNotNull(users);
        Assertions.assertEquals(userList.size(), users.size());
        Assertions.assertEquals("suresh1@gmail.com", users.get(0).getEmail());
        Assertions.assertEquals("suresh2@gmail.com", users.get(1).getEmail());

    }

    @Test
    public void deleteByUserIdTest() {
        try {
            User user = new User();
//            user.setPassword("133");
            user.setId(2l);
            user.setDateCreated(LocalDateTime.now());
            user.setEmail("suresh@gmail.com");
            user.setFirstName("Suresh");
            user.setLastName("Kesavan");
            user.setMobileNo("83838833");
            List<UserPassword> userPasswords = new ArrayList<>();
            UserPassword userPassword = new UserPassword();
            userPassword.setPassword("123");
            userPassword.setUser(user);
            userPassword.setAppName("test");
            userPassword.setId(1l);
            userPasswords.add(userPassword);
            user.setUserPasswords(userPasswords);
            UserRepository userRepository = mock(UserRepository.class);
            doNothing().when(userRepository).deleteById(anyLong());
            UserService userService = new UserService(userRepository, passwordEncoder);
            userService.deleteUserById(1l);
        }catch (InvalidInputException e) {
            System.out.println("Exception " + e.getErrorList());
        }
    }
}
