package com.github.joseprandj.SpringBootExpert_LibaryApi.service;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.User;
import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.BookRepository;
import com.github.joseprandj.SpringBootExpert_LibaryApi.security.SecurityService;
import com.github.joseprandj.SpringBootExpert_LibaryApi.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.github.joseprandj.SpringBootExpert_LibaryApi.repository.specs.BookSpecs.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookValidator validator;
    private final SecurityService securityService;

    public Book save(Book book){
        validator.validate(book);

        User user = securityService.getUserLoad();
        book.setIdUser(user.getIdApi());

        return repository.save(book);
    }

    public Book searchBookForIdApi(UUID id){
        return repository
            .findByIdApi(id).orElseThrow();
    }

    public void delete(Book book){
        repository.delete(book);
    }

    public Page<Book> search(String isbn, String title, String nameAuthor, GenderBook genderBook, Integer dtPublicated, Integer page, Integer quantityPage){
        // Specification<Book> specifications = Specification
        //  .where(BookSpecs.isbnEqual(isbn))
        //  .and(BookSpecs.titleLike(title))
        //  .and(BookSpecs.genderEqual(genderBook));

        // where 0 = 0
        Specification<Book> specifications = Specification.where((root, query, cb) -> cb.conjunction());

        if (isbn != null) specifications = specifications.and(isbnEqual(isbn));
        if (title != null) specifications = specifications.and(titleLike(title));
        if (genderBook != null) specifications = specifications.and(genderEqual(genderBook));
        if (dtPublicated != null) specifications = specifications.and(dtPublicatedEqual(dtPublicated));
        if (nameAuthor != null) specifications = specifications.and(nameAuthorLike(nameAuthor));

        Pageable pageRequest = PageRequest.of(page, quantityPage);

        return repository.findAll(specifications, pageRequest);
    }

}
