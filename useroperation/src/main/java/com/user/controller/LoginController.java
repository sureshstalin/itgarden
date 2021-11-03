package com.user.controller;


import com.user.entity.JwtToken;
import com.user.exception.InvalidInputException;
import com.user.exception.InvalidUserNamePasswordException;
import com.user.model.AuthenticationRequestInfo;
import com.user.model.AuthenticationResponseInfo;
import com.user.repository.JwtTokenRepository;
import com.user.service.JwtUtilService;
import com.user.service.LoginUserDetailsService;
import com.user.staticdata.TokenType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginUserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @PostMapping
    public ResponseEntity<?> authenticate(@Valid @RequestBody AuthenticationRequestInfo authenticationRequest) {
        log.info("AUTHENTICATING USER NAME AND PASSWORD");
        try {
            // This authenticate method call AuthenticationService.loadUserByUsername
            String userName = authenticationRequest.getUserName();
            String password = authenticationRequest.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password));

        } catch (BadCredentialsException bce) {
            bce.printStackTrace();
            log.debug("#### INVALID USER NAME AND PASSWORD " + bce.getMessage());
            throw new InvalidUserNamePasswordException(bce.getMessage());
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        AuthenticationResponseInfo authenticationResponseInfo = userDetailsService.generateAuthResponse(userDetails, TokenType.ACCESS_TOKEN);
        JwtToken jwtTokenResponse = userDetailsService.saveJwt(authenticationResponseInfo);
        if (jwtTokenResponse != null) {
            return new ResponseEntity<JwtToken>(jwtTokenResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Authentication Failure", HttpStatus.BAD_REQUEST);
        }
    }
}
