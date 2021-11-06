package com.user.service;


import com.user.entity.JwtToken;
import com.user.exception.InvalidInputException;
import com.user.repository.JwtTokenRepository;
import com.user.repository.UserRepository;
import com.user.staticdata.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;



@Service
public class JwtUtilService {

    @Value("${jwt.secretkey}")
    private String secret;

    @Value("${jwt.access-token-expiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refresh-token-expiration}")
    private long refreshTokenExpiration;

    @Autowired
    private JwtTokenRepository jwtTokenRepository;

    @Autowired
    private UserRepository userRepository;

    public String extractUsername(String token, TokenType tokenType) {
        String userName = extractClaim(token, Claims::getSubject);
        JwtToken jwtToken = jwtTokenRepository.findByUserName(userName);
        if(jwtToken != null) {
            return jwtToken.getUserName();
        }
        return null;
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    }

    public Boolean isAccessTokenExpired(String token) throws InvalidInputException {
        final String username = extractUsername(token, TokenType.ACCESS_TOKEN);
        JwtToken jwtToken = jwtTokenRepository.findByUserName(username);
        LocalDateTime accessTokenLocalDateTime = jwtToken.getAccessTokenExpiration();
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        return currentLocalDateTime.isAfter(accessTokenLocalDateTime);

    }


    public String generateAccessToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername(), TokenType.ACCESS_TOKEN);
    }


    /*
     * Generates JWT Token with Claims (subject,issued At,Expiration)
     */
    private String createToken(Map<String, Object> claims, String subject, TokenType tokenType) {

            return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration))
                    .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean isValidToken(String token, TokenType tokenType) throws InvalidInputException {
        List<String> errorList = new ArrayList<>();
        if (tokenType.equals(TokenType.ACCESS_TOKEN)) {
            if (isAccessTokenExpired(token)) {
                errorList.add("Invalid Access Token: Access Token is expired");
            }
        }
        if (!errorList.isEmpty()) {
            throw new InvalidInputException(errorList);
        }
        return true;
    }
}
