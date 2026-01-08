package com.github.joseprandj.SpringBootExpert_LibaryApi.service;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.AuthorRepository;
import com.github.joseprandj.SpringBootExpert_LibaryApi.validator.AuthorValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;

    public AuthorService(AuthorRepository authorRepository, AuthorValidator authorValidator) {
        this.authorRepository = authorRepository;
        this.authorValidator = authorValidator;
    }

    public Author saveAuthor(Author author){
        authorValidator.validate(author);
        return authorRepository.save(author);
    }

    public Author searchAuthorForIdApi(UUID uuid){
        return authorRepository.findByIdApi(uuid);
    }


    public void deleteAuthor(Author author) {
        authorRepository.delete(author);
    }

    public List<Author> listAuthorWithNameOrNationality(String name, String nationality) {
        if(name != null || nationality != null) return authorRepository.findByNameOrNationality(name, nationality);
        return authorRepository.findAll();
    }
}
