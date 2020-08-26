package ru.otus.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Collection;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers("/")
                .antMatchers( "/static/**" );
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // По умолчанию SecurityContext хранится в сессии. Эта часть вырубает и каждый запросом приходитТ
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                .and()
                .authorizeRequests()
                .antMatchers("/public/").anonymous()
                .and()
                .authorizeRequests()
                .antMatchers("/authenticated").authenticated()
//                .and()
//                .authorizeRequests().antMatchers("/public").authenticated()
                .and()
                .httpBasic()
                .and()
                .anonymous()
                .principal( "anonymous" )
                .and()
                .rememberMe().key( "Some secret" )
        ;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
        return NoOpPasswordEncoder.getInstance();
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return charSequence.toString().equals(s);
//            }
//        };
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("password").roles("ADMIN")
                ;
    }
}
