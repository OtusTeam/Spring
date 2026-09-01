package ru.otus.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.otus.demo.aop.LoggingAspect;
import ru.otus.demo.aop.SimpleWeaver;
import ru.otus.demo.dao.BookDao;
import ru.otus.demo.dao.BookDaoSimple;
import ru.otus.demo.dao.PersonDao;
import ru.otus.demo.dao.PersonDaoSimple;

public class Main {

	public static void main(String[] args) {

		// Как будто Bean Definitions
		List<Class<?>> beanDefinitions = List.of(SimpleWeaver.class, LoggingAspect.class,
				PersonDaoSimple.class, BookDaoSimple.class);

		// Как будто контекст
		Map<Class<?>, Object> context = new HashMap<>();
		beanDefinitions.stream()
				// Создание бинов
				.map(Main::createInstance)
				// Постобработка, вивинг
				.forEach(bean -> putBeanIntoContextAndApplyAspectIfNecessary(bean, context));

		// Как будто достаем из контекста дао (уже с примененным аспектом)
		PersonDao personDaoProxy = (PersonDao) context.get(PersonDao.class);
		BookDao bookDaoProxy = (BookDao) context.get(BookDao.class);

		// Используем сервисы
		personDaoProxy.findByName("Вася");
		System.out.println("-------------------------------------------------");
		bookDaoProxy.findByTitle("Укротители велосипедов");
	}

	private static Object createInstance(Class<?> clazz) {
		try {
			return clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static Class<?> getInterfaceOrClass(Object o) {
		if (o.getClass().getInterfaces().length > 0) {
			return o.getClass().getInterfaces()[0];
		}
		return o.getClass();
	}

	private static void putBeanIntoContextAndApplyAspectIfNecessary(Object bean, Map<Class<?>, Object> context) {
		// Если нет интерфейсов, то это наш вивер и аспект. Просто кладем в контекст
		// (сам спринг естественно не такие штуки ориентируется, но у нас будет такое допущение)
		Class<?> key = getInterfaceOrClass(bean);
		if (!key.isInterface()) {
			context.put(key, bean);
			return;
		}

		// Если интерфейсы есть, то это наши дао и мы их вивим с аспектом. Результат кладем в контекст
		SimpleWeaver weaver = (SimpleWeaver) context.get(SimpleWeaver.class);
		LoggingAspect aspect = (LoggingAspect) context.get(LoggingAspect.class);
		context.put(key, weaver.weave(bean, aspect));
	}
}
