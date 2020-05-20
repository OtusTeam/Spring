package ru.otus.smigunov.project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "checklist")
@Builder
public class CheckList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userid;

    @Column(name = "daynumber")
    private Integer daynumber;

    @Column(name = "nonsymptoms")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean nonsymptoms;

    @Column(name = "fever")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean fever;

    @Column(name = "sorethroat")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean sorethroat;

    @Column(name = "cough")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean cough;

    @Column(name = "runnynose")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean runnynose;

    @Column(name = "dyspnea")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean dyspnea;

    @Column(name = "othersymptoms")
    private String othersymptoms;

    public CheckList() {
    }
}
