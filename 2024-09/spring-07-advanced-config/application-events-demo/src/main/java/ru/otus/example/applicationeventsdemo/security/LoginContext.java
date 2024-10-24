package ru.otus.example.applicationeventsdemo.security;

public interface LoginContext {
    void login(String userName);
    boolean isUserLoggedIn();
}
