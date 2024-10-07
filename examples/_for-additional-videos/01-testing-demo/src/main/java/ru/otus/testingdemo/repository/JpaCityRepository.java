package ru.otus.testingdemo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.testingdemo.model.City;

public interface JpaCityRepository extends CityRepository, CrudRepository<City, Long> {
}
