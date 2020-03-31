package ru.otus.work.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.work.service.UserDetailsServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Тест класса UserDetailsServiceImpl")
@ActiveProfiles("test")
public class UserDetailsServiceTest {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private String USER_NAME = "admin";

    @Test
    @Transactional
    public void findUserTest() {
        UserDetails userDetails = userDetailsService.loadUserByUsername(USER_NAME);
        assertThat(userDetails.getUsername()).isEqualTo(USER_NAME);
        assertThat(userDetails.getAuthorities()).hasSizeGreaterThan(0);
    }
}
