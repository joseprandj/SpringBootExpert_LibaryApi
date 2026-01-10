package com.github.joseprandj.SpringBootExpert_LibaryApi.service;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.exception.OperationNotAllowedExecption;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.AuthorRepository;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.BookRepository;
import com.github.joseprandj.SpringBootExpert_LibaryApi.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;
    private final BookRepository bookRepository;

    public Author saveAuthor(Author author){
        authorValidator.validate(author);
        return authorRepository.save(author);
    }

    public Author searchAuthorForIdApi(UUID uuid){
        return authorRepository.findByIdApi(uuid);
    }


    public void deleteAuthor(Author author) {
        if (haveBoook(author)) throw new OperationNotAllowedExecption("Not allowed delete with author has a registered book");
        authorRepository.delete(author);
    }

    public List<Author> listAuthorWithNameOrNationality(String name, String nationality) {
        if(name != null || nationality != null) return authorRepository.findByNameOrNationality(name, nationality);
        return authorRepository.findAll();
    }

    public boolean haveBoook(Author author){
        return bookRepository.existsByAuthor(author);
    }
}
