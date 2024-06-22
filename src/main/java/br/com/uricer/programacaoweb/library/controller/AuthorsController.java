package br.com.uricer.programacaoweb.library.controller;

import br.com.uricer.programacaoweb.library.dto.AuthorDTO;
import br.com.uricer.programacaoweb.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/authors")
@CrossOrigin(origins = "http:localhost:3000")
public class AuthorsController {

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(authorDTO));
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        return ResponseEntity.ok().body(authorService.getAuthors());
    }

    @PutMapping(path = "/{authorId}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Integer authorId, @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.updateAuthor(authorId, authorDTO));
    }

    @DeleteMapping(path = "/{authorId}")
    public void deleteAuthor(@PathVariable Integer authorId) {
        authorService.deleteAuthor(authorId);
    }

}
