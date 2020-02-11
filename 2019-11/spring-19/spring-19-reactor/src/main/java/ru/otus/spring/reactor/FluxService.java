package ru.otus.spring.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Mono;

@Service
public class FluxService {

    private final Logger logger = LoggerFactory.getLogger(FluxService.class);

    private final NonFluxService nonFluxService;
    private final DirectProcessor<Message> processor;
    private final Disposable flow;

    @Autowired
    public FluxService(NonFluxService nonFluxService) {
        this.nonFluxService = nonFluxService;
        // Создаём процессор - это reactor-овская реализация reactive-stream интерфейса
        // Direct processor, кстати - это простой последовательный вызов методов)
        processor = DirectProcessor.create();
        // Здесь мы настриваем flow
        flow = Mono.from(processor)
                .map(nonFluxService::nonFluxSayHello)
                .subscribe(this::printMessage);
    }

    /**
     * Этот метод будет инициировать асинзронную обрабтку сообщения
     *
     * @param name это имя будет приходить из не-reactor окружения
     */
    public void printHello(String name) {
        processor.onNext(new Message(name));
    }

    /**
     * А это терминальный шаг для сообщения
     *
     * @param message а это финальный шаг для сообщения, отсюда можно вернуть рзультат в не-реактив окружение
     */
    private void printMessage(Message message) {
        logger.info("Message received: {}", message.getValue());
    }
}
