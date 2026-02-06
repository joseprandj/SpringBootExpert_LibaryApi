package com.github.joseprandj.SpringBootExpert_LibaryApi.repository;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void saveTest(){
        Author author = new Author();
        author.setName("Jose");
        author.setNationality("Brazileira");
        author.setDtBirth(LocalDate.of(1997, 10, 31));

        System.out.printf(authorRepository.save(author).toString());
    }

    @Test
    public void updateTest(){
        Author author = authorRepository.findById(1L).orElseThrow();
        author.setName("JJ");

        System.out.printf(authorRepository.save(author).toString());
    }

    @Test
    public void listAllAuthor(){
        List<Author> authorList = authorRepository.findAll();
        authorList.forEach(System.out::println);
    }

    @Test
    public void countAuthor(){
        System.out.println(authorRepository.count());
    }

    @Test
    public void deleteAuthor(){
        authorRepository.deleteById(1L);
        System.out.printf("Author deletado");
    }

    @Test
    public void saveAuthorWithBooks(){
        Author author = new Author();
        author.setName("Antonio");
        author.setNationality("Brazileiro");
        author.setDtBirth(LocalDate.of(1992, 10, 31));
        author.setBooks(new ArrayList<>());

        Book book = new Book();
        book.setIsbn("456-321");
        book.setPrice(new BigDecimal(199.9));
        book.setGenderBook(GenderBook.FICTION);
        book.setTitle("DTT");
        book.setPublicatedDate(LocalDate.of(2022, 10, 31));
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setIsbn("456-748");
        book2.setPrice(new BigDecimal(154));
        book2.setGenderBook(GenderBook.FANTASY);
        book2.setTitle("DTO");
        book2.setPublicatedDate(LocalDate.of(2022, 10, 31));
        book2.setAuthor(author);

        author.getBooks().add(book);
        author.getBooks().add(book2);

        authorRepository.save(author);
    }

    @Test
    //@Transactional
    public void listBooksTheAuthor(){
        Author author = authorRepository.findById(8l).orElseThrow();
        List<Book> listBoooks = bookRepository.findByAuthor(author);

        author.setBooks(listBoooks);
        author.getBooks().forEach(System.out::println);
    }
}
