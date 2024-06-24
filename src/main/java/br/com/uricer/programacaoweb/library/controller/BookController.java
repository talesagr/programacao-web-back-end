package br.com.uricer.programacaoweb.library.controller;

import br.com.uricer.programacaoweb.library.dto.BookDTO;
import br.com.uricer.programacaoweb.library.dto.AuthorDTO;
import br.com.uricer.programacaoweb.library.services.BookService;
import br.com.uricer.programacaoweb.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(bookDTO));
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {
        return ResponseEntity.ok().body(bookService.getBooks());
    }

    @GetMapping(path = "/{bookId}/authors")
    public ResponseEntity<List<AuthorDTO>> getAuthorsByBookId(@PathVariable Integer bookId) {
        return ResponseEntity.ok().body(authorService.findAuthorsByBookId(bookId));
    }

    @PutMapping(path = "/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Integer bookId, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.updateBook(bookId, bookDTO));
    }

    @DeleteMapping(path = "/{bookId}")
    public void deleteBook(@PathVariable Integer bookId) {
        bookService.deleteBook(bookId);
    }
}
