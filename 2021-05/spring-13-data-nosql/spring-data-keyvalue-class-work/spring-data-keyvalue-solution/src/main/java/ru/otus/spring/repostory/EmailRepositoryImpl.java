package ru.otus.spring.repostory;

import org.springframework.data.keyvalue.core.IterableConverter;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.data.keyvalue.core.query.KeyValueQuery;
import org.springframework.stereotype.Repository;
import ru.otus.spring.domain.Email;

import java.util.List;

@Repository
public class EmailRepositoryImpl implements EmailRepository {

    final private KeyValueOperations keyValueTemplate;

    public EmailRepositoryImpl(KeyValueOperations keyValueTemplate) {
        this.keyValueTemplate = keyValueTemplate;
    }

    @Override
    public List<Email> findAll() {
        return IterableConverter.toList(keyValueTemplate.findAll(Email.class));
    }

    @Override
    public List<Email> findByEmailLike(String email) {
        KeyValueQuery<String> kq = new KeyValueQuery<>("email.contains('" + email + "')");
        return IterableConverter.toList(keyValueTemplate.find(kq, Email.class));
    }

    @Override
    public Email save(Email email) {
        return keyValueTemplate.insert(email);
    }
}
