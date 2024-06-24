package br.com.uricer.programacaoweb.library.services;

import br.com.uricer.programacaoweb.library.domain.AuthorBook;
import br.com.uricer.programacaoweb.library.repository.AuthorBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorBookService {

    @Autowired
    private AuthorBookRepository authorBookRepository;

    public AuthorBook createAuthorBook(AuthorBook authorBook) {
        return authorBookRepository.save(authorBook);
    }

    public List<AuthorBook> getAuthorBooks() {
        return authorBookRepository.findAll();
    }

    public void deleteAuthorBook(Integer id) {
        authorBookRepository.deleteById(id);
    }
}
