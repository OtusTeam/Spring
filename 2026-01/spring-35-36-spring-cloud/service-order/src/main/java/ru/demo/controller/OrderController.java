package ru.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final InstanceId instanceId;

    public OrderController(InstanceId instanceId) {
        this.instanceId = instanceId;
    }

    // curl -v http://localhost:8082/info?id="idClient"

    // curl -v http://localhost:8091/info?id="idClient"
    // curl -v http://localhost:8092/info?id="idClient"

    @GetMapping(value = "/info")
    public String info(@RequestParam(name = "id") String id) {
        logger.info("instanceId:{}, request. id:{}", instanceId.name(), id);
        return String.format("%s, Order id:%s", instanceId.name(), id);
    }
}
