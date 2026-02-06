package com.github.joseprandj.SpringBootExpert_LibaryApi.entity;

import com.github.joseprandj.SpringBootExpert_LibaryApi.enums   .GenderBook;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "JJ", name = "BOOK")
@Getter @Setter @NoArgsConstructor @ToString(exclude = "author")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ID_API", unique = true, nullable = false, updatable = false)
    private UUID idApi;

    @Column(name = "ISBN", length = 20, nullable = false, unique = true)
    private String isbn;

    @Column(name = "TITLE", length = 200, nullable = false)
    private String title;

    @Column(name = "DT_PUBLICATED")
    private LocalDate publicatedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 30, nullable = false)
    private GenderBook genderBook;

    @Column(name = "PRICE", precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne(
        //cascade = CascadeType.ALL
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "ID_AUTHOR")
    private Author author;

    @CreatedDate
    @Column(name = "DT_CREATED")
    private LocalDateTime dtCreated;

    @LastModifiedDate
    @Column(name = "DT_UPDATED")
    private LocalDateTime dtUpdated;

    @Column(name = "ID_USER")
    private UUID idUser;

    @PrePersist
    public void prePersist() {
        if (idApi == null) {
            idApi = UUID.randomUUID();
        }
    }
}
