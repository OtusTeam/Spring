package com.example.homework12_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailSecurityService userService;

    @Override
    public void configure( WebSecurity web ) {
        web.ignoring().antMatchers( "/" );
    }

    @Override
    public void configure( HttpSecurity http ) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers( "/login", "/logout" ).anonymous()
                .and()
                .authorizeRequests().antMatchers( "/words/all", "/words/addWord", "/words/edit", "/words/delete" ).authenticated()
                .and()

                .formLogin()
                .defaultSuccessUrl("/words/all")
        .and()
        .rememberMe().key("security key");

    }

    @Autowired
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
