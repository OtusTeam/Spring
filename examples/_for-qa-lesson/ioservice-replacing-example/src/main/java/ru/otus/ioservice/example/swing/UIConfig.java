package ru.otus.ioservice.example.swing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.ioservice.example.poll.PollService;

import java.awt.*;

@Slf4j
@ConditionalOnProperty(name = "use.console", havingValue = "false")
@Configuration
public class UIConfig {

    @Bean
    public CommandLineRunner starter(PollService pollService, MessageSystem ms) {
        return args -> {
            EventQueue.invokeLater(() -> {
                try {
                    PollMainForm mainForm = new PollMainForm(ms);
                    mainForm.init();
                } catch (Exception e) {
                    log.error("Error during main form initialization", e);
                }
            });
            pollService.poll();
        };
    }

}
