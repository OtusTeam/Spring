package ru.otus.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    private String name;

    private String alpha3Code;

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code( String alpha3Code ) {
        this.alpha3Code = alpha3Code;
    }
}
