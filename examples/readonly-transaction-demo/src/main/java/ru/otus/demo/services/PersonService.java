package ru.otus.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.demo.model.Person;
import ru.otus.demo.repository.PersonRepository;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person findById(long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }

    public void updateWithoutTran(long id, String name) {
        Person person = personRepository.findById(id);
        person.setName(name);
    }

    @Transactional
    public void updateWithNormalTran(long id, String name) {
        Person person = personRepository.findById(id);
        person.setName(name);
    }

    @Transactional(readOnly = true)
    public void updateWithReadonlyTran(long id, String name) {
        Person person = personRepository.findById(id);
        person.setName(name);
    }
}
