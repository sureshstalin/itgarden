package com.user.service;


import com.user.common.Utils;
import com.user.entity.JwtToken;
import com.user.entity.User;
import com.user.model.AuthenticationResponseInfo;
import com.user.repository.JwtTokenRepository;
import com.user.repository.UserRepository;
import com.user.staticdata.TokenType;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    Utils utils;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        String password = user.getUserPasswords().get(0).getPassword();
//        user.setPassword(password);
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
                password, new ArrayList<>());
        return userDetails;
    }

    public AuthenticationResponseInfo generateAuthResponse(UserDetails userDetails, TokenType tokenType) {

        JwtToken jwtToken = jwtTokenRepository.findByUserName(userDetails.getUsername());
        AuthenticationResponseInfo authenticationResponseInfo = null;
        if (tokenType.equals(TokenType.ACCESS_TOKEN)) {
            String accessToken = jwtUtilService.generateAccessToken(userDetails);
            final Claims accessclaims = jwtUtilService.extractAllClaims(accessToken);
            authenticationResponseInfo = new AuthenticationResponseInfo();
            authenticationResponseInfo.setAccessToken(accessToken);
            authenticationResponseInfo.setUserName(userDetails.getUsername());
            authenticationResponseInfo.setAccessTokenExpiration(utils.convertToLocalDateTime(accessclaims.getExpiration().getTime()));
//            authenticationResponseInfo.setRefreshTokenExpiration(utils.convertToLocalDateTime(refreshClaims.getExpiration().getTime()));
            if (jwtToken != null) {
                authenticationResponseInfo.setId(jwtToken.getId());
            }
        }
        return authenticationResponseInfo;
    }

    public JwtToken saveJwt(AuthenticationResponseInfo authenticationResponseInfo) {
        JwtToken jwtToken = new JwtToken();
        jwtToken.setAccessToken(authenticationResponseInfo.getAccessToken());
        jwtToken.setAccessTokenExpiration(authenticationResponseInfo.getAccessTokenExpiration());
        jwtToken.setId(authenticationResponseInfo.getId());

        jwtToken.setUserName(authenticationResponseInfo.getUserName());
        JwtToken jwtTokenResponse = jwtTokenRepository.save(jwtToken);
        return jwtTokenResponse;
    }
}
