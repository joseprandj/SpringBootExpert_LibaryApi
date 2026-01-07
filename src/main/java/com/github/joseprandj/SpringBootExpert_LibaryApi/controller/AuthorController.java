package com.github.joseprandj.SpringBootExpert_LibaryApi.controller;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorResponseDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.repository.AuthorRepository;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

import static com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorResponseDTO.authorResponseDto;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> saveAuthor(@RequestBody AuthorDTO authorDTO){
        Author author = authorDTO.authorDtoToAuthor();
        authorService.saveAuthor(author);

        URI authorURI = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(author.getIdApi())
            .toUri();

        return ResponseEntity.created(authorURI).body(authorResponseDto(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchAuthor(@PathVariable("id") String id){
        Author author = authorService.searchAuthorForIdApi(UUID.fromString(id));

        if (author != null){
            return ResponseEntity.ok(authorResponseDto(author));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found!");
    }
}
