package ru.otus.spring.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagesController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/public")
    public String publicPage(/*@RequestParam(name = "SpecialValue") String specialValue*/) {
//        System.out.println(specialValue);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        System.out.println(authentication.getPrincipal());
        return "public";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage(Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user = (User) securityContext.getAuthentication().getPrincipal();
        model.addAttribute("userName", user.getUsername());
        return "authenticated";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }

    @GetMapping("/error")
    public String errorPage(Model model) {
        model.addAttribute("source", "errorPage");
        return "error";
    }

    @PostMapping("/fail")
    public String failPage(Model model) {
        model.addAttribute("source", "failPage");
        return "error";
    }
}
