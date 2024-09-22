package ru.otus.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests( ( authorize ) -> authorize
                        .antMatchers( "/**", "/" ).permitAll()
                )
                .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var users = new ArrayList<UserDetails>();
        users.add( User
                .withDefaultPasswordEncoder().username("admin").password("password").roles("USER")
                .build() );
        users.add( User
                .withDefaultPasswordEncoder().username("user").password("password").roles("USER")
                .build() );
        users.add( User
                .withDefaultPasswordEncoder().username("someone").password("password").roles("SOMEONE")
                .build() );
        return new InMemoryUserDetailsManager( users );

    }
}
