package com.github.joseprandj.SpringBootExpert_LibaryApi.validator;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.exception.DuplicatedRegisterExcepction;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.AuthorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorValidator {

    private final AuthorRepository authorRepository;

    public AuthorValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void validate(Author author){
        if (existsAuthorRegistered(author)) throw new DuplicatedRegisterExcepction("The Author is registered");
    }

    private boolean existsAuthorRegistered(Author author){
        Optional<Author> authorFind = authorRepository.findByNameAndDtBirthAndNationality(
            author.getName(),
            author.getDtBirth(),
            author.getNationality()
        );

        if (author.getIdApi() == null) return authorFind.isPresent();

        return !author.getId().equals(authorFind.get().getId()) && authorFind.isPresent();
    }

}
