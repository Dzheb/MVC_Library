package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reader")

public class ReaderController {
    // dependency injection
    private final ReaderRepository readers;
    private final IssueRepository issues;


    @GetMapping("/{id}")
    public ResponseEntity<Reader> getBookName(@PathVariable long id) {
        //log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
        final Reader reader;
        reader = readers.getReaderById(id);
        if (reader == null) {
            System.out.println("Читатель: не найден");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Читатель: " + readers.getReaderById(id));
            return ResponseEntity.status(HttpStatus.OK).body(reader);

        }
    }

    @PostMapping
    public Long addBook(@RequestBody Reader reader) {
        return readers.addReader(reader.getName());

    }

    @DeleteMapping("/{id}")
    public String deleteReader(@PathVariable long id) {
        return readers.deleteReader(id);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getBooksByReader(@PathVariable long id) {
        //log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
        final List<Issue> issuesReader;
        issuesReader = issues.getIssuesByReader(id);
        if (issuesReader.size() < 1) {
            System.out.println("Выдачи по читателю не найдены");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Читатель: " + readers.getReaderById(id));
            return ResponseEntity.status(HttpStatus.OK).body(issuesReader);

        }
    }


}
