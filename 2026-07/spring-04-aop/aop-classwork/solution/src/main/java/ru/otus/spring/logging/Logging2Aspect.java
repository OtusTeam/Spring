package ru.otus.spring.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(1)
@Component
public class Logging2Aspect {

	@Before("@annotation(ru.otus.spring.logging.LogMe)")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("Прокси2 : " + joinPoint.getThis().getClass().getName());
		System.out.println("Класс2 : " + joinPoint.getTarget().getClass().getName());

		System.out.println("Вызов метода2 : " + joinPoint.getSignature().getName());
	}
}
