package com.github.joseprandj.SpringBootExpert_LibaryApi.controller;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorResponseDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.User;
import com.github.joseprandj.SpringBootExpert_LibaryApi.mappers.AuthorMapper;
import com.github.joseprandj.SpringBootExpert_LibaryApi.security.SecurityService;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.AuthorService;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorResponseDTO.authorResponseDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorController implements GenericController{

    private final AuthorService service;
    private final AuthorMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> save(@Valid @RequestBody AuthorDTO dto){
        Author author = mapper.toEntity(dto);
        service.saveAuthor(author);

        URI authorURI = createHeaderLocation(author.getIdApi());

        return ResponseEntity.created(authorURI).body(authorResponseDto(author));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<AuthorResponseDTO>> searchListAuthor(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "nationality", required = false) String nationality
    ){
        List<AuthorResponseDTO> list = service
            .listByExample(name, nationality)
            .stream()
            .map(author -> authorResponseDto(author))
            .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<AuthorResponseDTO> searchAuthor(@PathVariable("id") String id){
        return service
            .searchAuthorForIdApi(UUID.fromString(id))
            .map(author -> {
                AuthorResponseDTO response = AuthorResponseDTO.authorResponseDto(author);
                return ResponseEntity.ok(response);
            }).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AuthorResponseDTO> updatedAuthor(@PathVariable("id") String id, @RequestBody AuthorDTO dto){
        return service
            .searchAuthorForIdApi(UUID.fromString(id))
            .map(author -> {
                author.setName(dto.name());
                author.setDtBirth(dto.dtBirth());
                author.setNationality(dto.nationality());

                service.saveAuthor(author);

                return ResponseEntity.ok(authorResponseDto(author));
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAuthor(@PathVariable String id){
        return service
            .searchAuthorForIdApi(UUID.fromString(id))
            .map(author -> {
                service.deleteAuthor(author);
                return ResponseEntity.noContent().build();
            })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found!"));
    }

}
