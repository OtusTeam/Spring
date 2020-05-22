package ru.otus.smigunov.project.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.smigunov.project.controller.dto.UserDto;
import ru.otus.smigunov.project.exceptions.ObjectNotFoundException;
import ru.otus.smigunov.project.service.EmailService;
import ru.otus.smigunov.project.service.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

@DataJpaTest
@DisplayName("Тест класса AuthorRepository")
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    EmailService emailService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @SneakyThrows
    @BeforeEach
    public void setUp() {
        doNothing().when(emailService).sendEmail(null, null);
    }

    @Test
    @DisplayName("Сохранение пользователя")
    public void save() throws Exception {
        String username = "username";
        String name = "name";
        String password = "password";
        String authority = "authority";
        String description = "description";
        String email = "email";
        String tel = "tel";

        UserDto userDto = UserDto.builder()
                .username(username)
                .name(name)
                .password(password)
                .authority(authority)
                .description(description)
                .email(email)
                .tel(tel)
                .build();

        List<UserDto> userDtos = userService.save(userDto);

        assertThat(userDtos).isNotEmpty();
        UserDto userDtoToCompare = userDtos.stream().filter(a -> a.getUsername().equals(username)).findFirst().orElseThrow(ObjectNotFoundException::new);
        assertThat(bCryptPasswordEncoder.matches(password, userDtoToCompare.getPassword())).isTrue();
        assertThat(userDtoToCompare.getUsername()).isEqualTo(username);
        assertThat(userDtoToCompare.getName()).isEqualTo(name);
        assertThat(userDtoToCompare.getAuthority()).isEqualTo(authority);
        assertThat(userDtoToCompare.getDescription()).isEqualTo(description);
        assertThat(userDtoToCompare.getEmail()).isEqualTo(email);
        assertThat(userDtoToCompare.getTel()).isEqualTo(tel);
    }
}
