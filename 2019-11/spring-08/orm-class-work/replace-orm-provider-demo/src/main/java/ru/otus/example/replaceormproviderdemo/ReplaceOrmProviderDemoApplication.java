package ru.otus.example.replaceormproviderdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.otus.example.replaceormproviderdemo.config.EclipseLinkJpaConfiguration;

@SpringBootApplication
@Import(EclipseLinkJpaConfiguration.class)
public class ReplaceOrmProviderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReplaceOrmProviderDemoApplication.class, args);
	}

}
