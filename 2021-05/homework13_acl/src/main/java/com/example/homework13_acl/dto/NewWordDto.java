package com.example.homework13_acl.dto;


import com.example.homework13_acl.model.Dictionary;
import com.example.homework13_acl.model.WordState;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NewWordDto {

    private String name;
    private String translation;
    private String context;
    private String example;
    private LocalDate addedDate;
    private WordState state;
    private int learntPercent;
    private Dictionary dictionary;
}
