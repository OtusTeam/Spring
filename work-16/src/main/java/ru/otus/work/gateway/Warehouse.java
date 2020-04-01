package ru.otus.work.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.work.domain.Cargo;
import ru.otus.work.domain.Container;

import java.util.Collection;

@MessagingGateway
public interface Warehouse {

    @Gateway(requestChannel = "cargoChannel", replyChannel = "containerChannel")
    Collection<Container> process(Collection<Cargo> cargoItem);
}
