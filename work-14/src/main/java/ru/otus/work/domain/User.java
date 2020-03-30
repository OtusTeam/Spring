package ru.otus.work.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nonexpired")
    private boolean isAccountNonExpired;

    @Column(name = "nonlocked")
    private boolean isAccountNonLocked;

    @Column(name = "credentialsnonexpired")
    private boolean isCredentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @Fetch(FetchMode.SELECT)
    @OneToMany(targetEntity = UserAuthorities.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private List<UserAuthorities> authorities;

    public User() {
    }
}
