package ru.otus.example.ineritancedemo.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@DiscriminatorColumn(name = "discriminator")
@DiscriminatorValue("RootA")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "A")
public class A {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // Не получится использовать при InheritanceType.TABLE_PER_CLASS
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    protected String a;

    public A() {
    }

    public A(long id, String a) {
        this.id = id;
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" +
                "id=" + id +
                ", a='" + a + '\'' +
                '}';
    }
}
