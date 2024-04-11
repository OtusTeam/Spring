package ru.otus.spring.logging;

import org.aspectj.lang.JoinPoint;

public class LoggingAspect {

	public void logBefore(JoinPoint joinPoint) {
		System.out.println("Прокси : " + joinPoint.getThis().getClass().getName());
		System.out.println("Класс : " + joinPoint.getTarget().getClass().getName());

		System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
	}
}
