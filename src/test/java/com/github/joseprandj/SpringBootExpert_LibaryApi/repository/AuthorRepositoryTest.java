package com.github.joseprandj.SpringBootExpert_LibaryApi.repository;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository repository;

    @Test
    public void saveTest(){
        Author author = new Author();
        author.setName("Jose");
        author.setNationality("Brazileira");
        author.setDtBirth(LocalDate.of(1997, 10, 31));

        System.out.printf(repository.save(author).toString());
    }

    @Test
    public void updateTest(){
        Author author = repository.findById(1L).orElseThrow();
        author.setName("JJ");

        System.out.printf(repository.save(author).toString());
    }

    @Test
    public void listAllAuthor(){
        List<Author> authorList = repository.findAll();
        authorList.forEach(System.out::println);
    }

    @Test
    public void countAuthor(){
        System.out.println(repository.count());
    }

    @Test
    public void deleteAuthor(){
        repository.deleteById(1L);
        System.out.printf("Author deletado");
    }
}
