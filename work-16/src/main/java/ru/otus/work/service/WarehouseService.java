package ru.otus.work.service;

import org.springframework.stereotype.Service;
import ru.otus.work.domain.Cargo;
import ru.otus.work.domain.Container;

/**
 * Склад
 */
@Service
public class WarehouseService {

    public Container prepareContainer(Cargo cargo) {
        Container container = new Container();
        container.setCargo(cargo);
        return container;
    }
}
