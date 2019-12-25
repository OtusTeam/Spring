package ru.otus.ioservice.example.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.ioservice.example.poll.PollService;

@ConditionalOnProperty(name = "use.swing", havingValue = "false")
@Configuration
public class UIConfig {

    @Bean
    public CommandLineRunner starter (PollService pollService) {
        return args -> pollService.poll();
    }

}
