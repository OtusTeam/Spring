package ru.otus.spring.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.service.MyService;

@Controller
public class PagesController {

    private final MyService myService;

    public PagesController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/public")
    public String publicPage( ) {
        return "public";
    }

    @GetMapping("/user")
    public String userPage() {
//        myService.onlyUser();
        return "user";
    }

    @GetMapping("/admin")
//    @Secured( "ADMIN" )
    public String adminPage(  ) {
        myService.onlyUser();
        //myService.onlyAdmin();
        return "admin";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        return "authenticated";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }
}
