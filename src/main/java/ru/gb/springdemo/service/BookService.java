package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book getBookById(long id) {
        return bookRepository.getBookByIdRepository(id);
    }

    public Long addBook(String name) {
        return  bookRepository.addBookRepository(name);
    }

    public String deleteBook(long id) {
        return bookRepository.deleteBookRepository(id);
    }

    public List<Book> allBooks() {
        return bookRepository.allBooksRepository();
    }
}
