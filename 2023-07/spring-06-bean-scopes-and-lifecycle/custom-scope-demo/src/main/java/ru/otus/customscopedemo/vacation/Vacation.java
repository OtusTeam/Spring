package ru.otus.customscopedemo.vacation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

//import javax.annotation.PreDestroy;
import jakarta.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.Random;

@Scope(value = "vacation", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Vacation {

    private static final Logger logger = LoggerFactory.getLogger(Vacation.class);

    private static final String[] actions = {"спит", "ест", "пьет"};
    private static final int VACATION_DURATION_SECONDS = 4;

    public void enjoy(){
        logger.info("Отпуск начат");
        var random = new Random();
        var startTime = LocalDateTime.now().plusSeconds(VACATION_DURATION_SECONDS);
        while (LocalDateTime.now().isBefore(startTime)) {
            var actionIndex = random.nextInt(3);
            logger.info("Отдыхающий - {}", actions[actionIndex]);
            sleep();
        }
        logger.info("Отпуск закончен (гаснет свет)");
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void destroyMethod(){
        logger.info("Бин отпуска уничтожен");
    }

}
