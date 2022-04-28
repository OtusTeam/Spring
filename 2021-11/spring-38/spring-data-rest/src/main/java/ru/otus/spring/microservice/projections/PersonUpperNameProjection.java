package ru.otus.spring.microservice.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import ru.otus.spring.microservice.domain.Person;

@Projection(name = "withuppername", types = Person.class)
public interface PersonUpperNameProjection {

    String getName();

    @Value("#{target.name.toUpperCase()}")
    String getNameUpperCase();
}
