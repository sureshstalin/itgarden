package com.user.service;

import com.user.entity.JwtToken;
import com.user.exception.InvalidInputException;
import com.user.repository.JwtTokenRepository;
import com.user.repository.UserRepository;
import com.user.staticdata.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JWTUtilServiceTest {


    @InjectMocks
    JwtUtilService jwtUtilService;

    @Mock
    private JwtTokenRepository jwtTokenRepository;

    @Mock
    private UserRepository userRepository;

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXJlc2hAZ21haWwuY29tIiwiZXhwIjoxNjM1ODgxNjEzLCJpYXQiOjE2MzU4NzY2MTN9.nueMnyDHrPjV2upod8JLzhCKAG17i74kRBM4YtJ6-0M";

    String invalidToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdXJlc2hAZ21haWwuY29tIiwiZXhwIjoxNjM1ODgxNjEzLCJpYXQiOjE2MzU4NzY2MTN9.nueMnyDHrPjV2upod8JLzhCKAG17i74kRBM4YtJ6-0M";
    LocalDateTime localDateTime = LocalDateTime.now();
    ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
    long currentLong = zdt.toInstant().toEpochMilli();

    LocalDateTime accessTokenExpirationTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(currentLong + 5000000),
                    TimeZone.getDefault().toZoneId());
    LocalDateTime refreshTokenExpirationTime =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(currentLong + 10000000), TimeZone.getDefault().toZoneId());


    LocalDateTime accessTokenAlreadyExpired =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(5000000),
                    TimeZone.getDefault().toZoneId());
    LocalDateTime refreshTokenAlreadyExpired =
            LocalDateTime.ofInstant(Instant.ofEpochMilli(10000000), TimeZone.getDefault().toZoneId());


    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(jwtUtilService, "secret", "secrettest");
        ReflectionTestUtils.setField(jwtUtilService, "accessTokenExpiration", 5000000);
        ReflectionTestUtils.setField(jwtUtilService, "refreshTokenExpiration", 10000000);
    }

    @Test
    public void generateAccessTokenTest() {
        UserDetails userDetails = new org.springframework.security.core.userdetails.User("suresh@gmail.com",
                "133", new ArrayList<>());
        String accessToken = jwtUtilService.generateAccessToken(userDetails);
        System.out.println(accessToken);
        Assertions.assertNotNull(accessToken);

    }



    @Test
    public void extractUsernameTest() {
        JwtToken jwtToken = new JwtToken();
        jwtToken.setUserName("suresh@gmail.com");
        jwtToken.setAccessToken(token);
        jwtToken.setRefreshToken(token);
        jwtToken.setAccessTokenExpiration(accessTokenExpirationTime);
        jwtToken.setId(2l);
        when(jwtTokenRepository.findByUserName(anyString())).thenReturn(jwtToken);
        String userName = jwtUtilService.extractUsername(token, TokenType.ACCESS_TOKEN);
        Assertions.assertEquals(jwtToken.getUserName(), userName);

    }

    @Test
    public void extractUsernamenull() {
        JwtToken jwtToken = null;
        try {
            when(jwtTokenRepository.findByUserName(anyString())).thenReturn(jwtToken);
            String userName = jwtUtilService.extractUsername(token, TokenType.ACCESS_TOKEN);
            Assertions.assertNull(userName);
//            Assertions.assertThrows(InvalidInputException.class, () -> {
//            });
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        Assertions.assertEquals(jwtToken.getUserName(), userName);
    }


    @Test
    public void isValidTokeTestTrue() {
        try{
        JwtToken jwtToken = new JwtToken();
        jwtToken.setUserName("suresh@gmail.com");
        jwtToken.setAccessToken(token);
        jwtToken.setRefreshToken(token);
        jwtToken.setAccessTokenExpiration(accessTokenExpirationTime);
        jwtToken.setRefreshTokenExpiration(refreshTokenExpirationTime);
        jwtToken.setId(2l);
        when(jwtTokenRepository.findByUserName(anyString())).thenReturn(jwtToken);
        String isValid = jwtUtilService.extractUsername(token, TokenType.ACCESS_TOKEN);
        } catch (Exception e) {
            InvalidInputException invalidInputException = (InvalidInputException) e;
            List<String> errorList = invalidInputException.getErrorList();
            System.out.println();
            Assertions.assertEquals(true, (errorList.size() > 0));
        }
    }


    @Test
    public void isValidTokeTestFalse() {
        try {
            JwtToken jwtToken = new JwtToken();
            jwtToken.setUserName("suresh@gmail.com");
            jwtToken.setAccessToken(token);
            jwtToken.setRefreshToken(token);
            jwtToken.setAccessTokenExpiration(accessTokenAlreadyExpired);
            jwtToken.setRefreshTokenExpiration(refreshTokenAlreadyExpired);
            jwtToken.setId(2l);
            when(jwtTokenRepository.findByUserName(anyString())).thenReturn(jwtToken);
            boolean isValid = jwtUtilService.isValidToken(token, TokenType.ACCESS_TOKEN);
            Assertions.assertThrows(InvalidInputException.class, () -> {
            });
        } catch (Exception e) {
            InvalidInputException invalidInputException = (InvalidInputException) e;
            List<String> errorList = invalidInputException.getErrorList();
            System.out.println();
            Assertions.assertEquals(true, (errorList.size() > 0));
        }
    }

    @Test
    public void isTokenExpiredTrue() {
        JwtToken jwtToken = new JwtToken();
        jwtToken.setUserName("suresh@gmail.com");
        jwtToken.setAccessToken(token);
        jwtToken.setRefreshToken(token);
        jwtToken.setAccessTokenExpiration(accessTokenAlreadyExpired);
        jwtToken.setRefreshTokenExpiration(refreshTokenAlreadyExpired);
        jwtToken.setId(2l);
        when(jwtTokenRepository.findByUserName(anyString())).thenReturn(jwtToken);
        boolean isExpired  = jwtUtilService.isAccessTokenExpired(token);
        Assertions.assertEquals(true,isExpired);
        System.out.println("Is Token Expired  " + isExpired);

    }


}
