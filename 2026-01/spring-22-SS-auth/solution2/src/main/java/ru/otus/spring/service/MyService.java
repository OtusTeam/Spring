package ru.otus.spring.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @PreAuthorize("hasRole('ROLE_MANAGER') && {new java.util.Random().nextInt()%2 == 0}")
    public void onlyAdmin() {
    }
}
