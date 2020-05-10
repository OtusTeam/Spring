package ru.otus.resourceserver.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentUserRequestResult {
    private String userName;
    private String firstName;
    private String fatherName;
}
