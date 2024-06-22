package br.com.uricer.programacaoweb.library.services;

import br.com.uricer.programacaoweb.library.domain.Book;
import br.com.uricer.programacaoweb.library.dto.BookDTO;
import br.com.uricer.programacaoweb.library.exceptions.BookNotFound;
import br.com.uricer.programacaoweb.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookDTO createBook(BookDTO BookDTO) {
        return bookRepository.save(BookDTO.toEntity()).toDTO();
    }

    public List<BookDTO> getBooks() {
        List<BookDTO> bookDTOS = new ArrayList<>();
        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            bookDTOS.add(book.toDTO());
        }

        return bookDTOS;
    }

    public BookDTO updateBook(Integer bookId, BookDTO bookDTO) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFound::new);

        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setGenre(bookDTO.getGenre());
        book.setStockQuantity(bookDTO.getStockQuantity());

        return bookRepository.save(book).toDTO();
    }

    public void deleteBook(Integer bookId) {
        Book Book = bookRepository.findById(bookId)
                .orElseThrow(BookNotFound::new);

        bookRepository.delete(Book);
    }

}
