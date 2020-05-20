package ru.otus.smigunov.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nonexpired")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isAccountNonExpired;

    @Column(name = "nonlocked")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isAccountNonLocked;

    @Column(name = "credentialsnonexpired")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isCredentialsNonExpired;

    @Column(name = "enabled")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;

    @Column(name = "description")
    private String description;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    @Fetch(FetchMode.SELECT)
    @OneToMany(targetEntity = UserAuthorities.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<UserAuthorities> authorities;

    public User() {
    }
}
