package ru.otus.spring.integration;

import java.util.Objects;

public record User(String name, int age) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return age == user.age &&
                Objects.equals(name, user.name);
    }

}
