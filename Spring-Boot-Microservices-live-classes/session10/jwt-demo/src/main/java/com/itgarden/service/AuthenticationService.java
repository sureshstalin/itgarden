package com.itgarden.service;

import com.itgarden.config.JwtUtils;
import com.itgarden.entity.AuthenticationResponseInfo;
import com.itgarden.entity.GrandAuthorityRoleInfo;
import com.itgarden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
 * Created by Suresh Stalin on 06 / Nov / 2020.
 */

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        com.itgarden.entity.User user = userRepository.findUserByEmailId(emailId);
        UserDetails userDetails = new User(user.getEmailId(), user.getPassword(),new ArrayList<>());
        return userDetails;
    }


    public AuthenticationResponseInfo generateAuthResponse(UserDetails userDetails) {
        String accessToken = jwtUtils.generateAccessToken(userDetails);
        String refreshToken = jwtUtils.generateRefreshToken(userDetails);
        AuthenticationResponseInfo authenticationResponseInfo = new AuthenticationResponseInfo();
        authenticationResponseInfo.setAccessToken(accessToken);
        authenticationResponseInfo.setRefreshToken(refreshToken);
        Date expirationDate = jwtUtils.extractExpiration(accessToken);
        LocalDateTime expirationLocalDate = Instant.ofEpochMilli(expirationDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        authenticationResponseInfo.setAccessTokenExpiration(expirationLocalDate);
        authenticationResponseInfo.setRefreshTokenExpiration(
                Instant.ofEpochMilli(jwtUtils.extractIssuedAt(refreshToken).getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
        return authenticationResponseInfo;
    }
}
