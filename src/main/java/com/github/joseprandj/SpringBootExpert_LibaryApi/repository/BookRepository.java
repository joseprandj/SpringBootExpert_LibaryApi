package com.github.joseprandj.SpringBootExpert_LibaryApi.repository;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Author;
import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    List<Book> findByAuthor(Author author);

    boolean existsByAuthor(Author author);

    List<Book> findByTitle(String title);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByTitleAndPrice(String isbn, BigDecimal price);

    List<Book> findByTitleOrIsbnOrderByTitle(String title, String isbn);

    List<Book> findByPublicatedDateBetween(LocalDate start, LocalDate Final);

    //Tabela = Entidade
    //Campo = Propriedade
    @Query(
        """
            SELECT LIV
            FROM Book LIV
            ORDER BY
                LIV.title,
                LIV.price
        """
    )
    List<Book> listAllBookOrdernedByTitleAndPrice();

    @Query(
        """
            SELECT AUTHOR
            FROM Book LIV
            INNER JOIN LIV.author AUTHOR
        """
    )
    List<Author> listAuthorsTheBooks();

    @Query(
        """
            SELECT DISTINCT LIV.title
            FROM Book LIV
        """
    )
    List<String> listAllBooksWithNamesDistinct();

    @Query(
        """
            SELECT LIV.genderBook
            FROM Book LIV
            INNER JOIN LIV.author AUTHOR
            WHERE AUTHOR.nationality = 'Brazileira'
            ORDER BY LIV.genderBook
        """
    )
    List<String> listGenderAuthorsBrazilian();

    @Query(
        """
            SELECT LIV
            FROM Book LIV
            WHERE LIV.genderBook = :GENDER_BOOK
        """
    )
    List<Book> findByGenderBook(@Param("GENDER_BOOK") GenderBook genderBook);

    @Modifying
    @Transactional
    @Query("DELETE FROM Book WHERE genderBook = :GENDER_BOOK")
    public void deleteByGender(@Param("GENDER_BOOK") GenderBook genderBook);

    @Modifying
    @Transactional
    @Query("UPDATE Book SET publicatedDate = :DATE WHERE id = :ID")
    public void updateDatePublicated(
            @Param("DATE") LocalDate date,
            @Param("ID") Long id
    );

    Optional<Book> findByIdApi(UUID id);
}
