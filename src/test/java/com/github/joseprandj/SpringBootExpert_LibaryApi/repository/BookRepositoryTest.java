package com.github.joseprandj.SpringBootExpert_LibaryApi.repository;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    public void saveTeste(){
        Book book = new Book();
        book.setIsbn("123-321");
        book.setPrice(new BigDecimal(100));
        book.setGenderBook(GenderBook.FICTION);
        book.setTitle("UFO");
        book.setPublicatedDate(LocalDate.of(1980, 10, 31));
        book.setAuthor(authorRepository.findById(1L).orElseThrow());
        bookRepository.save(book);
    }

    @Test
    public void saveCascadeTeste(){
        Book book = new Book();
        book.setIsbn("123-321");
        book.setPrice(new BigDecimal(100));
        book.setGenderBook(GenderBook.FICTION);
        book.setTitle("UFO");
        book.setPublicatedDate(LocalDate.of(1980, 10, 31));

        Author author = new Author();
        author.setName("Jose Cascade");
        author.setNationality("Brazileira");
        author.setDtBirth(LocalDate.of(1997, 10, 31));

        book.setAuthor(author);

        bookRepository.save(book);
    }

    @Test
    public void updateAuthorTheBook(){
        Author author = authorRepository.findById(1l).orElseThrow();
        Book book = bookRepository.findById(2l).orElseThrow();

        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Test
    public void delete(){
        Book book = bookRepository.findById(2l).orElseThrow();
        bookRepository.deleteById(book.getId());
    }

    @Test
    @Transactional
    public void searchBook(){
        Book book = bookRepository.findById(1L).orElseThrow();
        System.out.printf("Book: %s\n", book.getTitle());
        System.out.printf("Author: %s\n", book.getAuthor().getName());
    }


}
