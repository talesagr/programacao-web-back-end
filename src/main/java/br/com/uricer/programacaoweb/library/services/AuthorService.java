package br.com.uricer.programacaoweb.library.services;

import br.com.uricer.programacaoweb.library.domain.Author;
import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.domain.Book;
import br.com.uricer.programacaoweb.library.dto.AuthorDTO;
import br.com.uricer.programacaoweb.library.dto.BookDTO;
import br.com.uricer.programacaoweb.library.exceptions.AuthorNotFound;
import br.com.uricer.programacaoweb.library.repository.AuthorRepository;
import br.com.uricer.programacaoweb.library.repository.BookRepository;
import br.com.uricer.programacaoweb.library.repository.AuthorBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorBookRepository authorBookRepository;

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorDTO.toEntity();
        author = authorRepository.save(author);
        return author.toDTO();
    }

    public AuthorDTO addBooksToAuthor(Integer authorId, List<Integer> bookIds) {
        Author author = authorRepository.findById(authorId).orElseThrow(AuthorNotFound::new);

        if (bookIds != null && !bookIds.isEmpty()) {
            Author finalAuthor = author;
            List<AuthorBook> authorBooks = bookIds.stream()
                    .map(bookId -> {
                        Book book = bookRepository.findById(bookId)
                                .orElseThrow(() -> new RuntimeException("Book not found: " + bookId));
                        return AuthorBook.builder().author(finalAuthor).book(book).build();
                    })
                    .toList();
            author.getAuthorBooks().addAll(authorBooks);
        }

        author = authorRepository.save(author);
        AuthorDTO resultDTO = author.toDTO();
        resultDTO.setBooks(bookIds != null ? bookIds : new ArrayList<>());
        return resultDTO;
    }

    public List<AuthorDTO> getAuthors() {
        List<AuthorDTO> authorDTOS = new ArrayList<>();
        List<Author> authors = authorRepository.findAll();

        for (Author author : authors) {
            AuthorDTO authorDTO = author.toDTO();
            List<Integer> bookIds = author.getAuthorBooks() != null ?
                    author.getAuthorBooks().stream()
                            .map(ab -> ab.getBook().getId())
                            .collect(Collectors.toList()) :
                    new ArrayList<>();
            authorDTO.setBooks(bookIds);
            authorDTOS.add(authorDTO);
        }

        return authorDTOS;
    }

    public AuthorDTO updateAuthor(Integer authorId, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFound::new);

        author.setName(authorDTO.getName());
        author.setBio(authorDTO.getBio());
        author.setBirthDate(authorDTO.getBirthDate());
        author.setNationality(authorDTO.getNationality());

        Author finalAuthor = author;
        List<AuthorBook> authorBooks = authorDTO.getBooks().stream().map(bookId -> {
            Book book = new Book();
            book.setId(bookId);
            return AuthorBook.builder().author(finalAuthor).book(book).build();
        }).collect(Collectors.toList());
        author.setAuthorBooks(authorBooks);

        author = authorRepository.save(author);
        return AuthorDTO.fromEntity(author);
    }

    public void deleteAuthor(Integer authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFound::new);

        authorRepository.delete(author);
    }

    public List<AuthorDTO> findAuthorsByBookId(Integer bookId) {
        List<Author> authors = authorRepository.findAuthorsByBookId(bookId);
        return authors.stream().map(author -> {
            AuthorDTO authorDTO = author.toDTO();
            List<Integer> bookIds = author.getAuthorBooks() != null ?
                    author.getAuthorBooks().stream()
                            .map(ab -> ab.getBook().getId())
                            .collect(Collectors.toList()) :
                    new ArrayList<>();
            authorDTO.setBooks(bookIds);
            return authorDTO;
        }).collect(Collectors.toList());
    }

    public List<BookDTO> findBooksByAuthorId(Integer authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(AuthorNotFound::new);
        Set<Book> uniqueBooks = new HashSet<>(author.getAuthorBooks().stream()
                .map(AuthorBook::getBook)
                .collect(Collectors.toSet()));
        return uniqueBooks.stream().map(Book::toDTO).collect(Collectors.toList());
    }
}
