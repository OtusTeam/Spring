package ru.otus.example.beansscopesdemo.services;

import org.springframework.stereotype.Service;

@Service("singletonGreetingService")
public class SingletonGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
