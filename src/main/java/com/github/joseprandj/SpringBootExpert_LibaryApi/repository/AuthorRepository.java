package com.github.joseprandj.SpringBootExpert_LibaryApi.repository;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByIdApi(UUID idApi);
}
