package ru.otus.example.jpql_demo.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.example.jpql_demo.dto.CitySalary;
import ru.otus.example.jpql_demo.dto.EmployeeProjects;
import ru.otus.example.jpql_demo.models.Employee;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryJpa implements EmployeeRepository {

    @PersistenceContext
    private final EntityManager em;

    public EmployeeRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Employee> findAll() {
        return em.createQuery("select e from Employee e", Employee.class).getResultList();
    }

    @Override
    public Optional<Employee> findEmployeeById(long id) {
        TypedQuery<Employee> query = em.createQuery(
                "select e from Employee e where e.id = :id"
                , Employee.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Employee> findAllEmployeesWithSalaryOver100000() {
        return em.createQuery(
                "select e from Employee e where e.salary > 100000"
                , Employee.class).getResultList();
    }

    @Override
    public List<String> findEmployeesFirstNames() {
        return em.createQuery(
                "select e.firstName from Employee e"
                , String.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findEmployeesFirstAndLastNames() {
        return em.createQuery(
                "select e.firstName, e.lastName from Employee e"
        ).getResultList();
    }

    @Override
    public long calcEmployeesCount() {
        return em.createQuery(
                "select count(e) from Employee e"
                , Long.class).getSingleResult();
    }

    @Override
    public BigDecimal findMaxEmployeeSalary() {
        return em.createQuery(
                "select max(e.salary) from Employee e"
                , BigDecimal.class).getSingleResult();
    }

    @Override
    public Double calcAvgEmployeeSalary() {
        return em.createQuery(
                "select avg(e.salary) from Employee e"
                , Double.class).getSingleResult();
    }

    //-------------------------------------------------------------------------------------------------------

    @Override
    public List<CitySalary> calcAvgSalaryByCities() {
        return em.createQuery(
                "select new ru.otus.example.jpql_demo.dto.CitySalary(e.address.city, avg(e.salary)) " +
                        "from Employee e " +
                        "group by e.address.city"
                , CitySalary.class).getResultList();
    }

    @Override
    public List<CitySalary> calcAvgSalaryByCitiesSorted() {
        return em.createQuery(
                "select new ru.otus.example.jpql_demo.dto.CitySalary(e.address.city, avg(e.salary)) " +
                        "from Employee e " +
                        "group by e.address.city " +
                        "order by avg(e.salary)"
                , CitySalary.class).getResultList();
    }

    @Override
    public List<CitySalary> calcAvgSalaryByCitiesHavingValueOver100000() {
        return em.createQuery(
                "select new ru.otus.example.jpql_demo.dto.CitySalary(e.address.city, avg(e.salary)) " +
                        "from Employee e " +
                        "group by e.address.city " +
                        "having avg(e.salary) > 100000" +
                        "order by avg(e.salary) "
                , CitySalary.class).getResultList();
    }

    //-------------------------------------------------------------------------------------------------------

    @Override
    public List<Employee> findEmployeesWithGivenProjects(String p1Name, String p2Name) {
        TypedQuery<Employee> query = em.createQuery(
                "select e " +
                        "from Employee e join e.projects p1 join e.projects p2 " +
                        "where p1.name = :p1 and p2.name = :p2"
                , Employee.class);
        query.setParameter("p1", p1Name);
        query.setParameter("p2", p2Name);
        return query.getResultList();
    }

    @Override
    public List<EmployeeProjects> findEmployeesProjectsCount() {
        return em.createQuery(
                "select new ru.otus.example.jpql_demo.dto.EmployeeProjects(e, count(p)) " +
                        "from Employee e left join e.projects p " +
                        "group by e " +
                        "order by count(p) desc "
                , EmployeeProjects.class).getResultList();
    }

    //-------------------------------------------------------------------------------------------------------

    @Override
    public List<Employee> findEmployeesWithGivenFirstNames(String name1, String name2) {
        TypedQuery<Employee> query = em.createQuery(
                "select e " +
                        "from Employee e " +
                        "where e.firstName in (:name1, :name2) "
                , Employee.class);
        query.setParameter("name1", name1);
        query.setParameter("name2", name2);
        return query.getResultList();
    }

    @Override
    public List<Employee> findEmployeesWithFirstNamesFromGivenList(List<String> names) {
        TypedQuery<Employee> query = em.createQuery(
                "select e " +
                        "from Employee e " +
                        "where e.firstName in :names "
                , Employee.class);
        query.setParameter("names", names);
        return query.getResultList();
    }

    @Override
    public List<Employee> findEmployeeNameSakes(Employee employee) {
        TypedQuery<Employee> query = em.createQuery(
                "select e " +
                        "from Employee e " +
                        "where e.firstName in (select e2.firstName from Employee e2 where e2.lastName = :lastName and e2.id <> :id) "
                , Employee.class);
        query.setParameter("lastName", employee.getLastName());
        query.setParameter("id", employee.getId());
        return query.getResultList();
    }

    //-------------------------------------------------------------------------------------------------------

    @Override
    public Employee findEmployeeNameSake(Employee employee) {
        TypedQuery<Employee> query = em.createQuery(
                "select e " +
                        "from Employee e " +
                        "where e.firstName = (select e2.firstName from Employee e2 where e2.id = :id) and e.id <> :id "
                , Employee.class);
        query.setParameter("id", employee.getId());
        return query.getSingleResult();
    }

    @Override
    public List<Employee> findEmployeesWithSalaryLessThanGivenEmployee(Employee employee) {
        TypedQuery<Employee> query = em.createQuery(
                "select e " +
                        "from Employee e " +
                        "where e.salary < (select e2.salary from Employee e2 where e2.id = :id) "
                , Employee.class);
        query.setParameter("id", employee.getId());
        return query.getResultList();
    }

    @Override
    public List<Employee> findEmployeeWithNameMatchingAnyOtherEmployeesNames() {
        return em.createQuery(
                "select e " +
                        "from Employee e " +
                        "where e.firstName = any(select e2.firstName from Employee e2 where e2.id <> e.id) "
                , Employee.class).getResultList();
    }

    @Override
    public List<Employee> findEmployeesWithSalaryLessThanAllEmployees() {
        return em.createQuery(
                "select e " +
                        "from Employee e " +
                        "where e.salary <= all(select e2.salary from Employee e2) "
                , Employee.class).getResultList();
    }

    //-------------------------------------------------------------------------------------------------------

    @Override
    public int updateEmployeesSalary(BigDecimal oldSalary, BigDecimal newSalary) {
        Query query = em.createQuery("update Employee e set e.salary = :newSalary where e.salary = :oldSalary");
        query.setParameter("newSalary", newSalary);
        query.setParameter("oldSalary", oldSalary);
        return query.executeUpdate();
    }

    @Override
    public int doubleEmployeesSalary(BigDecimal oldSalary) {
        Query query = em.createQuery("update Employee e set e.salary = e.salary * 2 where e.salary = :oldSalary");
        query.setParameter("oldSalary", oldSalary);
        return query.executeUpdate();
    }

    @Override
    public int deleteEmployeesWithoutDepartment() {
        return em.createQuery("delete from Employee e where e.department is null")
                .executeUpdate();
    }
}
