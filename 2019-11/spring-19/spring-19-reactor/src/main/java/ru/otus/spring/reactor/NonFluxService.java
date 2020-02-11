package ru.otus.spring.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NonFluxService {

    private final Logger logger = LoggerFactory.getLogger(NonFluxService.class);

    public Message nonFluxSayHello(Message message) {
        logger.info("Message received in non-flux service: {}", message.getValue());

        final String name = message.getValue();
        final String withHello = "Hello, " + name + "!";
        try {
            Thread.sleep(1000);
            return new Message(withHello);
        } catch (InterruptedException ex) {
            return new Message(withHello);
        }
    }
}
