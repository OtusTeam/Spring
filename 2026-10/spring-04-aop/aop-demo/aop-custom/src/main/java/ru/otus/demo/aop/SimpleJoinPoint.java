package ru.otus.demo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.SourceLocation;

public class SimpleJoinPoint implements JoinPoint {
	private final Object targetClass;
	private final Object proxyClass;
	private final Object[] args;
	private final Signature targetMethodSignature;

	public SimpleJoinPoint(Object target, Object proxy, Object[] args,
			Method targetMethod) {
		this.targetClass = target;
		this.proxyClass = proxy;
		this.args = args;
		this.targetMethodSignature = new SimpleSignature(targetMethod);
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
	public Object getThis() {
		return proxyClass;
	}

	@Override
	public Object getTarget() {
		return targetClass;
	}

	@Override
	public Object[] getArgs() {
		return args;
	}

	@Override
	public Signature getSignature() {
		return targetMethodSignature;
	}

	@Override
	public SourceLocation getSourceLocation() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getKind() {
		throw new UnsupportedOperationException();
	}

	@Override
	public StaticPart getStaticPart() {
		throw new UnsupportedOperationException();
	}
}
