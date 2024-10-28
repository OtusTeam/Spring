package ru.otus.ioservice.example.swing;

import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@ConditionalOnBean(SwingIOService.class)
@Service
public class MessageSystem {
    private final LinkedBlockingQueue<String> inputQueue;
    private final LinkedBlockingQueue<String> outputQueue;

    public MessageSystem() {
        inputQueue = new LinkedBlockingQueue<>();
        outputQueue = new LinkedBlockingQueue<>();
    }

    @SneakyThrows
    public void putToInputQueue(String message) {
        inputQueue.put(message);
    }

    @SneakyThrows
    public void putToOutputQueue(String message) {
        outputQueue.put(message);
    }

    @SneakyThrows
    public String takeFromInputQueue() {
        return inputQueue.take();
    }

    @SneakyThrows
    public String takeFromOutputQueue() {
        return outputQueue.take();
    }
}
