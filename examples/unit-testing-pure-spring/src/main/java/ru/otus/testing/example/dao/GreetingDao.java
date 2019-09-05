package ru.otus.testing.example.dao;

import java.util.Optional;

public interface GreetingDao {
    Optional<String> findGreetingByCountryCode(String countryCode);
}
