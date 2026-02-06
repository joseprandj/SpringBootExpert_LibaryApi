package com.github.joseprandj.SpringBootExpert_LibaryApi.service;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.User;
import com.github.joseprandj.SpringBootExpert_LibaryApi.exception.OperationNotAllowedExecption;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.AuthorRepository;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.BookRepository;
import com.github.joseprandj.SpringBootExpert_LibaryApi.security.SecurityService;
import com.github.joseprandj.SpringBootExpert_LibaryApi.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorValidator authorValidator;
    private final BookRepository bookRepository;
    private final SecurityService securityService;

    public Author saveAuthor(Author author){
        authorValidator.validate(author);

        User user = securityService.getUserLoad();
        author.setIdUser(user.getIdApi());

        return authorRepository.save(author);
    }

    public Optional<Author> searchAuthorForIdApi(UUID uuid){
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

    public List<Author> listByExample(String name, String nationality){
        Author author = new Author();
        author.setName(name);
        author.setNationality(nationality);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
            .withIgnoreNullValues()
            .withIgnoreCase()
            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); //like

        Example<Author> authorExample = Example.of(author, exampleMatcher);
        return authorRepository.findAll(authorExample);
    }

    public boolean haveBoook(Author author){
        return bookRepository.existsByAuthor(author);
    }
}
