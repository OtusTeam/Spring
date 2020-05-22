package ru.otus.smigunov.project.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import ru.otus.smigunov.project.configuration.MailConfig;
import ru.otus.smigunov.project.domain.User;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class EmailServiceImpl implements EmailService {

    private final LocalizedMessageService localizedMessageService;
    private final MailConfig mailConfig;

    private final JavaMailSenderImpl mailSender;

    public EmailServiceImpl(LocalizedMessageService localizedMessageService, JavaMailSenderImpl mailSender, MailConfig mailConfig) {
        this.localizedMessageService = localizedMessageService;
        this.mailSender = mailSender;
        this.mailConfig = mailConfig;
    }

    public void sendEmail(String email, User user) throws UnknownHostException {
        SimpleMailMessage message = new SimpleMailMessage();

        String localhost = InetAddress.getLocalHost().getHostName();
        String link = String.format("http://%s:%s/checkLists?userid=%s", localhost, mailConfig.getServerPort(), user.getId());

        message.setTo(email);
        message.setFrom(mailConfig.getUsername());
        message.setSubject(localizedMessageService.getMessage("mailTitle", new Object[]{""}));
        message.setText(localizedMessageService.getMessage("mailText", new Object[]{link, user.getUsername(), user.getPassword()}));

        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
