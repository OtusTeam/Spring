package ru.otus.spring.dao;

import org.springframework.core.io.ClassPathResource;
import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDaoImpl implements QuestionDao{
    private final ClassPathResource resource;

    public QuestionDaoImpl(ClassPathResource resource) {
        this.resource = resource;
    }

    @Override
    public List<Question> findAll() {
        try {
            return Files.readAllLines(resource.getFile().toPath()).stream()
                    .map(iter -> {
                        String[] r = iter.split(";");
                        return new Question(r[0], Integer.parseInt(r[1]));
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
