package ru.otus.testingExample.dao;

import java.util.Optional;

public interface GreetingDao {
    Optional<String> findGreetingByCountryCode(String countryCode);
}
