/*
 * Copyright 2016 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right name only.
 */
package ru.otus.spring.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.domain.Person;

@Data
@AllArgsConstructor
public class PersonDto {

    private long id;
    private String name;

    public static PersonDto toDto(Person person) {
        return new PersonDto(person.getId(), person.getName());
    }

    public Person toDomainObject() {
        return new Person(id, name);
    }
}
