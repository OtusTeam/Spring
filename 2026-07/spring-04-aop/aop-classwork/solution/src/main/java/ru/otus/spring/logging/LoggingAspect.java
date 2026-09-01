package ru.otus.spring.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Before("@annotation(ru.otus.spring.logging.LogMe)")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("Прокси : " + joinPoint.getThis().getClass().getName());
		System.out.println("Класс : " + joinPoint.getTarget().getClass().getName());

		System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
	}
}
