package com.itgarden.filters;

import com.itgarden.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class BasicRequestFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        String authorization = httpServletRequest.getHeader("Authorization");
//        suresh:123
//        "basic fsfsfsafsdfsdfsddsf";
        try {
            authorization = authorization.substring(6);
            byte[] authByteValue = authorization.getBytes(StandardCharsets.UTF_8);

            String basicAuthString = new String(Base64Utils.decode(authByteValue)); // suresh:123
//            String userName = basicAuthString.split(":")[0];
//            String password = basicAuthString.split(":")[1];
            String array[] = basicAuthString.split(":"); //[0] = suresh , [1] = 123
            String userName = array[0];
            String password = array[1];
            System.out.println(basicAuthString);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            System.out.println("User name password is successfully validated");
            UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            System.out.println("Invalid credentials " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Authorization error " + e.getMessage());
        }
        // through this metod we are saying execute next available filter or controller method that
        // requested by client.
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
