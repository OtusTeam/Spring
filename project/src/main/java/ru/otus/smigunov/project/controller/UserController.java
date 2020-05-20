package ru.otus.smigunov.project.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.smigunov.project.controller.dto.UserDto;
import ru.otus.smigunov.project.domain.User;
import ru.otus.smigunov.project.domain.UserAuthorities;
import ru.otus.smigunov.project.enumeration.Roles;
import ru.otus.smigunov.project.exceptions.ObjectNotFoundException;
import ru.otus.smigunov.project.repositories.UserRepository;
import ru.otus.smigunov.project.service.EmailService;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @GetMapping("/")
    public String getAllUser(Model model) {
        model.addAttribute("users", userRepository.findAll().stream().map(UserDto::toDto).collect(Collectors.toList()));
        return "users";
    }

    @GetMapping("/editUser.html")
    public String getEditPage(Model model) {
        model.addAttribute("user", UserDto.builder().build());
        model.addAttribute("authorities", Roles.allRoles());
        return "editUser";
    }

    @GetMapping("/users")
    public String getUserById(
            @RequestParam("id") Long id,
            Model model
    ) throws ObjectNotFoundException {
        User user = userRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        model.addAttribute("user", UserDto.toDto(user));
        model.addAttribute("authorities", Roles.allRoles());
        return "editUser";
    }

    @PostMapping(value = "/userSave",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveUser(
            @ModelAttribute("user") UserDto userDto,
            Model model
    ) throws ObjectNotFoundException, UnknownHostException {
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
        user.setPassword(userDto.getPassword());
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

        userRepository.save(user);
        emailService.sendEmail(user.getEmail(), user);
        model.addAttribute("users", userRepository.findAll().stream().map(UserDto::toDto).collect(Collectors.toList()));
        return "users";
    }

    @GetMapping("/deleteusers")
    public String deleteUser(
            @RequestParam("id") Long id,
            Model model
    ) {
        userRepository.deleteById(id);
        model.addAttribute("users", userRepository.findAll().stream().map(UserDto::toDto).collect(Collectors.toList()));
        return "users";
    }
}
