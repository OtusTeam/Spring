package ru.demo.service;

import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import ru.demo.model.StringValue;

public class DataSenderKafka implements DataSender {
    private static final Logger log = LoggerFactory.getLogger(DataSenderKafka.class);

    private final KafkaTemplate<String, StringValue> template;

    private final Consumer<StringValue> sendAsk;

    private final String topic;

    public DataSenderKafka(
            String topic,
            KafkaTemplate<String, StringValue> template,
            Consumer<StringValue> sendAsk) {
        this.topic = topic;
        this.template = template;
        this.sendAsk = sendAsk;
    }

    @Override
    public void send(StringValue value) {
        try {
            log.info("value:{}", value);
            template.send(topic, value)
                    .whenComplete(
                            (result, ex) -> {
                                if (ex == null) {
                                    log.info(
                                            "message id:{} was sent, offset:{}",
                                            value.id(),
                                            result.getRecordMetadata().offset());
                                    sendAsk.accept(value);
                                } else {
                                    log.error("message id:{} was not sent", value.id(), ex);
                                }
                            });
        } catch (Exception ex) {
            log.error("send error, value:{}", value, ex);
        }
    }
}
