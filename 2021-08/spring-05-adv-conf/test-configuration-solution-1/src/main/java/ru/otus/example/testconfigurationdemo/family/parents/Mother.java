package ru.otus.example.testconfigurationdemo.family.parents;

import org.springframework.stereotype.Component;
import ru.otus.example.testconfigurationdemo.family.FamilyMember;

@Component
public class Mother extends FamilyMember {
    @Override
    public String getName() {
        return "Мама";
    }
}
