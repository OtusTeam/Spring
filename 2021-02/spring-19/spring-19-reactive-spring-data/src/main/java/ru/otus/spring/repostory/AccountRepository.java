package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.domain.Account;
import ru.otus.spring.domain.Person;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
}
