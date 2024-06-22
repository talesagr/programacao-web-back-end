package br.com.uricer.programacaoweb.library.repository;

import br.com.uricer.programacaoweb.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
