package ru.otus.ioservice.example.swing;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.ioservice.example.api.IOService;

@ConditionalOnProperty(name = "use.swing", havingValue = "true")
@RequiredArgsConstructor
@Service
public class SwingIOService implements IOService {
    private final MessageSystem ms;

    @SneakyThrows
    @Override
    public void out(String message) {
        ms.putToUiQueue(message);
    }

    @SneakyThrows
    @Override
    public String readString() {
        return ms.takeFromPollQueue();
    }
}
