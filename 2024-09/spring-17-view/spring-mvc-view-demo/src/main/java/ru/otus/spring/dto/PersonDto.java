package ru.otus.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Person;

import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Data
@AllArgsConstructor
public class PersonDto {

    private long id;

    @NotBlank(message = "{name-field-should-not-be-blank}")
    @Size(min = 2, max = 10, message = "{name-field-should-has-expected-size}")
    private String name;
    private List<String> hobby;

    public String hobbyAsString() {
        if (isEmpty(hobby)){
            return "";
        }
        return String.join(", ", hobby);
    }

    public Person toDomainObject(){
        return new Person(id, name, hobby);
    }

    public static PersonDto fromDomainObject(Person person) {
        return new PersonDto(person.getId(), person.getName(), person.getHobby());
    }
}
