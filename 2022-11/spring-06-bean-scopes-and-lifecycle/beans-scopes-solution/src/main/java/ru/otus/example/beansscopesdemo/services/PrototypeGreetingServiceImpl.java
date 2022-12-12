package ru.otus.example.beansscopesdemo.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Scope(scopeName = "prototype")
@Service("PrototypeGreetingService")
public class PrototypeGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
