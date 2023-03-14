package ru.otus.spring.sso.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
                .authorizeRequests( a -> a
                        .antMatchers( "/", "/error", "/webjars/**" ).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling( e -> e
                        .authenticationEntryPoint( new HttpStatusEntryPoint( HttpStatus.UNAUTHORIZED ) )
                )
                .csrf().disable()
                .logout( l -> l
                        .logoutSuccessUrl( "/" ).permitAll()
                )

                .oauth2Login();
    }
}
