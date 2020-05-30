package ru.otus.smigunov.project.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.smigunov.project.controller.dto.UserDto;
import ru.otus.smigunov.project.domain.User;
import ru.otus.smigunov.project.domain.UserAuthorities;
import ru.otus.smigunov.project.exceptions.ObjectNotFoundException;
import ru.otus.smigunov.project.repositories.UserRepository;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public List<UserDto> save(UserDto userDto) throws ObjectNotFoundException, UnknownHostException {
        User user;
        if (userDto.getId() != null) {
            user = userRepository.findById(userDto.getId()).orElseThrow(ObjectNotFoundException::new);
        } else {
            user = new User();
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setEnabled(true);
            user.setCredentialsNonExpired(true);
        }

        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        String passOpen = userDto.getPassword();

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(hashedPassword);

        if (user.getAuthorities() != null && !user.getAuthorities().isEmpty()) {
            user.getAuthorities().get(0).setUsername(userDto.getUsername());
            user.getAuthorities().get(0).setAuthority(userDto.getAuthority());
        } else {
            user.setAuthorities(Collections.singletonList(UserAuthorities.builder()
                    .username(userDto.getUsername())
                    .authority(userDto.getAuthority())
                    .build()));
        }
        user.setDescription(userDto.getDescription());
        user.setEmail(userDto.getEmail());
        user.setTel(userDto.getTel());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);

        userRepository.save(user);
        user.setPassword(passOpen);
        emailService.sendEmail(user.getEmail(), user);

        return userRepository.findAll().stream().map(UserDto::toDto).collect(Collectors.toList());
    }
}
