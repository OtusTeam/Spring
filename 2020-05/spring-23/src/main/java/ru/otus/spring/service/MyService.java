package ru.otus.spring.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    @PreAuthorize("hasRole('ROLE_USER')")
    public String onlyUser() {
        return "My love";
    }

    @Secured( "ADMIN" )
    public void onlyAdmin() {}
}
