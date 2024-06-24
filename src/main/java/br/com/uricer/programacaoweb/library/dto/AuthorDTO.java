package br.com.uricer.programacaoweb.library.dto;

import br.com.uricer.programacaoweb.library.domain.Author;
import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
public class AuthorDTO {

    private Integer id;
    private String name;
    private String bio;
    private String birthDate;
    private String nationality;
    private List<Integer> books;

    public Author toEntity() {
        Author author = Author.builder()
                .id(id)
                .name(name)
                .bio(bio)
                .birthDate(birthDate)
                .nationality(nationality)
                .build();

        if (books != null && !books.isEmpty()) {
            List<AuthorBook> authorBooks = books.stream().map(bookId -> {
                Book book = new Book();
                book.setId(bookId);
                return AuthorBook.builder().author(author).book(book).build();
            }).collect(Collectors.toList());
            author.setAuthorBooks(authorBooks);
        } else {
            author.setAuthorBooks(new ArrayList<>());
        }

        return author;
    }

    public static AuthorDTO fromEntity(Author author) {
        List<Integer> bookIds = author.getAuthorBooks().stream()
                .map(authorBook -> authorBook.getBook().getId())
                .collect(Collectors.toList());

        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .bio(author.getBio())
                .birthDate(author.getBirthDate())
                .nationality(author.getNationality())
                .books(bookIds)
                .build();
    }
}
