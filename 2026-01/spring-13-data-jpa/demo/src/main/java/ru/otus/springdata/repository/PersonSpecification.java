package ru.otus.springdata.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.otus.springdata.domain.Person;

public class PersonSpecification {

    public static Specification<Person> nameLike(String name) {
        if (name == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Person> emailAddressLike(String address) {
        if (address == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(root.join("email").get("address"), "%" + address + "%");
    }
}
