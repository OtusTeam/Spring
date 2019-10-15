package ru.otus.spring12.repostory;

import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring12.domain.Email;

import java.util.List;

@Repository
public class EmailRepositoryImpl implements EmailRepository {

    final private KeyValueOperations keyValueTemplate;

    public EmailRepositoryImpl(KeyValueOperations keyValueTemplate) {
        this.keyValueTemplate = keyValueTemplate;
    }

    @Override
    public List<Email> findAll() {
        return (List<Email>) keyValueTemplate.findAll(Email.class);
    }

    @Override
    public Email save(Email email) {
        return keyValueTemplate.insert(email);
    }
}
