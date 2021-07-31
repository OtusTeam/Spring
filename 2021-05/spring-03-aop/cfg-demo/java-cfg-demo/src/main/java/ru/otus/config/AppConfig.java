package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.services.*;

@Configuration
public class AppConfig {


    @Bean
    public EquationPreparer equationPreparer(){
        return new EquationPreparerImpl();
    }


    @Bean
    public PlayerService playerService(IOService ioService) {
        return new PlayerServiceImpl(ioService);
    }

    @Bean
    public GameProcessor gameProcessor(IOService ioService,
                                       PlayerService playerService,
                                       EquationPreparer equationPreparer) {
        return new GameProcessorImpl(ioService, equationPreparer, playerService);
    }

    @Bean
    public IOService ioService() {
        return new IOServiceStreams(System.out, System.in);
    }

}
