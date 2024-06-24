package br.com.uricer.programacaoweb.library.dto;

import br.com.uricer.programacaoweb.library.domain.Author;
import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.domain.Book;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class BookDTO {

    private Integer id;
    private String title;
    private String description;
    private Integer publicationYear;
    private String genre;
    private Float stockQuantity;
    private List<Integer> authors;

    public Book toEntity() {
        Book book = Book.builder()
                .id(id)
                .title(title)
                .description(description)
                .publicationYear(publicationYear)
                .genre(genre)
                .stockQuantity(stockQuantity)
                .build();

        if (authors != null && !authors.isEmpty()) {
            List<AuthorBook> authorBooks = authors.stream().map(authorId -> {
                Author author = new Author();
                author.setId(authorId);
                return AuthorBook.builder().author(author).book(book).build();
            }).collect(Collectors.toList());
            book.setAuthorBooks(authorBooks);
        } else {
            book.setAuthorBooks(new ArrayList<>());
        }

        return book;
    }

    public BookDTO withAuthors(List<Integer> authors) {
        this.authors = authors;
        return this;
    }
}
