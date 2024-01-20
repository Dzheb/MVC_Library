package ru.gb.springdemo.model;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Запись о факте выдачи книги (в БД)
 */
@Data
// @Entity
public class Issue {
    public static long sequence = 1L;

    private final long id;
    private final long bookId;
    private final long readerId;

    /**
     * Дата выдачи и возврата
     */
    private final LocalDateTime issued_at;
    public LocalDateTime returned_at;

    public Issue(long bookId, long readerId) {
        this.id = sequence++;
        this.bookId = bookId;
        this.readerId = readerId;
        this.issued_at = LocalDateTime.now();
        this.returned_at = null;

    }

    public void setReturned_at(LocalDateTime returned_at) {
        this.returned_at = returned_at;
    }
}
