package com.github.joseprandj.SpringBootExpert_LibaryApi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "JJ", name = "AUTHOR")
@Getter @Setter @NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @OneToMany(mappedBy = "author"
            //, cascade = CascadeType.ALL
    )
    private List<Book> books;

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
