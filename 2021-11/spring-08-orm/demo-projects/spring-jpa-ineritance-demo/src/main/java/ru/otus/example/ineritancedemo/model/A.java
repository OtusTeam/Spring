package ru.otus.example.ineritancedemo.model;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "exterminator")
@DiscriminatorValue("RootA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
