package br.com.uricer.programacaoweb.library.controller;

import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.services.AuthorBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/author-books")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorBookController {

    @Autowired
    AuthorBookService authorBookService;

    @PostMapping
    public ResponseEntity<AuthorBook> createAuthorBook(@RequestBody AuthorBook authorBook) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorBookService.createAuthorBook(authorBook));
    }

    @GetMapping
    public ResponseEntity<List<AuthorBook>> getAuthorBooks() {
        return ResponseEntity.ok().body(authorBookService.getAuthorBooks());
    }

    @DeleteMapping(path = "/{id}")
    public void deleteAuthorBook(@PathVariable Integer id) {
        authorBookService.deleteAuthorBook(id);
    }
}
