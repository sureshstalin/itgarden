package com.itgarden.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("suresh").password(getPasswordEncoder().encode("123")).roles("SUPERADMIN")
                .and()
                .withUser("mark").password(getPasswordEncoder().encode("123")).roles("ADMIN")
                .and()
                .withUser("joe").password(getPasswordEncoder().encode("123")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/user").hasAnyRole("USER","SUPERADMIN")
                .antMatchers("/api/admin").hasAnyRole("ADMIN","SUPERADMIN")
                .antMatchers("/api/superadmin").hasRole("SUPERADMIN")
                .antMatchers("/").permitAll()
                .and()
                .formLogin();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
