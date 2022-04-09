package com.example.homework13_acl.controller;

import com.example.homework13_acl.dto.NewUserDto;
import com.example.homework13_acl.dto.NewWordDto;
import com.example.homework13_acl.model.User;
import com.example.homework13_acl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {
    final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/users/all")
    public String userList(Model model) {

        List<User> users = repository.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        User user = repository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("user", user);
        return "editUser";
    }

    @GetMapping("/users/addUser")
    public String editNewPage(Model model) {
        NewUserDto userForm = new NewUserDto();
        model.addAttribute("userForm", userForm);
        return "addUser";
    }

    @PostMapping("/users/edit")
    public String saveWord(
            User userForm,
            Model model
    ) {
        User user = repository.findById(userForm.getId()).orElseThrow(NotFoundException::new);
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setRole(userForm.getRole());
        User saved = repository.save(user);
        model.addAttribute(saved);
        return "redirect:/users/all";
    }

    @PostMapping("/users/addUser")
    public String createWord(NewUserDto userForm, Model model) {
        User user = new User();
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setRole(userForm.getRole());

        repository.save(user);
        return "redirect:/users/all";
    }

    @GetMapping("/users/delete")
    public String deleteWord(@RequestParam Long id) {

        repository.deleteById(id);
        return "redirect:/users/all";
    }

}
