package ru.otus.spring.microservice.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;
import ru.otus.spring.microservice.domain.Person;

@Projection(name = "withlowername", types = Person.class)
public interface PersonLowerNameProjection {

    String getName();

    @Value("#{target.name.toLowerCase()}")
    String getNameLowerCase();
}
