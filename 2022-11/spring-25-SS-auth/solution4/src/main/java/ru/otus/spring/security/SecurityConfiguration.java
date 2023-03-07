package ru.otus.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration  {

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests( ( authorize ) -> authorize
                        .antMatchers( "/" ).permitAll()
                        .antMatchers( "/public" ).permitAll()
                        .antMatchers( "/authenticated", "/success" ).authenticated()
                        .anyRequest().permitAll()
                )
                .anonymous().principal( new AnonimusUD() )
                .and()
                .rememberMe().key("AnySecret").tokenValiditySeconds(60 * 30)
                .and()
                .formLogin();
        return http.build();
    }

    // Больше не работает
//    @Override
//    public void configure( WebSecurity web ) {
//        web.ignoring().antMatchers( "/" );
//    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth.inMemoryAuthentication()
                .withUser( "admin" ).password( "password" ).roles( "ADMIN" );
    }
}
