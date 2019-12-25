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

//    @Before("execution(* ru.otus.spring.dao.PersonDaoSimple.*(..))")
//    public void logBefore(JoinPoint joinPoint) {
//        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
//    }

    @Around("execution(* ru.otus.spring.dao.PersonDaoSimple.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Вызов метода: " + joinPoint.getSignature().getName());
        System.out.println("Параметры: " + Arrays.toString(joinPoint.getArgs()));
        return joinPoint.proceed();
    }

}
