package ru.otus.spring.repostory;

import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Email;

import java.util.List;

@Service
public class EmailRepositoryImpl implements EmailRepository{
    final private KeyValueOperations keyValueTemplate;

    public EmailRepositoryImpl( KeyValueOperations keyValueTemplate ) {
        this.keyValueTemplate = keyValueTemplate;
    }

    @Override
    public List<Email> findAll() {
        return null;
    }

    @Override
    public Email save( Email email ) {
        return null;
    }
}
