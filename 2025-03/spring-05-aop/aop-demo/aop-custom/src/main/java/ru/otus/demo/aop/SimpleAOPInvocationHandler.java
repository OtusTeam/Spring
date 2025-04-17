package ru.otus.demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;

public class SimpleAOPInvocationHandler implements InvocationHandler {
	private final Object target;
	private final LoggingAspect aspect;

	public SimpleAOPInvocationHandler(Object target, LoggingAspect aspect) {
		this.target = target;
		this.aspect = aspect;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		JoinPoint joinPoint = new SimpleJoinPoint(target,
				proxy, args, method);
		aspect.logBefore(joinPoint);
		return method.invoke(target, args);
	}
}
