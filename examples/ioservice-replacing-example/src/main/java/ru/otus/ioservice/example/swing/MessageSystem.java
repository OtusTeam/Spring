package ru.otus.ioservice.example.swing;

import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;

@Service
public class MessageSystem {
    private final LinkedBlockingQueue<String> pollQueue;
    private final LinkedBlockingQueue<String> uiQueue;

    public MessageSystem() {
        pollQueue = new LinkedBlockingQueue<>();
        uiQueue = new LinkedBlockingQueue<>();
    }

    @SneakyThrows
    public void putToPollQueue(String message) {
        pollQueue.put(message);
    }

    @SneakyThrows
    public void putToUiQueue(String message) {
        uiQueue.put(message);
    }

    @SneakyThrows
    public String takeFromPollQueue() {
        return pollQueue.take();
    }

    @SneakyThrows
    public String takeFromUiQueue() {
        return uiQueue.take();
    }
}
