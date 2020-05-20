package ru.otus.smigunov.project.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import ru.otus.smigunov.project.domain.User;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class EmailServiceImpl implements EmailService {

    private final LocalizedMessageService localizedMessageService;

    @Value("${server.port}")
    private String serverPort;

    @Value("${mail.username}")
    private String username;

    private final JavaMailSenderImpl mailSender;

    public EmailServiceImpl(LocalizedMessageService localizedMessageService, JavaMailSenderImpl mailSender) {
        this.localizedMessageService = localizedMessageService;
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, User user) throws UnknownHostException {
        SimpleMailMessage message = new SimpleMailMessage();

        String localhost = InetAddress.getLocalHost().getHostName();
        String link = String.format("http://%s:%s/checkLists?userid=%s", localhost, serverPort, user.getId());

        message.setTo(email);
        message.setFrom(username);
        message.setSubject(localizedMessageService.getMessage("mailTitle", new Object[]{""}));
        message.setText(localizedMessageService.getMessage("mailText", new Object[]{link, user.getUsername(), user.getPassword()}));

        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
