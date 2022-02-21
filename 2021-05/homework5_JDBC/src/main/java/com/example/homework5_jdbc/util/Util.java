package com.example.homework5_jdbc.util;

import java.util.List;
import java.util.stream.Collectors;

public class Util {
    public static String listToString(List<?> list) {
        return list.stream().map(e -> e.toString()).collect(Collectors.joining(",\n", "{", "}"));
    }
}