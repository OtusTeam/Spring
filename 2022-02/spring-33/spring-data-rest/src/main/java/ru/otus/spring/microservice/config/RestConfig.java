package ru.otus.spring.microservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import ru.otus.spring.microservice.projections.PersonLowerNameProjection;
import ru.otus.spring.microservice.projections.PersonUpperNameProjection;

//@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration,
                                                     CorsRegistry cors) {
        repositoryRestConfiguration.getProjectionConfiguration()
                .addProjection(PersonUpperNameProjection.class)
                .addProjection(PersonLowerNameProjection.class);
    }
}
