package com.github.joseprandj.SpringBootExpert_LibaryApi.service;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.AuthorRepository;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransactionService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void updateWithoutUpdating(){
        Book book = bookRepository.findById(1L).orElseThrow();
        book.setPublicatedDate(LocalDate.of(2024, 06, 01));
    }

    @Transactional
    public void execute(){
        Author author = new Author();
        author.setName("Test Francisco");
        author.setNationality("Brazileira");
        author.setDtBirth(LocalDate.of(1997, 10, 31));
        authorRepository.save(author);

        Book book = new Book();
        book.setIsbn("123-321");
        book.setPrice(new BigDecimal(100));
        book.setGenderBook(GenderBook.FICTION);
        book.setTitle("Francisco's Book");
        book.setPublicatedDate(LocalDate.of(1980, 10, 31));
        book.setAuthor(author);
        bookRepository.save(book);

        if (author.getName().equals("Test Francisco")){
            throw new RuntimeException("Rollback!");
        }
    };
}
