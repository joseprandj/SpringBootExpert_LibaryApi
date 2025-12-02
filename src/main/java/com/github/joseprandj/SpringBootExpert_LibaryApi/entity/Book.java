package com.github.joseprandj.SpringBootExpert_LibaryApi.entity;

import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(schema = "JJ", name = "BOOK")
@Getter @Setter @NoArgsConstructor @ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_API", unique = true, nullable = false, updatable = false)
    private UUID idApi;

    @Column(name = "ISBN", length = 20, nullable = false)
    private String isbn;

    @Column(name = "TITLE", length = 20, nullable = false)
    private String title;

    @Column(name = "DT_PUBLICATED")
    private LocalDate publicatedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 30, nullable = false)
    private GenderBook genderBook;

    @Column(name = "PRICE", precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "ID_AUTHOR")
    private Author author;

    @PrePersist
    public void prePersist() {
        if (idApi == null) {
            idApi = UUID.randomUUID();
        }
    }
}
