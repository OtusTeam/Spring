package ru.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGateway {
    private static final Logger log = LoggerFactory.getLogger(ApiGateway.class);

    /*
       curl -v http://localhost:7777/client/info?name=testClientName
       curl -v http://localhost:7777/order/info?id=testOrderId

    */

    public static void main(String[] args) {
        SpringApplication.run(ApiGateway.class, args);
        log.info("SrvApiGateway started");
    }
}
