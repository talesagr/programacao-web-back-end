package br.com.uricer.programacaoweb.library.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "author_book")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
