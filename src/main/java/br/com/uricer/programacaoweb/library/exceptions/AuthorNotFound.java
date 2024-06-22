package br.com.uricer.programacaoweb.library.exceptions;

public class AuthorNotFound extends RuntimeException {
    public AuthorNotFound() {
        super("Usuário não encontrado!");
    }
}
