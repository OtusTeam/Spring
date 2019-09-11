package ru.otus.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.demo.model.User;
import ru.otus.demo.repository.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsersController {

    private UsersRepository repository;

    public UsersController(UsersRepository repository) {
        this.repository = repository;
        User user = new User();
        user.setName("Ivan");
        repository.save(user);
    }

    @GetMapping("/users")
    public List<UserDto> allUsers() {
        return repository.findAll().stream()
                .map(user -> new UserDto() {{
                    setId("" + user.getId());
                    setName(user.getName());
                }})
                .collect(Collectors.toList());
    }
}
