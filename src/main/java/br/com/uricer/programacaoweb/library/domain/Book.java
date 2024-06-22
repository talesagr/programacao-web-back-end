package br.com.uricer.programacaoweb.library.domain;

import br.com.uricer.programacaoweb.library.dto.BookDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "publication_year")
    private Integer publicationYear;

    @Column(name = "genre")
    private String genre;

    @Column(name = "stock_quantity")
    private Float stockQuantity;

    public BookDTO toDTO() {
        return BookDTO.builder()
                .id(id)
                .title(title)
                .description(description)
                .publicationYear(publicationYear)
                .genre(genre)
                .stockQuantity(stockQuantity).build();
    }

}
