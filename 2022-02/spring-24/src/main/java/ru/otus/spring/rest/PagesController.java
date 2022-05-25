package ru.otus.spring.rest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/public")
    public String publicPage() {
        return "public";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage() {
        return "authenticated";
    }

    @GetMapping("/success")
    public String successPage(){
        return "success";
    }
}
