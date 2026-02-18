package ru.otus.demo.aop;

import ru.otus.demo.Main;

import java.lang.reflect.Proxy;

public class SimpleWeaver {
    @SuppressWarnings("unchecked")
    public <T> T weave(T classInstance, LoggingAspect aspect) {
        return (T) Proxy.newProxyInstance(
                Main.class.getClassLoader(),
                classInstance.getClass().getInterfaces(),
                new SimpleAOPInvocationHandler(classInstance, aspect)
        );
    }
}
