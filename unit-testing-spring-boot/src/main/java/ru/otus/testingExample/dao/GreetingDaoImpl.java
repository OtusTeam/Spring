package ru.otus.testingExample.dao;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class GreetingDaoImpl implements GreetingDao {

    private final String initialDataPropertiesResourceName;
    private final Map<String, String> greetingsByCountryCode;

    public GreetingDaoImpl(String initialDataPropertiesResourceName) {
        this.initialDataPropertiesResourceName = initialDataPropertiesResourceName;
        greetingsByCountryCode = new HashMap<>();
        loadInitialData();
    }

    private void loadInitialData() {
        greetingsByCountryCode.clear();

        try {
            try (InputStream initialDataPropertiesResourceStream = getClass().getClassLoader().getResourceAsStream(initialDataPropertiesResourceName)) {
                Properties properties = new Properties();
                properties.load(new InputStreamReader(Objects.requireNonNull(initialDataPropertiesResourceStream), "UTF-8"));
                properties.forEach((key, value) -> greetingsByCountryCode.putIfAbsent(((String) key).toUpperCase(), (String) value));
            }
        }catch (Exception e) {
            throw new InitialDataLoadingException(e);
        }

    }


    public Optional<String> findGreetingByCountryCode(String countryCode) {
        return Optional.ofNullable(greetingsByCountryCode.get(countryCode.toUpperCase()));
    }
}
