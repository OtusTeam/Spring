package ru.otus.junit.domain;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Person {

    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void birthDay() {
        this.age++;
    }
}
