package ru.otus.example.jpql_demo.repositories;

import ru.otus.example.jpql_demo.dto.CitySalary;
import ru.otus.example.jpql_demo.dto.EmployeeProjects;
import ru.otus.example.jpql_demo.models.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    List<Employee> findAll();
    Optional<Employee> findEmployeeById(long id);
    List<Employee> findAllEmployeesWithSalaryOver100000();
    List<String> findEmployeesFirstNames();
    List<Object[]> findEmployeesFirstAndLastNames();
    long calcEmployeesCount();
    BigDecimal findMaxEmployeeSalary();
    Double calcAvgEmployeeSalary();


    List<CitySalary> calcAvgSalaryByCities();
    List<CitySalary> calcAvgSalaryByCitiesSorted();
    List<CitySalary> calcAvgSalaryByCitiesHavingValueOver100000();


    List<Employee> findEmployeesWithGivenProjects(String p1Name, String p2Name);
    List<EmployeeProjects> findEmployeesProjectsCount();


    List<Employee> findEmployeesWithGivenFirstNames(String name1, String name2);
    List<Employee> findEmployeesWithFirstNamesFromGivenList(List<String> names);
    List<Employee> findEmployeeNameSakes(Employee employee);


    Employee findEmployeeNameSake(Employee employee);
    List<Employee> findEmployeesWithSalaryLessThanGivenEmployee(Employee employee);
    List<Employee> findEmployeeWithNameMatchingAnyOtherEmployeesNames();
    List<Employee> findEmployeesWithSalaryLessThanAllEmployees();


    int updateEmployeesSalary(BigDecimal oldSalary, BigDecimal newSalary);
    int doubleEmployeesSalary(BigDecimal oldSalary);
    int deleteEmployeesWithoutDepartment();



}
