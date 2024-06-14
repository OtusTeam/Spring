package ru.otus.demo;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.cglib.proxy.Enhancer;

import ru.otus.demo.dao.PersonDao;
import ru.otus.demo.dao.PersonDaoSimple;
import ru.otus.demo.service.PersonService;
import ru.otus.demo.service.PersonServiceImpl;

public class MainSimple {

	public static void main(String[] args) {

		LoggingAspectSimple aspect = new LoggingAspectSimple();

		PersonDao personDaoSimple = new PersonDaoSimple();

		// JdkProxy
		System.out.println("JdkProxy");
		PersonDao personDaoJdkProxy = (PersonDao) Proxy.newProxyInstance(
				MainSimple.class.getClassLoader(),
				personDaoSimple.getClass().getInterfaces(),
				new java.lang.reflect.InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] methodArgs) throws Throwable {
						aspect.logBefore(personDaoSimple.getClass(), proxy.getClass(), method, methodArgs);
						return method.invoke(personDaoSimple, methodArgs);
					}
				}
		);
		PersonService personService = new PersonServiceImpl(personDaoJdkProxy);
		personService.getByName("Вася");


		// CglibProxy (Spring edition, работает в Java 17 без плясок)
		System.out.println("\n\nCglibProxy (Spring edition)");
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(PersonDaoSimple.class);
		enhancer.setCallback(new org.springframework.cglib.proxy.InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] methodArgs) throws Throwable {
				aspect.logBefore(personDaoSimple.getClass(), proxy.getClass(), method, methodArgs);
				return method.invoke(personDaoSimple, methodArgs);
			}
		});
		PersonDao personDaoCgLibProxy = (PersonDao) enhancer.create();
		personService = new PersonServiceImpl(personDaoCgLibProxy);
		personService.getByName("Игорь");
	}

	private static class LoggingAspectSimple {
		public void logBefore(Class<?> originalClass, Class<?> proxyClass, Method method, Object[] methodArgs) {
			System.out.println("Прокси : " + proxyClass.getName());
			System.out.println("Класс : " + originalClass.getName());

			System.out.println("Вызов метода : " + method.getName());
			System.out.println("Аргументы метода : " + Arrays.stream(methodArgs)
					.map(Objects::toString)
					.collect(Collectors.joining(", ")));
		}
	}
}
