package ru.appender.kafka;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.encoder.Encoder;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class LogAppender extends UnsynchronizedAppenderBase<LoggingEvent> {
    private static final String MESSAGE_TEMPLATE = "[Kafka appender] %s";

    private String bootstrapServers;
    private String topicName;

    private final Queue<LoggingEvent> eventsQueue = new ArrayBlockingQueue<>(1000);

    private Thread senderThread;
    private Encoder<LoggingEvent> encoder;

    private final ErrorMsgConsumer errorMsgConsumer = error -> addError(String.format(MESSAGE_TEMPLATE, error));

    public void setEncoder(Encoder<LoggingEvent> encoder) {
        this.encoder = encoder;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
        addInfo(String.format(MESSAGE_TEMPLATE, "set bootstrapServers:" + bootstrapServers));
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
        addInfo(String.format(MESSAGE_TEMPLATE, "set topicName:" + topicName));
    }

    @Override
    public void start() {
        if (bootstrapServers == null) {
            addError(String.format(MESSAGE_TEMPLATE, "bootstrapServers is null"));
            return;
        }

        if (topicName == null) {
            addError(String.format(MESSAGE_TEMPLATE, "topicName is null"));
            return;
        }
        var logProducer = new LogProducer(bootstrapServers, topicName);

        senderThread = Thread.ofVirtual().name("senderThread").start(() -> sendMessages(logProducer));
        super.start();
        addInfo(String.format(MESSAGE_TEMPLATE, "started"));
    }

    @Override
    public void stop() {
        super.stop();
        if (senderThread != null) {
            senderThread.interrupt();
        }
        addInfo(String.format(MESSAGE_TEMPLATE, "stopped"));
    }

    @Override
    public void append(LoggingEvent eventObject) {
        var result = eventsQueue.offer(eventObject);
        if (!result) {
            addWarn(String.format(MESSAGE_TEMPLATE, "eventsQueue is full"));
        }
    }

    private void sendMessages(LogProducer logProducer) {
        while (!Thread.currentThread().isInterrupted()) {
            var event = eventsQueue.poll();
            if (event != null) {
                try {
                    var messageAsText = new String(encoder.encode(event));
                    logProducer.send(messageAsText, errorMsgConsumer);
                } catch (Exception ex) {
                    addError(String.format(MESSAGE_TEMPLATE, ex.getMessage()));
                }
            }
        }
    }
}
