package br.com.uricer.programacaoweb.library.repository;

import br.com.uricer.programacaoweb.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
