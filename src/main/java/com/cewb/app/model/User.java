package com.cewb.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ManyToMany( targetEntity = Role.class, cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name="user_roles",
            joinColumns = {@JoinColumn(name="user_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name="role_id", nullable = false)}
    )
    private Set<Role> roles = new HashSet<>();

    public User(String name, String password, String email) {
        super();
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
