package br.com.uricer.programacaoweb.library.exceptions;

public class BookNotFound extends RuntimeException{
    public BookNotFound() {
        super("Livro não encontrado!");
    }
}
