package ru.otus.example.beansscopesdemo.services;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service("RequestGreetingService")
public class RequestGreetingServiceImpl extends AbstractGreetingServiceImpl {
}
