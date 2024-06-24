package br.com.uricer.programacaoweb.library.dto;

import br.com.uricer.programacaoweb.library.domain.Author;
import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.domain.Book;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AuthorDTO {

    private Integer id;
    private String name;
    private String bio;
    private LocalDate birthDate;
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
        }

        return author;
    }
}
