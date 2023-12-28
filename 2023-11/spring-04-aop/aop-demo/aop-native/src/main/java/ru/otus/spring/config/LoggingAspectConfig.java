package ru.otus.spring.config;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.otus.spring.dao.PersonDaoSimple;

@Configuration
public class LoggingAspectConfig {

	public Pointcut personDaoSimpleLoggingAspectPointcut() {
		return new Pointcut() {
			@Override
			public ClassFilter getClassFilter() {
				return clazz -> clazz.equals(PersonDaoSimple.class);
			}

			@Override
			public MethodMatcher getMethodMatcher() {
				return MethodMatcher.TRUE;
			}
		};
	}

	MethodBeforeAdvice personDaoSimpleLoggingAspectBeforeAdvice() {
		return (method, objects, o) -> System.out.println("Ура! Вызов метода : " + method.getName());
	}


	@Bean
	public DefaultPointcutAdvisor personDaoSimpleLoggingAspectAdvisor() {
		return new DefaultPointcutAdvisor(personDaoSimpleLoggingAspectPointcut(),
				personDaoSimpleLoggingAspectBeforeAdvice());
	}
}
