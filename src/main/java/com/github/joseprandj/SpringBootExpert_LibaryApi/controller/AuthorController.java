package com.github.joseprandj.SpringBootExpert_LibaryApi.controller;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorResponseDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.ErrorResponseDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.exception.DuplicatedRegisterExcepction;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorResponseDTO.authorResponseDto;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody AuthorDTO authorDTO){
        try {
            Author author = authorDTO.authorDtoToAuthor();
            authorService.saveAuthor(author);

            URI authorURI = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(author.getIdApi())
                    .toUri();

            return ResponseEntity.created(authorURI).body(authorResponseDto(author));
        } catch (DuplicatedRegisterExcepction ex){
            ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.conflict(ex.getMessage());
            return ResponseEntity.status(errorResponseDTO.status()).body(errorResponseDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> searchListAuthor(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "nationality", required = false) String nationality
    ){
        List<AuthorResponseDTO> list = authorService.listAuthorWithNameOrNationality(name, nationality)
            .stream()
            .map(author -> authorResponseDto(author))
            .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> searchAuthor(@PathVariable("id") String id){
        Author author = authorService.searchAuthorForIdApi(UUID.fromString(id));

        if (author != null){
            return ResponseEntity.ok(authorResponseDto(author));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found!");
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updatedAuthor(@PathVariable("id") String id, @RequestBody AuthorDTO authorDTO){
        try{
            Author author = authorService.searchAuthorForIdApi(UUID.fromString(id));
            author.setName(authorDTO.name());
            author.setDtBirth(authorDTO.dtBirth());
            author.setNationality(authorDTO.nationality());

            if (author != null) {
                authorService.saveAuthor(author);
                return ResponseEntity.ok(authorResponseDto(author));
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found");
        } catch (DuplicatedRegisterExcepction ex) {
            ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.conflict(ex.getMessage());
            return ResponseEntity.status(errorResponseDTO.status()).body(errorResponseDTO);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable String id){
        Author author = authorService.searchAuthorForIdApi(UUID.fromString(id));

        if (author != null){
            authorService.deleteAuthor(author);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found!");
    }


}
