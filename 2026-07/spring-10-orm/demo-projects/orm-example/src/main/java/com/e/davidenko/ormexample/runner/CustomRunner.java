package com.e.davidenko.ormexample.runner;

import com.e.davidenko.ormexample.entity.EmailEntity;
import com.e.davidenko.ormexample.entity.UserEntity;
import com.e.davidenko.ormexample.entity.UserId;
import com.e.davidenko.ormexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomRunner implements CommandLineRunner {

    private final UserRepository userRepository;


    @Override
    public void run(String @NonNull ... args) {
        UserEntity vasya = UserEntity.builder()
                .id(getUserId())
                .firstName("Vasya")
                .build();

        EmailEntity email = new EmailEntity("hello@mail.ru", vasya);
        vasya.setEmailEntities(List.of(email));
        userRepository.save(vasya);
        System.out.println("Vasya: " + vasya);

        UserEntity updatedVasya = UserEntity.builder()
                .id(getUserId())
                .firstName("Vasya")
                .build();
        EmailEntity updatedEmail = new EmailEntity("hello_vasya@mail.ru", updatedVasya);
        EmailEntity newEmail = new EmailEntity("hey@mail.ru", updatedVasya);
        EmailEntity oldEmail = new EmailEntity("hello@mail.ru", updatedVasya);
        updatedVasya.setEmailEntities(List.of(updatedEmail, newEmail, oldEmail));
        userRepository.save(updatedVasya);


        UserEntity byId = userRepository.findById(getUserId()).get();
        System.out.println();
    }

    private static @NonNull UserId getUserId() {
        UserId userId = new UserId();
        userId.setObjectId("123");
        userId.setSystemId("MAIN");
        return userId;
    }
}
