package ru.otus.demo.aop;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;

public class LoggingAspect {

	public void logBefore(JoinPoint joinPoint) {
		System.out.println("Прокси : " + joinPoint.getThis().getClass().getName());
		System.out.println("Класс : " + joinPoint.getTarget().getClass().getName());

		System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
		System.out.println("Аргументы метода : " + Arrays.stream(joinPoint.getArgs())
				.map(Objects::toString)
				.collect(Collectors.joining(", ")));
	}
}
