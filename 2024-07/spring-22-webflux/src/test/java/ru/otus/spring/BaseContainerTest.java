package ru.otus.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public abstract class BaseContainerTest {
    private static final Logger log = LoggerFactory.getLogger(BaseContainerTest.class);
    private static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:16");

    static {
        POSTGRE_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        log.info("POSTGRE_SQL_CONTAINER.getJdbcUrl:{}", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        registry.add("spring.flyway.url", () -> POSTGRE_SQL_CONTAINER.getJdbcUrl() + "&stringtype=unspecified");
        registry.add("spring.flyway.user", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("spring.flyway.password", POSTGRE_SQL_CONTAINER::getPassword);

        registry.add("spring.r2dbc.url", () -> String.format("r2dbc:postgresql://%s:%d/%s",
                POSTGRE_SQL_CONTAINER.getHost(), POSTGRE_SQL_CONTAINER.getMappedPort(5432), POSTGRE_SQL_CONTAINER.getDatabaseName()));
        registry.add("spring.r2dbc.username", POSTGRE_SQL_CONTAINER::getUsername);
        registry.add("spring.r2dbc.password", POSTGRE_SQL_CONTAINER::getPassword);
    }
}
