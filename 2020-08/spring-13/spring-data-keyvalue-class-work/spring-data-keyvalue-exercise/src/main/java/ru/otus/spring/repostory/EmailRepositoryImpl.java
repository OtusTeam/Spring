package ru.otus.spring.repostory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Email;

import java.util.List;
@Repository
public class EmailRepositoryImpl implements EmailRepository {

    @Autowired
    private KeyValueOperations keyValueOperations;

    @Override
    public List<Email> findAll() {
        return (List<Email>) keyValueOperations.findAll(Email.class);
    }

    @Override
    public Email save(Email email) {
        return keyValueOperations.insert(email);
    }
}
