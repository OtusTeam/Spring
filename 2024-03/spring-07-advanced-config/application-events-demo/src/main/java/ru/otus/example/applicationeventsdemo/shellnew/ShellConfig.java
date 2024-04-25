package ru.otus.example.applicationeventsdemo.shellnew;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;
import ru.otus.example.applicationeventsdemo.security.LoginContext;

@Configuration
public class ShellConfig {

    @Bean
    public AvailabilityProvider publishEventCommandAvailabilityProvider(LoginContext loginContext) {
        return () -> loginContext.isUserLoggedIn()
                ? Availability.available()
                : Availability.unavailable("Сначала залогиньтесь");
    }
}
