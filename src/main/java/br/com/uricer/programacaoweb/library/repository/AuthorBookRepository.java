package br.com.uricer.programacaoweb.library.repository;

import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {
}
