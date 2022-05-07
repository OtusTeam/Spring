package ru.otus.spring.repostory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.domain.Account;

public interface AccountRepository extends ReactiveMongoRepository<Account, String> {
}
