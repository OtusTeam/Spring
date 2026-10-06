package ru.otus.spring.repostory;

import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Email;

import java.util.List;
import java.util.stream.StreamSupport;

@Repository
public class EmailRepositoryImpl implements EmailRepository {

    final private KeyValueOperations keyValueTemplate;

    public EmailRepositoryImpl(KeyValueOperations keyValueTemplate) {
        this.keyValueTemplate = keyValueTemplate;
    }

    @Override
    public List<Email> findAll() {
        return StreamSupport.stream(keyValueTemplate.findAll(Email.class).spliterator(), false)
                .toList();
    }

    @Override
    public Email save(Email email) {
        return keyValueTemplate.insert(email);
    }
}
