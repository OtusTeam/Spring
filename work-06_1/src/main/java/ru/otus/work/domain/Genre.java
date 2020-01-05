package ru.otus.work.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    private Long id;
    private String name;
}
