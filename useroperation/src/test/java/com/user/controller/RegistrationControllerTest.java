package com.user.controller;

import com.user.config.SecurityConfig;
import com.user.controller.RegistrationController;
import com.user.entity.User;
import com.user.entity.UserPassword;
import com.user.filters.JwtRequestFilter;
import com.user.service.JwtUtilService;
import com.user.service.LoginUserDetailsService;
import com.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RegistrationController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,useDefaultFilters = false)
//@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RegistrationController.class)
public class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void registerUserTest() throws Exception {
        String requestBody = "{ \"firstName\":\"Suresh\",\"lastName\":\"Kesavan\",\"email\":\"suresh@gmail.com\",\"password\":\"123\",\"mobileNo\":1233444}";
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

        when(userService.save(Mockito.any(User.class))).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/register")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
//                .andExpect(content().json(
//                        "{id:1,firstName:Suresh,lastName:Kesavan,email:suresh@gmail.com,fee: 1000,tax:100,totalValue:1100}"))
                .andReturn();
        System.out.println("Response " + mvcResult.getResponse().getContentAsString());
    }
}
