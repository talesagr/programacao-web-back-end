package br.com.uricer.programacaoweb.library.repository;

import br.com.uricer.programacaoweb.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}

