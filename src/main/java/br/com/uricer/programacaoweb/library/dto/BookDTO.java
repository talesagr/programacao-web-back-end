package br.com.uricer.programacaoweb.library.dto;

import br.com.uricer.programacaoweb.library.domain.Book;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDTO {

    private Integer id;
    private String title;
    private String description;
    private Integer publicationYear;
    private String genre;
    private Float stockQuantity;

    public Book toEntity() {
        return Book.builder()
                .id(id)
                .title(title)
                .description(description)
                .publicationYear(publicationYear)
                .genre(genre)
                .stockQuantity(stockQuantity).build();
    }

}
