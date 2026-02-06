package com.github.joseprandj.SpringBootExpert_LibaryApi.repository;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByIdApi(UUID idApi);

    List<Author> findByNameOrNationality(String name, String nationality);

    Optional<Author> findByNameAndDtBirthAndNationality(String name, LocalDate dtBirth, String nationality);
}
