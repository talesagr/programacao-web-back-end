package br.com.uricer.programacaoweb.library.domain;

import br.com.uricer.programacaoweb.library.dto.AuthorDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "author")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "bio")
    private String bio;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "nationality")
    private String nationality;

    public AuthorDTO toDTO() {
        return AuthorDTO.builder()
                .id(id)
                .name(name)
                .bio(bio)
                .birthDate(birthDate)
                .nationality(nationality).build();
    }

}
