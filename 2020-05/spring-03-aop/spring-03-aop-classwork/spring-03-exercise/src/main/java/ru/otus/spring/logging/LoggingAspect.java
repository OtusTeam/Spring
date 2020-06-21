package ru.otus.spring.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public ru.otus.spring.domain.Person ru.otus.spring.dao.PersonDaoSimple.findByName(String))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Прокси : " + joinPoint.getThis().getClass().getName());
        System.out.println("Класс : " + joinPoint.getTarget().getClass().getName());

        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
    }

    @Around("execution(* ru.otus.spring..*(..))")
    public Object logAround(ProceedingJoinPoint point) {
        if (point != null) {
            System.out.println("Class: " + point.getTarget().toString());
            System.out.println("Method: " + point.getSignature().getName());
            System.out.println("Args: " + Arrays.toString(point.getArgs()));
            try {
                Object result = point.proceed();
                System.out.println("Result: " + result != null ? result.toString() : "null");
                return result;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                return null;
            }
        } else return null;
    }
}
