package com.user.controller;

import com.user.entity.User;
import com.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class, useDefaultFilters = false)
//@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void updateUser() throws Exception {
        String requestBody = "{ \"id\":1, \"firstName\":\"Suresh\",\"lastName\":\"Kesavan\",\"email\":\"suresh@gmail.com\",\"password\":\"123\",\"mobileNo\":1233444}";
        User user = new User();
        user.setId(1l);
//        user.setPassword("123");
        user.setMobileNo("12383");
        user.setEmail("suresh@gmail.com");
        user.setFirstName("Suresh");
        user.setLastName("Stalin");
        when(userService.save(Mockito.any(User.class))).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/users/1")
                .content(requestBody)
                .header("Authorization", "Bearer teststringsdfdfdf")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println("Response " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getUserById() throws Exception {
        User user = new User();
        user.setId(1l);
//        user.setPassword("123");
        user.setMobileNo("12383");
        user.setEmail("suresh@gmail.com");
        user.setFirstName("Suresh");
        user.setLastName("Stalin");
        when(userService.getUser(Mockito.anyLong())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/users/id/1")
                .header("Authorization", "Bearer teststringsdfdfdf")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println("Response " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getUserByEmail() throws Exception {
        User user = new User();
        user.setId(1l);
//        user.setPassword("123");
        user.setMobileNo("12383");
        user.setEmail("suresh@gmail.com");
        user.setFirstName("Suresh");
        user.setLastName("Stalin");
        when(userService.getUserByEmail(Mockito.anyString())).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/users/email/{emailId}", "suresh@gmail.com")
                .header("Authorization", "Bearer teststringsdfdfdf")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println("Response " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1l);
//        user1.setPassword("123");
        user1.setMobileNo("12383");
        user1.setEmail("suresh@gmail.com");
        user1.setFirstName("Suresh");
        user1.setLastName("Stalin");

        User user2 = new User();
        user2.setId(1l);
//        user2.setPassword("123");
        user2.setMobileNo("12383");
        user2.setEmail("kesavan@gmail.com");
        user2.setFirstName("Suresh");
        user2.setLastName("kesavan");
        List<User> userList = new ArrayList<>();
        when(userService.getAllUser()).thenReturn(userList);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/users")
                .header("Authorization", "Bearer teststringsdfdfdf")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteUserById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/users/1")
                .header("Authorization", "Bearer teststringsdfdfdf")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
    }
}
