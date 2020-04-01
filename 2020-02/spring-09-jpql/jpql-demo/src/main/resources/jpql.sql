select e from Employee e where e.id = :id
----------------------------------------------------------------------------------
select e from Employee e where e.salary > 100000
----------------------------------------------------------------------------------
select e.firstName from Employee e
----------------------------------------------------------------------------------
select e.firstName, e.lastName from Employee e
----------------------------------------------------------------------------------
select count(e) from Employee e
----------------------------------------------------------------------------------
select max(e.salary) from Employee e
----------------------------------------------------------------------------------
select new ru.otus.example.jpql_demo.dto.CitySalary(e.address.city, avg(e.salary))
from Employee e
group by e.address.city
----------------------------------------------------------------------------------
select new ru.otus.example.jpql_demo.dto.CitySalary(e.address.city, avg(e.salary))
from Employee e
group by e.address.city
order by avg(e.salary)
----------------------------------------------------------------------------------
select new ru.otus.example.jpql_demo.dto.CitySalary(e.address.city, avg(e.salary))
from Employee e
group by e.address.city
having avg(e.salary) > 100000
order by avg(e.salary)
----------------------------------------------------------------------------------
select e
from Employee e join e.projects p1 join e.projects p2
where p1.name = :p1 and p2.name = :p2
----------------------------------------------------------------------------------
select new ru.otus.example.jpql_demo.dto.EmployeeProjects(e, count(p))
from Employee e left join e.projects p
group by e
order by count(p) desc
----------------------------------------------------------------------------------
select e
from Employee e
where e.firstName in (:name1, :name2)
----------------------------------------------------------------------------------
select e
from Employee e
where e.firstName in :names
----------------------------------------------------------------------------------
select e
from Employee e
where e.firstName in (select e2.firstName
                      from Employee e2
					  where e2.lastName = :lastName and
					        e2.id <> :id)
----------------------------------------------------------------------------------
select e
from Employee e
where e.firstName = (select e2.firstName
                     from Employee e2
					 where e2.id = :id) and e.id <> :id
----------------------------------------------------------------------------------
select e
from Employee e
where e.salary < (select e2.salary
                  from Employee e2
				  where e2.id = :id)
----------------------------------------------------------------------------------
select e
from Employee e
where e.firstName = any(select e2.firstName
                        from Employee e2
						where e2.id <> e.id)
----------------------------------------------------------------------------------
select e
from Employee e
where e.salary <= all(select e2.salary
                      from Employee e2)
----------------------------------------------------------------------------------