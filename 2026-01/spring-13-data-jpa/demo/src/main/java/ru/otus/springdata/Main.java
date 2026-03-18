package ru.otus.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import ru.otus.springdata.domain.Email;
import ru.otus.springdata.domain.Person;
import ru.otus.springdata.repository.EmailRepository;
import ru.otus.springdata.repository.PersonRepository;

import java.util.Objects;
import java.util.stream.Collectors;

import static ru.otus.springdata.repository.PersonSpecification.emailAddressLike;
import static ru.otus.springdata.repository.PersonSpecification.nameLike;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class);

        PersonRepository personRepository = context.getBean(PersonRepository.class);
        EmailRepository emailRepository = context.getBean(EmailRepository.class);

        var pushkin = new Person("Александр Сергеевич Пушкин", new Email("alex.pushkin@mail.ru"));
        var block = new Person("Александр Александрович Блок", new Email("alex.block@mail.ru"));
        var lermontov = new Person("Михаил Юрьевич Лермонтов", new Email("michail.lermontov@bk.ru"));
        var gorbachev = new Person("Михаил Сергеевич Горбачев", new Email("gorbachev@mail.ru"));
        var bulgakov = new Person("Михаил Афанасьевич Булгаков", new Email("bulgakov@mail.ru"));

        emailRepository.save(pushkin.getEmail());
        emailRepository.save(block.getEmail());
        emailRepository.save(lermontov.getEmail());
        emailRepository.save(gorbachev.getEmail());
        emailRepository.save(bulgakov.getEmail());

        personRepository.save(pushkin);
        personRepository.save(block);
        personRepository.save(lermontov);
        personRepository.save(gorbachev);
        personRepository.save(bulgakov);

        System.out.println("\n\nИщем почту Горбачева по его id");
        emailRepository.findByPersonId(gorbachev.getId())
                .ifPresent(System.out::println);


        System.out.println("\n\nС помощью Example ищем всех пёрсонов с именем \"Михаил\" и почтой на \"mail.ru\"");
        ExampleMatcher ignoringExampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("email.address", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnorePaths("id", "email.id");

        Example<Person> example = Example.of(new Person("Михаил", new Email(0, "mail.ru")), ignoringExampleMatcher);

        System.out.println(personRepository.findAll(example).stream().map(Objects::toString)
                .collect(Collectors.joining("\n")));


        System.out.println("\n\nС помощью Specification ищем всех пёрсонов с именем \"Александр\" или с почтой на \"bk.ru\"");

        Specification<Person> specification = Specification.where(nameLike("Александр"))
                .or(emailAddressLike("bk.ru"));

        System.out.println(personRepository.findAll(specification).stream().map(Objects::toString)
                .collect(Collectors.joining("\n")));

        System.out.println("\n\n");

    }
}
