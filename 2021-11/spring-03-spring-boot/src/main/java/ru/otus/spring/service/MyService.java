package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.HelloConfig;


@Service
public class MyService {

    private final String helloString;
    private final int count;
    private final HelloConfig.PrefixesAndSuffixes other;

    public MyService(HelloConfig config) {
        this.helloString = config.getStringToPrint();
        this.count = config.getCount();
        this.other = config.getOther();
    }

    public String sayHello() {
        return other.getPrefix() + helloString.repeat(count)
                + other.getSuffix();
    }
}
