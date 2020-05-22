package ru.otus.smigunov.project.service;

import ru.otus.smigunov.project.domain.User;

import java.net.UnknownHostException;

public interface EmailService {
    void sendEmail(String email, User user) throws UnknownHostException;
}
