package com.user.controller;

import com.user.entity.JwtToken;
import com.user.model.AuthenticationResponseInfo;
import com.user.repository.JwtTokenRepository;
import com.user.service.JwtUtilService;
import com.user.service.LoginUserDetailsService;
import com.user.staticdata.TokenType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LoginController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class,useDefaultFilters = false)
//@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private LoginUserDetailsService userDetailsService;

    @MockBean
    private JwtUtilService jwtUtilService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private JwtTokenRepository jwtTokenRepository;

    LocalDateTime localDateTime = LocalDateTime.now();
    ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    long currentLong = zdt.toInstant().toEpochMilli();

    LocalDateTime accessTokenExpirationTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(currentLong + 5000000),
                    TimeZone.getDefault().toZoneId());
    LocalDateTime refreshTokenExpirationTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(currentLong + 10000000), TimeZone.getDefault().toZoneId());

    @Test
    public void loginTest() throws Exception {
        String requestBody = "{ \"userName\":\"kishore@gmail.com\",\"password\":\"1234\"}";
        UserDetails userDetails = new org.springframework.security.core.userdetails.User("kishore@gmail.com",
                "133", new ArrayList<>());
        AuthenticationResponseInfo authenticationResponseInfo = new AuthenticationResponseInfo();
        authenticationResponseInfo.setAccessTokenExpiration(accessTokenExpirationTime);
        authenticationResponseInfo.setId(2l);
        authenticationResponseInfo.setAccessToken("accessfsdsadfsdafsd");
        authenticationResponseInfo.setUserName("kishore@gmail.com");

        JwtToken jwtToken = new JwtToken();
        jwtToken.setUserName("suresh@gmail.com");
        jwtToken.setAccessToken("accessfsdsadfsdafsd");
        jwtToken.setRefreshToken("refreshfasfsafsadfdsf");
        jwtToken.setAccessTokenExpiration(accessTokenExpirationTime);
        jwtToken.setRefreshTokenExpiration(refreshTokenExpirationTime);
        jwtToken.setId(2l);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(userDetailsService.generateAuthResponse(userDetails,TokenType.ACCESS_TOKEN))
                .thenReturn(authenticationResponseInfo);
        when(userDetailsService.saveJwt(any(AuthenticationResponseInfo.class))).thenReturn(jwtToken);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        System.out.println("Response " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void loginValidationTest() throws Exception {
        String requestBody = "{ \"userName\":\"\",\"password\":\"1234\"}";
        UserDetails userDetails = new org.springframework.security.core.userdetails.User("kishore@gmail.com",
                "133", new ArrayList<>());
        AuthenticationResponseInfo authenticationResponseInfo = new AuthenticationResponseInfo();
        authenticationResponseInfo.setAccessTokenExpiration(accessTokenExpirationTime);
        authenticationResponseInfo.setId(2l);
        authenticationResponseInfo.setAccessToken("accessfsdsadfsdafsd");
        authenticationResponseInfo.setUserName("kishore@gmail.com");

        JwtToken jwtToken = new JwtToken();
        jwtToken.setUserName("suresh@gmail.com");
        jwtToken.setAccessToken("accessfsdsadfsdafsd");
        jwtToken.setRefreshToken("refreshfasfsafsadfdsf");
        jwtToken.setAccessTokenExpiration(accessTokenExpirationTime);
        jwtToken.setRefreshTokenExpiration(refreshTokenExpirationTime);
        jwtToken.setId(2l);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(userDetailsService.generateAuthResponse(userDetails,TokenType.ACCESS_TOKEN))
                .thenReturn(authenticationResponseInfo);
        when(userDetailsService.saveJwt(any(AuthenticationResponseInfo.class))).thenReturn(jwtToken);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andReturn();
        System.out.println("Response " + mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void loginTestBadRequest() throws Exception {
        String requestBody = "{ \"userName\":\"kishore@gmail.com\",\"password\":\"1234\"}";
        UserDetails userDetails = new org.springframework.security.core.userdetails.User("kishore@gmail.com",
                "133", new ArrayList<>());
        AuthenticationResponseInfo authenticationResponseInfo = new AuthenticationResponseInfo();
        authenticationResponseInfo.setAccessTokenExpiration(accessTokenExpirationTime);
        authenticationResponseInfo.setId(2l);
        authenticationResponseInfo.setAccessToken("accessfsdsadfsdafsd");
        authenticationResponseInfo.setUserName("kishore@gmail.com");

        JwtToken jwtToken = new JwtToken();
        jwtToken.setUserName("suresh@gmail.com");
        jwtToken.setAccessToken("accessfsdsadfsdafsd");
        jwtToken.setRefreshToken("refreshfasfsafsadfdsf");
        jwtToken.setAccessTokenExpiration(accessTokenExpirationTime);
        jwtToken.setRefreshTokenExpiration(refreshTokenExpirationTime);
        jwtToken.setId(2l);

//        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
//        when(userDetailsService.generateAuthResponse(userDetails,TokenType.ACCESS_TOKEN))
//                .thenReturn(authenticationResponseInfo);
        when(userDetailsService.saveJwt(any(AuthenticationResponseInfo.class))).thenReturn(jwtToken);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
//                .andExpect(content().json(
//                        "{id:1,firstName:Suresh,lastName:Kesavan,email:suresh@gmail.com,fee: 1000,tax:100,totalValue:1100}"))
                .andReturn();
        System.out.println("Response " + mvcResult.getResponse().getContentAsString());
    }
}
