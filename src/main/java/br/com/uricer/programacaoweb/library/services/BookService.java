package br.com.uricer.programacaoweb.library.services;

import br.com.uricer.programacaoweb.library.domain.Author;
import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.domain.Book;
import br.com.uricer.programacaoweb.library.dto.AuthorDTO;
import br.com.uricer.programacaoweb.library.dto.BookDTO;
import br.com.uricer.programacaoweb.library.exceptions.BookNotFound;
import br.com.uricer.programacaoweb.library.repository.AuthorBookRepository;
import br.com.uricer.programacaoweb.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorBookRepository authorBookRepository;

    public BookDTO createBook(BookDTO bookDTO) {
        return bookRepository.save(bookDTO.toEntity()).toDTO();
    }

    public List<BookDTO> getBooks() {
        return bookRepository.findAll().stream()
                .map(Book::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO updateBook(Integer bookId, BookDTO bookDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFound::new);

        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setGenre(bookDTO.getGenre());
        book.setStockQuantity(bookDTO.getStockQuantity());

        List<AuthorBook> authorBooks = bookDTO.getAuthors().stream().map(authorId -> {
            Author author = new Author();
            author.setId(authorId);
            return AuthorBook.builder().author(author).book(book).build();
        }).collect(Collectors.toList());

        book.setAuthorBooks(authorBooks);

        return bookRepository.save(book).toDTO();
    }

    public void deleteBook(Integer bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFound::new);

        List<AuthorBook> authorBooks = authorBookRepository.findByBook(book);
        authorBookRepository.deleteAll(authorBooks);

        bookRepository.delete(book);
    }

}
