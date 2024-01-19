package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.service.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    // dependency injection
    final BookService service;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookName(@PathVariable long id) {
       final Book book;
        book = service.getBookById(id);
        if (book == null) {
            System.out.println("Книга: не найдена");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Книга: " + service.getBookById(id));
            return ResponseEntity.status(HttpStatus.OK).body(book);

        }
    }

    @PostMapping
    public Long addBook(@RequestBody Book book) {
        return service.addBook(book.getName());

    }
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id) {
        return service.deleteBook(id);
    }

}
