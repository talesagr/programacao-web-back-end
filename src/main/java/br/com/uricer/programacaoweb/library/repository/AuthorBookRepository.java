package br.com.uricer.programacaoweb.library.repository;

import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
    Optional<AuthorBook> findByAuthorIdAndBookId(Integer authorId, Integer bookId);
    List<AuthorBook> findByBook(Book book);

    Collection<Object> findByBookId(Integer bookId);
}
