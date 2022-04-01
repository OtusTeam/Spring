package ru.otus.example.jpql_demo.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.example.jpql_demo.dto.CitySalary;
import ru.otus.example.jpql_demo.dto.EmployeeProjects;
import ru.otus.example.jpql_demo.models.Employee;

import javax.persistence.NonUniqueResultException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Репозиторий Employee должен")
@DataJpaTest
@Import(EmployeeRepositoryJpa.class)
class EmployeeRepositoryJpaTest {

    private static final long FIRST_EMPLOYEE_ID = 1L;
    private static final long SECOND_EMPLOYEE_ID = 2L;
    private static final long THIRD_EMPLOYEE_ID = 3L;
    private static final long FOURTH_EMPLOYEE_ID = 4L;
    private static final long SEVENTH_EMPLOYEE_ID = 7L;
    private static final long EIGTH_EMPLOYEE_ID = 8L;

    private static final int EMPLOYEES_COUNT = 8;
    private static final String FIRST_EMPLOYEE_FIRST_NAME = "fn1";

    private static final String PROJECT_3 = "Project #3";
    private static final String PROJECT_4 = "Project #4";

    private static final CitySalary SARATOV_SALARY = new CitySalary("Saratov", 66666.0);
    private static final CitySalary OMSK_SALARY = new CitySalary("Omsk", 170000.0);
    private static final CitySalary MOSCOW_SALARY = new CitySalary("Moscow", 330100.0);

    private static final int MAX_SALARY = 1000000;
    private static final double AVG_SALARY = 211299.75d;
    private static final int EMPLOYEES_WITH_SALARY_OVER_100000_COUNT = 4;
    private static final int FOURTH_EMPLOYEE_PROJECTS_COUNT = 4;
    private static final String NAME_SAKE_NAME_1 = "NameSake1";
    private static final String NAME_SAKE_NAME_2 = "NameSake2";

    @Autowired
    private TestEntityManager em;

    @Autowired
    private EmployeeRepositoryJpa employeeRepository;


    @DisplayName("возвращать список всех сотрудников")
    @Test
    void shouldFindAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        assertThat(employees).hasSize(EMPLOYEES_COUNT);
    }

    @DisplayName("возвращать сотрудника по его id")
    @Test
    void shouldFindEmployeeById() {
        Optional<Employee> employee = employeeRepository.findEmployeeById(FIRST_EMPLOYEE_ID);
        assertThat(employee).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("firstName", FIRST_EMPLOYEE_FIRST_NAME);
    }

    @DisplayName("возвращать список всех сотрудников c окладом более 100000")
    @Test
    void shouldFindAllEmployeesWithSalaryOver100000() {
        List<Employee> allEmployeesWithSalaryOver100000 = employeeRepository.findAllEmployeesWithSalaryOver100000();
        assertThat(allEmployeesWithSalaryOver100000).size().isEqualTo(EMPLOYEES_WITH_SALARY_OVER_100000_COUNT);
    }

    @DisplayName("возвращать список имен всех сотрудников")
    @Test
    void shouldFindEmployeesFirstNames() {
        List<String> employeesFirstNames = employeeRepository.findEmployeesFirstNames();
        assertThat(employeesFirstNames)
                .containsExactlyInAnyOrder("fn1", "fn2", "fn3", "fn4", "fn5", "fn6", "fn7", "fn8");
    }

    @DisplayName("возвращать список имен и фамилий всех сотрудников")
    @Test
    void shouldFindEmployeesFirstAndLastNames() {
        List<Object[]> employeesFirstAndLastNames = employeeRepository.findEmployeesFirstAndLastNames();
        String[][] expectedFirstAndLastNames = new String[EMPLOYEES_COUNT][2];
        IntStream.range(1, EMPLOYEES_COUNT + 1)
                .forEachOrdered(i -> expectedFirstAndLastNames[i - 1] = new String[]{"fn" + i, "ln" + i});
        assertThat(employeesFirstAndLastNames).containsExactlyInAnyOrder(expectedFirstAndLastNames);
    }

    @DisplayName("считать общее количество сотрудников")
    @Test
    void shouldCalcEmployeesCount() {
        long employeesCount = employeeRepository.calcEmployeesCount();
        assertThat(employeesCount).isEqualTo(EMPLOYEES_COUNT);
    }

    @DisplayName("находить максимальный оклад сотрудников")
    @Test
    void shouldFindMaxEmployeeSalary() {
        BigDecimal maxSalary = employeeRepository.findMaxEmployeeSalary();
        assertThat(maxSalary).isEqualTo(new BigDecimal(MAX_SALARY));
    }

    @DisplayName("считать средний оклад всех сотрудников")
    @Test
    void shouldCalcAvgEmployeeSalary() {
        Double avgSalary = employeeRepository.calcAvgEmployeeSalary();
        assertThat(avgSalary).isEqualTo(AVG_SALARY, offset(0.01d));
    }

    //-------------------------------------------------------------------------------------------------------

    @DisplayName("возвращать список окладов по городам")
    @Test
    void shouldCalcAvgEmployeeSalaryByCities() {
        List<CitySalary> avgSalaryByCities = employeeRepository.calcAvgSalaryByCities();
        assertThat(avgSalaryByCities)
                .containsExactlyInAnyOrder(SARATOV_SALARY, MOSCOW_SALARY, OMSK_SALARY);
    }

    @DisplayName("возвращать сортированный список окладов по городам")
    @Test
    void shouldCalcAvgEmployeeSalaryByCitiesSorted() {
        List<CitySalary> avgSalaryByCities = employeeRepository.calcAvgSalaryByCitiesSorted();
        assertThat(avgSalaryByCities)
                .containsExactly(SARATOV_SALARY, OMSK_SALARY, MOSCOW_SALARY);
    }

    @DisplayName("возвращать список окладов по городам, где средний доход сотрудников более 100000")
    @Test
    void shouldCalcAvgEmployeeSalaryByCitiesHavingValueOver100000() {
        List<CitySalary> avgSalaryByCities = employeeRepository.calcAvgSalaryByCitiesHavingValueOver100000();
        assertThat(avgSalaryByCities).containsExactly(OMSK_SALARY, MOSCOW_SALARY);
    }

    //-------------------------------------------------------------------------------------------------------

    @DisplayName("возвращать список всех сотрудников работающих над заданными проектами")
    @Test
    void shouldFindEmployeesWithGivenProjects() {
        Employee employee2 = em.find(Employee.class, SECOND_EMPLOYEE_ID);
        Employee employee4 = em.find(Employee.class, FOURTH_EMPLOYEE_ID);
        List<Employee> employees = employeeRepository.findEmployeesWithGivenProjects(PROJECT_3, PROJECT_4);
        assertThat(employees).hasSize(2).containsExactlyInAnyOrder(employee2, employee4);
    }

    @DisplayName("возвращать количество проектов по сотрудникам")
    @Test
    void shouldFindEmployeesProjectsCount() {
        Employee employee4 = em.find(Employee.class, FOURTH_EMPLOYEE_ID);
        List<EmployeeProjects> employeeProjects = employeeRepository.findEmployeesProjectsCount();
        assertThat(employeeProjects).hasSize(EMPLOYEES_COUNT)
                .contains(new EmployeeProjects(employee4, FOURTH_EMPLOYEE_PROJECTS_COUNT));
    }

    //-------------------------------------------------------------------------------------------------------

    @DisplayName("возвращать список всех сотрудников имеющих одно из двух заданных имен")
    @Test
    void shouldFindEmployeesWithGivenFirstNames() {
        Employee employee1 = em.find(Employee.class, FIRST_EMPLOYEE_ID);
        Employee employee7 = em.find(Employee.class, SEVENTH_EMPLOYEE_ID);
        List<Employee> employees = employeeRepository.findEmployeesWithGivenFirstNames("fn1", "fn7");
        assertThat(employees).hasSize(2).containsExactlyInAnyOrder(employee1, employee7);
    }

    @DisplayName("возвращать список всех сотрудников имеющих имя, совпадающее с одним из заданного списка")
    @Test
    void shouldFindEmployeesWithFirstNamesFromGivenList() {
        Employee employee1 = em.find(Employee.class, FIRST_EMPLOYEE_ID);
        Employee employee7 = em.find(Employee.class, SEVENTH_EMPLOYEE_ID);
        List<Employee> employees = employeeRepository.findEmployeesWithFirstNamesFromGivenList(List.of("fn1", "fn7"));
        assertThat(employees).hasSize(2).containsExactlyInAnyOrder(employee1, employee7);
    }

    @DisplayName("возвращать список всех однофамильцев заданного сотрудника")
    @Test
    void shouldFindEmployeesNameSakes() {
        Employee employee1 = em.find(Employee.class, FIRST_EMPLOYEE_ID);
        Employee employee9 = em.persistAndFlush(new Employee(NAME_SAKE_NAME_1, employee1.getLastName()));
        Employee employee10 = em.persistAndFlush(new Employee(NAME_SAKE_NAME_2, employee1.getLastName()));
        List<Employee> nameSakes = employeeRepository.findEmployeeNameSakes(employee1);
        assertThat(nameSakes).hasSize(2).containsExactlyInAnyOrder(employee9, employee10);
    }

    //-------------------------------------------------------------------------------------------------------

    @DisplayName("возвращать список всех тезок заданного сотрудника")
    @Test
    void shouldFindEmployeeNameSake() {
        Employee employee1 = em.find(Employee.class, FIRST_EMPLOYEE_ID);
        Employee employee9 = em.persistAndFlush(new Employee(employee1.getFirstName(), NAME_SAKE_NAME_1));
        Employee nameSake = employeeRepository.findEmployeeNameSake(employee1);
        assertThat(nameSake).usingRecursiveComparison().isEqualTo(employee9);

        em.persistAndFlush(new Employee(employee1.getFirstName(), NAME_SAKE_NAME_2));
        assertThatCode(() -> employeeRepository.findEmployeeNameSake(employee1))
                .isInstanceOf(NonUniqueResultException.class);
    }

    @DisplayName("возвращать список всех сотрудников имеющих оклад меньше, чем у заданного сотрудника")
    @Test
    void shouldFindEmployeesWithSalaryLessThanGivenEmployee() {
        Employee employee1 = em.find(Employee.class, FIRST_EMPLOYEE_ID);
        Employee employee2 = em.find(Employee.class, SECOND_EMPLOYEE_ID);
        Employee employee3 = em.find(Employee.class, THIRD_EMPLOYEE_ID);
        Employee employee7 = em.find(Employee.class, SEVENTH_EMPLOYEE_ID);
        List<Employee> employees = employeeRepository.findEmployeesWithSalaryLessThanGivenEmployee(employee7);
        assertThat(employees).hasSize(3).containsExactlyInAnyOrder(employee1, employee2, employee3);
    }

    @DisplayName("возвращать сотрудника, являющегося тезкой любому другому сотруднику")
    @Test
    void shouldFindEmployeeWithNameMatchingAnyOtherEmployeesNames() {
        Employee employee1 = em.find(Employee.class, FIRST_EMPLOYEE_ID);
        Employee employee9 = em.persistAndFlush(new Employee(employee1.getFirstName(), NAME_SAKE_NAME_1));
        List<Employee> nameSakes = employeeRepository.findEmployeeWithNameMatchingAnyOtherEmployeesNames();
        assertThat(nameSakes).hasSize(2).containsExactlyInAnyOrder(employee9, employee1);
    }

    @DisplayName("возвращать сотрудника имеющго оклад меньше, чем у всех")
    @Test
    void shouldFindEmployeesWithSalaryLessThanAllEmployees() {
        Employee employee3 = em.find(Employee.class, THIRD_EMPLOYEE_ID);
        List<Employee> employees = employeeRepository.findEmployeesWithSalaryLessThanAllEmployees();
        assertThat(employees).hasSize(1).containsOnly(employee3);

    }

    //-------------------------------------------------------------------------------------------------------

    @DisplayName("изменять значение оклада сотрудника имеющего заданный оклад")
    @Test
    void shouldUpdateEmployeesSalary() {
        Employee employee3 = em.find(Employee.class, THIRD_EMPLOYEE_ID);
        BigDecimal oldSalary = employee3.getSalary();
        BigDecimal newSalary = oldSalary.multiply(new BigDecimal(2));
        em.detach(employee3);
        employeeRepository.updateEmployeesSalary(oldSalary, newSalary);

        employee3 = em.find(Employee.class, THIRD_EMPLOYEE_ID);
        assertThat(employee3.getSalary()).isEqualTo(newSalary);
    }

    @DisplayName("изменять значение оклада в два раза, у сотрудника имеющего заданный оклад")
    @Test
    void shouldDoubleEmployeesSalary() {
        Employee employee3 = em.find(Employee.class, THIRD_EMPLOYEE_ID);
        BigDecimal oldSalary = employee3.getSalary();
        BigDecimal newSalary = oldSalary.multiply(new BigDecimal(2));
        em.detach(employee3);
        employeeRepository.doubleEmployeesSalary(oldSalary);

        employee3 = em.find(Employee.class, THIRD_EMPLOYEE_ID);
        assertThat(employee3.getSalary()).isEqualTo(newSalary);
    }

    @DisplayName("удалять сотрудников не относящихся ни к одному отделу")
    @Test
    void shouldDeleteEmployeesWithoutDepartment() {
        Employee employee2 = em.find(Employee.class, SECOND_EMPLOYEE_ID);
        Employee employee8 = em.find(Employee.class, EIGTH_EMPLOYEE_ID);
        assertThat(employee2).isNotNull();
        assertThat(employee8).isNotNull();
        employeeRepository.deleteEmployeesWithoutDepartment();

        em.clear();

        employee2 = em.find(Employee.class, SECOND_EMPLOYEE_ID);
        employee8 = em.find(Employee.class, EIGTH_EMPLOYEE_ID);
        assertThat(employee2).isNull();
        assertThat(employee8).isNull();
    }

}