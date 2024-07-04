package ru.otus.spring.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagesController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/public")
    public String publicPage(@RequestParam(name = "SpecialValue") String specialValue) {
        System.out.println(specialValue);
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();
//        System.out.println(authentication.getPrincipal());
        return "public";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return "authenticated";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }


    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }
}
