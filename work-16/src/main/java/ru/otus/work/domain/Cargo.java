package ru.otus.work.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Груз
 */
@Setter
@Getter
public class Cargo {
    private String type;

    @Override
    public String toString() {
        return "Cargo{" +
                "type='" + type + '\'' +
                '}';
    }
}
