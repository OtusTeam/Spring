package ru.otus.ioservice.example.swing;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.ioservice.example.api.IOService;

@ConditionalOnProperty(name = "use.console", havingValue = "false")
@RequiredArgsConstructor
@Service
public class SwingIOService implements IOService {
    private final MessageSystem ms;

    @Override
    public void out(String message) {
        ms.putToOutputQueue(message);
    }

    @Override
    public String readString() {
        return ms.takeFromInputQueue();
    }
}
