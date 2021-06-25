package ru.otus.ineritance;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.ineritance.model.A;
import ru.otus.ineritance.model.B;
import ru.otus.ineritance.model.C;
import ru.otus.ineritance.repository.ARepository;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class InheritanceDemo {

    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext ctx = SpringApplication.run(InheritanceDemo.class, args);
        ARepository aRepository = ctx.getBean(ARepository.class);

        System.out.println("\n\n-------------------------------------------\n\n");
        System.out.println("Начинаем вставку сущностей A/B/C: ");

        var a = new A(0, "aaaaaa1");
        var b = new B(0, "aaaaaa2", "bbbbbbb");
        var c = new C(0, "aaaaaa3", "ccccccc");
        aRepository.save(a);
        aRepository.save(b);
        aRepository.save(c);

        System.out.println("\n\n-------------------------------------------\n\n");
        System.out.println("Загружаем все сущности A (в т.ч. наследников):");

        List<A> resultList = aRepository.findAll();

        System.out.println("\n\nРезультат:");
        System.out.println(resultList);


        Console.main();
    }
}
