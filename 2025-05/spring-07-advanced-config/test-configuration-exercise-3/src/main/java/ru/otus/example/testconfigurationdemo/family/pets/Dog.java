package ru.otus.example.testconfigurationdemo.family.pets;

import org.springframework.stereotype.Component;
import ru.otus.example.testconfigurationdemo.family.FamilyMember;

@Component
public class Dog extends FamilyMember {

    @Override
    public String getName() {
        return "Собака";
    }
}
