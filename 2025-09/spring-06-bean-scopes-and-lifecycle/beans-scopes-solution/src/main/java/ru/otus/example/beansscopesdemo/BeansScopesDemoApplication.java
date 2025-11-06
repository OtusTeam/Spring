package ru.otus.example.beansscopesdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeansScopesDemoApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(BeansScopesDemoApplication.class, args);
		var serverPort = ctx.getEnvironment().getProperty("local.server.port");
		System.out.printf("Чтобы смотреть результат переходи сюда: http://localhost:%s", serverPort);
	}

}
