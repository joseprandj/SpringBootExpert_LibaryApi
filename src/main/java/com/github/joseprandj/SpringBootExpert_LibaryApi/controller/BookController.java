package com.github.joseprandj.SpringBootExpert_LibaryApi.controller;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.BookDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.BookResponseDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import com.github.joseprandj.SpringBootExpert_LibaryApi.mappers.BookMapper;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.AuthorService;
import com.github.joseprandj.SpringBootExpert_LibaryApi.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
//@PreAuthorize("hasAnyRole('ADMIN', 'USER')") SE CONFIGURADO AQUI APLICA PARA TODOS OS MÉTODOS
public class BookController implements GenericController{

    private final BookService service;
    private final AuthorService authorService;
    private final BookMapper mapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> createBook(@RequestBody @Valid BookDTO dto){
        Book book = mapper.toEntity(dto);
        service.save(book);
        return ResponseEntity.created(createHeaderLocation(book.getIdApi())).build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable("id") String id){
        return ResponseEntity.ok(
            mapper.toDTO(
                service.searchBookForIdApi(UUID.fromString(id))
            )
        );
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> delete(@PathVariable("id") String id){
        Book book = service.searchBookForIdApi(UUID.fromString(id));
        service.delete(book);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<BookResponseDTO>> search(
        @RequestParam(value = "isbn", required = false) String isbn,
        @RequestParam(value = "title", required = false) String title,
        @RequestParam(value = "name_author", required = false) String nameAuthor,
        @RequestParam(value = "gender", required = false) GenderBook gender,
        @RequestParam(value = "publicated_date", required = false) Integer dtPublicated,
        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "quantity_page", defaultValue = "10") Integer quantityPage
        ){
        return ResponseEntity.ok(
            service.search(isbn, title, nameAuthor, gender, dtPublicated, page, quantityPage)
                .map(mapper::toDTO)
        );
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> update(
        @PathVariable("id") String id,
        @RequestBody  @Valid BookDTO dto
    ){
        Book book = service.searchBookForIdApi(UUID.fromString(id));
        book.setIsbn(dto.isbn());
        book.setTitle(dto.title());
        book.setPublicatedDate(dto.publicatedDate());
        book.setGenderBook(dto.genderBook());
        book.setPrice(dto.price());
        book.setAuthor(authorService.searchAuthorForIdApi(dto.idAuthor()).orElseThrow());

        service.save(book);

        return ResponseEntity.noContent().build();
    }
}
