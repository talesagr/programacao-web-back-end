package br.com.uricer.programacaoweb.library.config;

import br.com.uricer.programacaoweb.library.domain.Author;
import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.domain.Book;
import br.com.uricer.programacaoweb.library.repository.AuthorRepository;
import br.com.uricer.programacaoweb.library.repository.BookRepository;
import br.com.uricer.programacaoweb.library.repository.AuthorBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorBookRepository authorBookRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            Author defaultAuthor = authorRepository.findByName("Default Author")
                    .orElseGet(() -> authorRepository.save(Author.builder()
                            .name("Vladimir Nabokov")
                            .bio("Controversial Author")
                            .birthDate("22-04-1899")
                            .nationality("Russian-American")
                            .build()));

            Book defaultBook = bookRepository.findByTitle("Default Book")
                    .orElseGet(() -> bookRepository.save(Book.builder()
                            .title("Lolita")
                            .description("Humbert Humbert is obsessed with his step-daughter Dolores Haze")
                            .publicationYear(1955)
                            .genre("Classics")
                            .stockQuantity(10.0f)
                            .build()));

            if (authorBookRepository.findByAuthorIdAndBookId(defaultAuthor.getId(), defaultBook.getId()).isEmpty()) {
                authorBookRepository.save(AuthorBook.builder()
                        .author(defaultAuthor)
                        .book(defaultBook)
                        .build());
            }
        };
    }
}
