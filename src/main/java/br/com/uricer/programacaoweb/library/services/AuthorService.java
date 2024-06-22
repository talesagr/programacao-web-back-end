package br.com.uricer.programacaoweb.library.services;

import br.com.uricer.programacaoweb.library.domain.Author;
import br.com.uricer.programacaoweb.library.dto.AuthorDTO;
import br.com.uricer.programacaoweb.library.exceptions.AuthorNotFound;
import br.com.uricer.programacaoweb.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        return authorRepository.save(authorDTO.toEntity()).toDTO();
    }

    public List<AuthorDTO> getAuthors() {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        List<Author> authors = authorRepository.findAll();

        for (Author author : authors) {
            authorDTOS.add(author.toDTO());
        }

        return authorDTOS;
    }

    public AuthorDTO updateAuthor(Integer authorId, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFound::new);

        author.setName(author.getName());
        author.setBio(author.getBio());
        author.setBirthDate(author.getBirthDate());
        author.setNationality(author.getNationality());

        return authorRepository.save(author).toDTO();
    }

    public void deleteAuthor(Integer authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFound::new);

        authorRepository.delete(author);
    }

}
