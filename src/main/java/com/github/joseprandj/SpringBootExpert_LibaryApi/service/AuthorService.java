package com.github.joseprandj.SpringBootExpert_LibaryApi.service;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author){
        return authorRepository.save(author);
    }

    public Author searchAuthorForIdApi(UUID uuid){
        return authorRepository.findByIdApi(uuid);
    }
}
