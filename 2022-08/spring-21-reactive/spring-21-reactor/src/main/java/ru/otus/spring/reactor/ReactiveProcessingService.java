package ru.otus.spring.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Scheduler;
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
        // Создаём sink
        // Это reactor-овская реализация reactive-stream интерфейса
        // Обрабатывает данные как простой последовательный вызов методов :)
        sink = Sinks.many().unicast().onBackpressureBuffer();
        // Здесь мы настраиваем flow
        flow = sink.asFlux()
                .map(nonFluxService::nonFluxSayHello)
                .publishOn(scheduler)
                .subscribe(this::printMessage);
    }

    /**
     * Этот метод будет инициировать асинхронную обрабтку сообщения
     *
     * @param name это имя будет приходить из не-reactor окружения
     */
    public void printHello(String name) {
        if (sink.tryEmitNext(new Message(name)).isFailure()) {
            logger.error("!!!!!!");
        }
    }

    /**
     * А это терминальный шаг для сообщения
     *
     * @param message а это финальный шаг для сообщения, отсюда можно вернуть рзультат в не-реактив окружение
     */
    private void printMessage(Message message) {
        logger.info("Message received: {}", message.getValue());
    }

    /**
     * Просто пример, как остановить процесс
     */
    @PreDestroy
    public void dispose() {
        this.flow.dispose();
    }
}
