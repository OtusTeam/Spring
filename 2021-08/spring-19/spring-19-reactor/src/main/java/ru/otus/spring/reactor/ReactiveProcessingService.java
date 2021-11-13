package ru.otus.spring.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Sinks;
import ru.otus.spring.service.NonFluxService;

import javax.annotation.PreDestroy;

@Service
public class ReactiveProcessingService {

    private final Logger logger = LoggerFactory.getLogger(ReactiveProcessingService.class);

    private final Sinks.Many<Message> sink;
    private final Disposable flow;

    public ReactiveProcessingService(NonFluxService nonFluxService) {
        // Создаём sink (ранее - процессор)
        // Это reactor-овская реализация reactive-stream интерфейса
        // Обрабатывает данные как простой последовательный вызов методов :)
        sink = Sinks.many().multicast().directBestEffort();
        // Здесь мы настраиваем flow
        flow = sink.asFlux()
                .map(nonFluxService::nonFluxSayHello)
                .subscribe(this::printMessage);
        // в идеале в коде выше должен быть doOnNext
        // в map не предполагаются задержки
    }

    /**
     * Этот метод будет инициировать асинхронную обрабтку сообщения
     *
     * @param name это имя будет приходить из не-reactor окружения
     */
    public void printHello(String name) {
        sink.tryEmitNext(new Message(name));
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
