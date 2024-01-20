package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final List<Book> books;

    @PostConstruct
    public void generateData() {
        books.addAll(List.of(
                new Book("Война и мир"),
                new Book("Мёртвые души"),
                new Book("Чистый код"),
                new Book("Особый район Китая"),
                new Book("Тихий Дон")
        ));
    }

    public Book getBookByIdRepository(long id) {
        return books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Long addBookRepository(String name) {
        Book book = new Book(name);
        books.add(book);
        return book.getId();
    }

    public String deleteBookRepository(Long id) {
        final Book book;
        book = books.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
        if (book != null) {
            books.remove(book);
            return "Книга id = " + id + " удалена";
        } else {
            return "Книга id = " + id + " не нрайдена";
        }

    }

    public List<Book> allBooksRepository() {
        return this.books;
    }
}
