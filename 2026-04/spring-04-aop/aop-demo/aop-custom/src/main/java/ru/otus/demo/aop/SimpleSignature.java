package ru.otus.demo.aop;

import org.aspectj.lang.Signature;

import java.lang.reflect.Method;

public class SimpleSignature implements Signature {
    private final Method targetMethod;

    public SimpleSignature(Method targetMethod) {
        this.targetMethod = targetMethod;
    }

    @Override
    public String toShortString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toLongString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getName() {
        return targetMethod.getName();
    }

    @Override
    public int getModifiers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class<?> getDeclaringType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getDeclaringTypeName() {
        throw new UnsupportedOperationException();
    }
}