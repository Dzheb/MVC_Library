package ru.gb.springdemo.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.repository.BookRepository;

@RestController
@RequestMapping("/book")
public class BookController {
    // dependency injection
    final BookRepository books;

    public BookController(BookRepository books) {
        this.books = books;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookName(@PathVariable long id) {
        //log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
        final Book book;
        book = books.getBookById(id);
        if (book == null) {
            System.out.println("Книга: не найдена");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Книга: " + books.getBookById(id));
            return ResponseEntity.status(HttpStatus.OK).body(book);

        }
    }

    @PostMapping
    public Long addBook(@RequestBody Book book) {
        return books.addBook(book.getName());

    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id) {
        return books.deleteBook(id);
    }


}
