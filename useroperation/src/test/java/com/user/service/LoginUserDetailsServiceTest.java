package com.user.service;

import com.user.common.Utils;
import com.user.entity.JwtToken;
import com.user.entity.User;
import com.user.entity.UserPassword;
import com.user.model.AuthenticationResponseInfo;
import com.user.repository.JwtTokenRepository;
import com.user.repository.UserRepository;
import com.user.service.JwtUtilService;
import com.user.service.LoginUserDetailsService;
import com.user.staticdata.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginUserDetailsServiceTest {

    @InjectMocks
    private LoginUserDetailsService loginUserDetailsService;

    @Mock
    private JwtTokenRepository jwtTokenRepository;

    @Mock
    private JwtUtilService jwtUtilService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Utils utils;


    @Test
    public void loadByUserNameTest() {

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
        when(userRepository.findUserByEmail(anyString())).thenReturn(user);
        UserDetails userDetails =
                loginUserDetailsService.loadUserByUsername("suresh@gmail.com");
        Assertions.assertNotNull(userDetails);

    }

    @Test
    public void generateAuthResponseTest() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User("suresh@gmail.com",
                "133", new ArrayList<>());
        String refreshToken = "refreshfsafsdafsafsdfsd";
        String accessToken = "access:asggfdgdfgdfgf";

        JwtToken jwtToken = new JwtToken();
        jwtToken.setUserName("suresh@gmail.com");
        jwtToken.setRefreshToken(refreshToken);
        jwtToken.setAccessToken(accessToken);
        jwtToken.setRefreshTokenExpiration(LocalDateTime.now());
        jwtToken.setAccessTokenExpiration(LocalDateTime.now());
        jwtToken.setId(2l);
//        Claims claims =Jwts.claims();
        when(jwtTokenRepository.findByUserName(anyString())).thenReturn(jwtToken);
        when(jwtUtilService.generateAccessToken(userDetails)).thenReturn(accessToken);
        Claims claims = Jwts.claims();
        claims.setExpiration(new Date());
        when(jwtUtilService.extractAllClaims(anyString())).thenReturn(claims);
//        when(jwtUtilService.generateRefreshToken(userDetails)).thenReturn(refreshToken);
        when(jwtUtilService.extractAllClaims(anyString())).thenReturn(claims);
        when(utils.convertToLocalDateTime(anyLong())).thenReturn(LocalDateTime.now());
        when(utils.convertToLocalDateTime(anyLong())).thenReturn(LocalDateTime.now());
        AuthenticationResponseInfo authenticationResponseInfo =
                loginUserDetailsService.generateAuthResponse(userDetails, TokenType.ACCESS_TOKEN);
        Assertions.assertNotNull(authenticationResponseInfo);
        Assertions.assertEquals("suresh@gmail.com", authenticationResponseInfo.getUserName());
    }

//    @Test
//    public void saveJwtTokenTest() {
//        String refreshToken = "refreshfsafsdafsafsdfsd";
//        String accessToken = "access:asggfdgdfgdfgf";
//        JwtToken jwtToken = new JwtToken();
//        jwtToken.setUserName("suresh@gmail.com");
//        jwtToken.setRefreshToken(refreshToken);
//        jwtToken.setAccessToken(accessToken);
//        jwtToken.setRefreshTokenExpiration(LocalDateTime.now());
//        jwtToken.setAccessTokenExpiration(LocalDateTime.now());
//        jwtToken.setId(2l);
//        AuthenticationResponseInfo authenticationResponseInfo = new AuthenticationResponseInfo();
//        authenticationResponseInfo.setAccessToken(accessToken);
//        authenticationResponseInfo.setRefreshToken(refreshToken);
//        authenticationResponseInfo.setId(2l);
//        authenticationResponseInfo.setUserName("suresh@gmail.com");
//        authenticationResponseInfo.setRefreshTokenExpiration(LocalDateTime.now());
//        authenticationResponseInfo.setAccessTokenExpiration(LocalDateTime.now());
//        when(jwtTokenRepository.save(jwtToken)).thenReturn(jwtToken);
////        doReturn(when(jwtTokenRepository.save(jwtToken)));
//        JwtToken mockedJwtToken = loginUserDetailsService.saveJwt(authenticationResponseInfo);
//        Assertions.assertNotNull(mockedJwtToken);
//    }
}
