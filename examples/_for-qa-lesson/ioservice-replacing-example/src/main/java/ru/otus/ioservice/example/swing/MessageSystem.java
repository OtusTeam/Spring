package ru.otus.ioservice.example.swing;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@ConditionalOnBean(SwingIOService.class)
@Service
public class MessageSystem {
    private final BlockingQueue<String> inputQueue;
    private final BlockingQueue<String> outputQueue;

    public MessageSystem() {
        inputQueue = new LinkedBlockingQueue<>();
        outputQueue = new LinkedBlockingQueue<>();
    }

    public void putToInputQueue(String message) {
        //noinspection ResultOfMethodCallIgnored
        inputQueue.offer(message);
    }

    public void putToOutputQueue(String message) {
        //noinspection ResultOfMethodCallIgnored
        outputQueue.offer(message);
    }

    public String takeFromInputQueue() {
        return takeFromQueue(inputQueue);
    }

    public String takeFromOutputQueue() {
        return takeFromQueue(outputQueue);
    }

    private String takeFromQueue(BlockingQueue<String> queue) {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
