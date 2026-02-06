package com.github.joseprandj.SpringBootExpert_LibaryApi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(schema = "JJ", name = "USERS")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_API", unique = true, nullable = false, updatable = false)
    private UUID idApi;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLES", columnDefinition = "CLOB")
    private String roles;

    @Column(name = "EMAIL")
    private String email;

    @PrePersist
    public void prePersist() {
        if (idApi == null) {
            idApi = UUID.randomUUID();
        }
    }
}
