package ru.otus.example.jpql_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.example.jpql_demo.models.Employee;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProjects {
    private Employee employee;
    private long projectsCount;

}
