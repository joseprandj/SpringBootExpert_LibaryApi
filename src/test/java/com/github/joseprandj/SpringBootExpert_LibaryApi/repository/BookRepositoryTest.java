package com.github.joseprandj.SpringBootExpert_LibaryApi.repository;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;

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
        book.setAuthor(authorRepository.findById(2L).orElseThrow());
        repository.save(book);
    }
}
