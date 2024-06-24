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

    public static BookDTO fromEntity(Book book) {
        List<Integer> authorIds = book.getAuthorBooks().stream()
                .map(authorBook -> authorBook.getAuthor().getId())
                .collect(Collectors.toList());

        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .publicationYear(book.getPublicationYear())
                .genre(book.getGenre())
                .stockQuantity(book.getStockQuantity())
                .authors(authorIds)
                .build();
    }
}
