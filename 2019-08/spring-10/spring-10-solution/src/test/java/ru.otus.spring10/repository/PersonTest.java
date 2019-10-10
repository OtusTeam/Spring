package ru.otus.spring10.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring10.domain.Person;
import ru.otus.spring10.repostory.PersonRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonTest {

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void init(){
        personRepository.save(new Person("Alex"));
    }

    @Test
    public void testPersonName() {
        Person person = personRepository.findByName("Alex");
        Assert.assertEquals("Alex", person.getName());
    }

    @Test
    public void testCount(){
        System.out.println(personRepository.findAll());
        Assert.assertTrue(personRepository.count() > 0);
    }

    @Test
    public void testDeleteAll(){
        personRepository.deleteAll();
        Assert.assertEquals(0, personRepository.count());
    }
}
