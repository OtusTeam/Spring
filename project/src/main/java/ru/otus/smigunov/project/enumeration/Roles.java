package ru.otus.smigunov.project.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum Roles {
    ROLE_DOC,
    ROLE_PATIENT;

    public static List<String> allRoles() {
        List<String> result = new ArrayList<>();
        for (Roles role : Roles.values()) {
            result.add(role.name());
        }
        return result;
    }

}
