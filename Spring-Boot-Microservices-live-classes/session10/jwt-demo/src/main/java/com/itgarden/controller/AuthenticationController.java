package com.itgarden.controller;

import com.itgarden.entity.AuthenticationRequestInfo;
import com.itgarden.entity.AuthenticationResponseInfo;
import com.itgarden.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authenticate")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestInfo authenticationRequest) {
        try {
            // This authenticate method call AuthenticationService.loadUserByUsername
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
                            authenticationRequest.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.getUserName());

        AuthenticationResponseInfo authenticationResponseInfo = authenticationService.generateAuthResponse(userDetails);
        return new ResponseEntity<AuthenticationResponseInfo>(authenticationResponseInfo, HttpStatus.OK);
    }

    // refreshToken -- this generate the new Access token by providing refresh Token
    // Use AuthenticationRequestInfo as requestBody
    // use JwtUtils  public String extractUsername(String token) - use this method to get the User Name
    // use this userName in
//    UserDetails userDetails = authenticationService.loadUserByUsername(authenticationRequest.getUserName());
    // This will give you all the details
    // authenticationManager.authenticate( - Once through this method authenticated
    // call here you new method which generating new Access token
}
