package com.github.joseprandj.SpringBootExpert_LibaryApi.docs;

import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorDTO;
import com.github.joseprandj.SpringBootExpert_LibaryApi.dto.AuthorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "Authors")
public interface AuthorControllerDocs {

    @Operation(
            summary = "Save",
            description = "Save new Author"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Registered with success"),
            @ApiResponse(responseCode = "422", description = "Error validation"),
            @ApiResponse(responseCode = "409", description = "Author is registered")
    })
    public ResponseEntity<Object> save(@Valid @RequestBody AuthorDTO dto);

    @Operation(
            summary = "Search",
            description = "Search All Authors With Params"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success")
    })
    public ResponseEntity<List<AuthorResponseDTO>> searchListAuthor(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nationality", required = false) String nationality
    );

    @Operation(
            summary = "Get Only Author",
            description = "Get Detail Author"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Author found"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    public ResponseEntity<AuthorResponseDTO> searchAuthor(@PathVariable("id") String id);

    @Operation(
            summary = "Update",
            description = "Update Data Author"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Update with success"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "409", description = "Author is registered")
    })
    public ResponseEntity<AuthorResponseDTO> updatedAuthor(@PathVariable("id") String id, @RequestBody AuthorDTO dto);

    @Operation(
            summary = "Delete",
            description = "Delete Author"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Delete with success"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "400", description = "Author has a book registered")
    })
    public ResponseEntity<?> deleteAuthor(@PathVariable String id);
}
