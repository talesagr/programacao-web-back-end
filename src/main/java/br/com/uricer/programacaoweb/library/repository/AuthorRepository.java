package br.com.uricer.programacaoweb.library.repository;

import br.com.uricer.programacaoweb.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("SELECT a FROM Author a JOIN a.authorBooks ab WHERE ab.book.id = :bookId")
    List<Author> findAuthorsByBookId(@Param("bookId") Integer bookId);
}
