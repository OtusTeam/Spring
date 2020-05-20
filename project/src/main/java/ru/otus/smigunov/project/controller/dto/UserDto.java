package ru.otus.smigunov.project.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import ru.otus.smigunov.project.domain.User;
import ru.otus.smigunov.project.domain.UserAuthorities;

@AllArgsConstructor
@Getter
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String name;
    private String password;
    private String authority;
    private String description;
    private String email;
    private String tel;

    public static UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getUsername(),
                user.getName(),
                user.getPassword(),
                user.getAuthorities().stream().map(UserAuthorities::getAuthority).reduce("", String::concat),
                user.getDescription(),
                user.getEmail(),
                user.getTel()
        );
    }
}
