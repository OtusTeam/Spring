package ru.otus.example.beansscopesdemo.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service("PrototypeGreetingService")
public class PrototypeGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
