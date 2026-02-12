package com.github.joseprandj.SpringBootExpert_LibaryApi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(schema = "JJ", name = "CLIENT")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "UUID", nullable = false)
    private UUID id;

    @Column(name = "CLIENT_ID", nullable = false)
    private String clientId;

    @Column(name = "CLIENT_SECRET", length = 400, nullable = false)
    private String clientSecrect;

    @Column(name = "URI", nullable = false)
    private String redirectUri;

    @Column(name = "SCOPE")
    private String scope;


}
