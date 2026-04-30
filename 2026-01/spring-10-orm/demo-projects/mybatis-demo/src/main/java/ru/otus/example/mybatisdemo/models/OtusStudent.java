package ru.otus.example.mybatisdemo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtusStudent {
    private long id;
    private String name;
    private Avatar avatar;
    private List<EMail> emails;
    private List<Course> courses;
}
