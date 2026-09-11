package ru.otus.example.applicationeventsdemo.security;

import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class InMemoryLoginContext implements LoginContext {
    private String userName;

    @Override
    public void login(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean isUserLoggedIn() {
        return nonNull(userName);
    }
}
