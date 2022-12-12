package ru.otus.example.beansscopesdemo.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service("SingletonGreetingService")
public class SingletonGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
