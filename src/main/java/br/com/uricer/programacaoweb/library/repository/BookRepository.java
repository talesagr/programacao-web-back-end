package br.com.uricer.programacaoweb.library.repository;

import br.com.uricer.programacaoweb.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b JOIN b.authorBooks ab WHERE ab.author.id = :authorId")
    List<Book> findBooksByAuthorId(@Param("authorId") Integer authorId);
    Optional<Book> findByTitle(String title);
}
