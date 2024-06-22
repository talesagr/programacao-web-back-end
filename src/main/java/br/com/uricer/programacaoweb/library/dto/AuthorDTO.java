package br.com.uricer.programacaoweb.library.dto;

import br.com.uricer.programacaoweb.library.domain.Author;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AuthorDTO {

    private Integer id;
    private String name;
    private String bio;
    private LocalDate birthDate;
    private String nationality;

    public Author toEntity() {
        return Author.builder()
                .id(id)
                .name(name)
                .bio(bio)
                .birthDate(birthDate)
                .nationality(nationality).build();
    }

}
