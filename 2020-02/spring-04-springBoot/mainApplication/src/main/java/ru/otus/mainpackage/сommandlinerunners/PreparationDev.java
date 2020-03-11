package ru.otus.mainpackage.сommandlinerunners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.Messager;

import java.util.Arrays;


@Component
public class PreparationDev implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(PreparationDev.class);

    private final Messager messager;

    public PreparationDev(Messager messager) {
        this.messager = messager;
    }

    @Override
    public void run(String... args) {
        logger.info("DEV mode!!! Что-то настравиваем и подготавливаем, параметры: {} ", Arrays.toString(args));
        logger.info("message from Messager:{}", messager.sayMessage());
        //args парметры, котрые могут быть переданы в Main
    }
}
