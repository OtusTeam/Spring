package ru.otus.spring.domain;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Table("person")
public class Person {

    @Id
    private final Long id;

    @NotNull
    private final String lastName;

    private final int age;


    @PersistenceCreator
    private Person(Long id, @NotNull String lastName, int age) {
        this.id = id;
        this.lastName = lastName;
        this.age = age;
    }

    public Person(String lastName, int age) {
        this(null, lastName,  age);
    }

    public Long getId() {
        return id;
    }


    public @NotNull String getLastName() {
        return lastName;
    }


    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
