package br.com.uricer.programacaoweb.library.services;

import br.com.uricer.programacaoweb.library.domain.Author;
import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.domain.Book;
import br.com.uricer.programacaoweb.library.dto.AuthorDTO;
import br.com.uricer.programacaoweb.library.dto.BookDTO;
import br.com.uricer.programacaoweb.library.exceptions.AuthorNotFound;
import br.com.uricer.programacaoweb.library.repository.AuthorRepository;
import br.com.uricer.programacaoweb.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = authorDTO.toEntity();
        return authorRepository.save(author).toDTO();
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

        author.setName(authorDTO.getName());
        author.setBio(authorDTO.getBio());
        author.setBirthDate(authorDTO.getBirthDate());
        author.setNationality(authorDTO.getNationality());

        if (authorDTO.getBooks() != null && !authorDTO.getBooks().isEmpty()) {
            List<AuthorBook> authorBooks = authorDTO.getBooks().stream()
                    .map(bookId -> {
                        Book book = bookRepository.findById(bookId)
                                .orElseThrow(() -> new RuntimeException("Book not found: " + bookId));
                        return AuthorBook.builder().author(author).book(book).build();
                    })
                    .collect(Collectors.toList());
            author.setAuthorBooks(authorBooks);
        }

        return authorRepository.save(author).toDTO();
    }

    public void deleteAuthor(Integer authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFound::new);

        authorRepository.delete(author);
    }

    public List<AuthorDTO> findAuthorsByBookId(Integer bookId) {
        List<Author> authors = authorRepository.findAuthorsByBookId(bookId);
        return authors.stream().map(Author::toDTO).collect(Collectors.toList());
    }

    public List<BookDTO> findBooksByAuthorId(Integer authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(AuthorNotFound::new);
        List<Book> books = author.getAuthorBooks().stream().map(AuthorBook::getBook).collect(Collectors.toList());
        return books.stream().map(Book::toDTO).collect(Collectors.toList());
    }
}
