package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.Container;

/**
 * Определение опасный или не опасный груз
 */
@Service
public class DetectDangerService {

    public Container detectCargo(Container container) {
        if (container.getCargo().getType().startsWith("Batt") || container.getCargo().getType().startsWith("Fuel")) {
            container.setDangerous(true);
        } else {
            container.setDangerous(false);
        }
        return container;
    }
}
