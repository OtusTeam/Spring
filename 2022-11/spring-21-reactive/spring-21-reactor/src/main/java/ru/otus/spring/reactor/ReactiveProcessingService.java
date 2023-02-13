package ru.otus.spring.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;
import ru.otus.spring.service.NonFluxService;

import javax.annotation.PreDestroy;

@Service
public class ReactiveProcessingService {

    private final Logger logger = LoggerFactory.getLogger(ReactiveProcessingService.class);

    private final Sinks.Many<Message> sink;
    private final Disposable flow;

    public ReactiveProcessingService(NonFluxService nonFluxService) {
        var scheduler = Schedulers.newParallel("processing", 2);
        sink = Sinks.many().unicast().onBackpressureBuffer();
        flow = sink.asFlux()
                .publishOn(scheduler)
                .map(nonFluxService::nonFluxSayHello)
                .doOnError(error -> logger.error("error", error))
                .subscribe(this::printMessage);
    }

    /**
     * Этот метод будет инициировать асинхронную обрабтку сообщения
     *
     * @param name это имя будет приходить из не-reactor окружения
     */
    public void printHello(String name) {
        var emitResult = sink.tryEmitNext(new Message(name));
        logger.info("emitResult:{}", emitResult);
    }


    private void printMessage(Message message) {
        logger.info("Message received: {}", message.getValue());
    }

    @PreDestroy
    public void dispose() {
        this.flow.dispose();
    }
}
