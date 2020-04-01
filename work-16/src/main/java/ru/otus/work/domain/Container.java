package ru.otus.work.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Контейнер
 */
@Setter
@Getter
public class Container {
    private Cargo cargo;
    private Boolean dangerous;

    @Override
    public String toString() {
        return "Container{" +
                "cargo=" + cargo +
                ", dangerous=" + dangerous +
                '}';
    }
}
