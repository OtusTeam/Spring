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
import ru.otus.smigunov.project.enumeration.Roles;
import ru.otus.smigunov.project.exceptions.ObjectNotFoundException;
import ru.otus.smigunov.project.repositories.UserRepository;
import ru.otus.smigunov.project.service.UserService;

import java.net.UnknownHostException;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

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
        model.addAttribute("users", userService.save(userDto));
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
