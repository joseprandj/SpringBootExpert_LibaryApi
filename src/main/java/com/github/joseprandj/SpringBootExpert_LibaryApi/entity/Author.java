package com.github.joseprandj.SpringBootExpert_LibaryApi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "JJ", name = "AUTHOR")
@Getter @Setter @NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_API", unique = true, nullable = false, updatable = false)
    private UUID idApi;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "DT_BIRTH", nullable = false)
    private LocalDate dtBirth;

    @Column(name = "NATIONALITY", length = 50)
    private String nationality;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @PrePersist
    public void prePersist() {
        if (idApi == null) {
            idApi = UUID.randomUUID();
        }
    }
}
