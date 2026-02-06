package com.github.joseprandj.SpringBootExpert_LibaryApi.validator;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.exception.DuplicatedRegisterExcepction;
import com.github.joseprandj.SpringBootExpert_LibaryApi.exception.InvalidFieldException;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookValidator {
    private final BookRepository repository;
    private static final int YEAR_MANDATORY_PRICE = 2020;

    public void validate(Book book){
        if(exitsBookWithIsbn(book)) throw new DuplicatedRegisterExcepction("ISBN registered");
        if(isPriceMandatory(book)) throw new InvalidFieldException("price", "Books publicated above 2022, necessary inform price");
    }

    private boolean isPriceMandatory(Book book) {
        return (book.getPrice() == null) &&
            (book.getPublicatedDate().getYear() >= YEAR_MANDATORY_PRICE);
    }

    private boolean exitsBookWithIsbn(Book book){
        Optional<Book> bookFound = repository.findByIsbn(book.getIsbn());

        if(book.getIdApi() == null) return bookFound.isPresent();

        return bookFound
            .map(Book::getIdApi)
            .stream()
            .anyMatch(id -> id.equals(book.getIdApi()));
    }
}
