package ru.otus.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        http
                .csrf( AbstractHttpConfigurer::disable )
                .authorizeHttpRequests( ( authorize ) -> authorize
                        .requestMatchers( "/public", "/" ).permitAll()
                        .requestMatchers( "/authenticated", "/success" ).authenticated()
                        .requestMatchers( "/manager" ).hasAnyRole( "MANAGER" )
                        .requestMatchers( "/user" ).hasAnyRole( "ADMIN" )
                        .anyRequest().permitAll()
                )
                .formLogin( Customizer.withDefaults() )

        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var users = new ArrayList<UserDetails>();
        users.add( User
                .withUsername("admin").password("password").roles("ADMIN")
                .build() );
        users.add( User
                .withUsername("user").password("password").roles("USER")
                .build() );
        return new InMemoryUserDetailsManager(users);

    }
}
