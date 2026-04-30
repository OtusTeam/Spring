package ru.otus.spring.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.spi.Readable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.otus.spring.domain.PersonDto;

import java.util.List;


@Repository
public class PersonRepositoryCustom {

    private final R2dbcEntityTemplate template;
    private final ObjectMapper objectMapper;

    private static final String SQL_ALL = """
            select json_array(select note_text from notes n where n.person_id = p.id) as notes, p.id,
                   p.last_name, p.age
                 from person p
            """;

    public PersonRepositoryCustom(R2dbcEntityTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }

    public Flux<PersonDto> findAll() {
        return template.getDatabaseClient().inConnectionMany(connection ->
                Flux.from(connection.createStatement(SQL_ALL)
                                .execute())
                        .flatMap(result -> result.map(this::mapper)));
    }

    private PersonDto mapper(Readable selectedRecord) {
        var notesAsText = selectedRecord.get("notes", String.class);
        try {
            List<String> notes = objectMapper.readValue(notesAsText, new TypeReference<>() {
            });
            return new PersonDto(selectedRecord.get("id", Long.class),
                    selectedRecord.get("last_name", String.class),
                    selectedRecord.get("age", Integer.class),
                    notes);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("notes:" + notesAsText + " parsing error:" + e);
        }
    }
}
