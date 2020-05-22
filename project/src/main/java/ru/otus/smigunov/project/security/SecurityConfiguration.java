package ru.otus.smigunov.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.otus.smigunov.project.enumeration.Roles;
import ru.otus.smigunov.project.repositories.UserRepository;
import ru.otus.smigunov.project.service.UserDetailsServiceImpl;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    @Value("${application.security:true}")
    private Boolean security;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (security) {
            security = userRepository.findFirstByOrderByUsernameAsc() != null;
        }

        if (security) {
            http.authorizeRequests()
                    .and()
                    .authorizeRequests().antMatchers("/editUser", "/users", "/").hasAuthority(Roles.ROLE_DOC.name())
                    .and()
                    .authorizeRequests().antMatchers("/editCheckList", "/checkLists").hasAnyAuthority(Roles.ROLE_PATIENT.name())
                    .and()
                    .formLogin()
            ;
        }
    }

    /*
     * Енкодер
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
