package ru.otus.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.reactor.Message;

@Service
public class NonFluxService {

    private final Logger logger = LoggerFactory.getLogger(NonFluxService.class);

    public Message nonFluxSayHello(Message message) {
        logger.info("Message received in non-flux service: {}", message.value());

        var name = message.value();
        var withHello = "Hello, " + name + "!";
        try {
            Thread.sleep(1000);
            return new Message(withHello);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return new Message(withHello);
        }
    }
}
