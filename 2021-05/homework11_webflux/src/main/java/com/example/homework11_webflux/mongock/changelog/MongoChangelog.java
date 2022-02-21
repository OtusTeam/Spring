package com.example.homework11_webflux.mongock.changelog;


import com.example.homework11_webflux.model.Dictionary;
import com.example.homework11_webflux.model.User;
import com.example.homework11_webflux.model.Word;
import com.example.homework11_webflux.model.WordState;
import com.example.homework11_webflux.repository.DictionaryRepository;
import com.example.homework11_webflux.repository.UserRepository;
import com.example.homework11_webflux.repository.WordRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@ChangeLog
public class MongoChangelog {
    private Mono<User> user;
    private Mono<Dictionary> dict;

    //удаление базы
    @ChangeSet(author = "lenu", id = "dropDB", order = "001", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

//    //добавляем пользователей в базу
//    @ChangeSet(author = "lenu", id = "initUsers", order = "002", runAlways = true)
//    public void insertUsers(UserRepository repository) {
//        user = repository.save(new User("0", "Lenu"));
//
//    }
//
//    //добавляем словарь в базу
//    @ChangeSet(author = "lenu", id = "initDictionaries", order = "003")
//    public void insertDictionaries(DictionaryRepository repository) {
//        dict = repository.save(new Dictionary("0", "My Dictionary", LocalDate.of(2022, 02, 10), user, "main dictionary"));
//    }

    //добавляем слова в словарь
    @ChangeSet(author = "lenu", id = "initBooks", order = "001", runAlways = true)
    public void insertWords(WordRepository repository, DictionaryRepository dictRepository, UserRepository userRepository) {
        Mono<Word> word1 = repository.save(new Word("0", "tenacious", "упорный, упрямый", "merriam-webster",
                "The tenacious king salmon will likely be coming back for many, many autumns to come.",
                LocalDate.of(2022, 02, 10), null, WordState.NEW, 1));

        Mono<Word> word2 = repository.save(new Word("1", "treadmill", "беговая дорожка", "Dr. House",
                "Put the patient on a treadmill.",LocalDate.of(2022,02, 10),
                null, WordState.NEW, 1));


    }
}
