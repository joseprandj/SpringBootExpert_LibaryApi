package com.github.joseprandj.SpringBootExpert_LibaryApi.repository.specs;

import com.github.joseprandj.SpringBootExpert_LibaryApi.entity.Book;
import com.github.joseprandj.SpringBootExpert_LibaryApi.enums.GenderBook;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecs {

    public static Specification<Book> isbnEqual(String isbn){
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleLike(String title){
        return (root, query, cb) -> cb.like(
            cb.upper(root.get("title")),
            "%" + title.toUpperCase() + "%"
        );
    }

    public static Specification<Book> genderEqual(GenderBook genderBook){
        return (root, query, cb) -> cb.equal(root.get("genderBook"), genderBook);
    }

    public static Specification<Book> dtPublicatedEqual(Integer dtPublicated){
        return (root, query, cb) -> cb.equal(
            cb.function(
                "TO_CHAR",
                String.class,
                root.get("publicatedDate"),
                cb.literal("YYYY")
            ),
            dtPublicated.toString()
        );
    }

    public static Specification<Book> nameAuthorLike(String name){
        return (root, query, cb) -> {
            Join<Object, Object> joinAuthor = root.join("author", JoinType.LEFT);
            return cb.like(
                cb.upper(joinAuthor.get("name")),
                "%" + name.toUpperCase() + "%"
            );

            //  Funciona como um INNER JOIN
            //  return cb.like(
            //      cb.upper(root.get("author").get("name")),
            //      "%" + name.toUpperCase() + "%"
            //  );
        };
    }
}
