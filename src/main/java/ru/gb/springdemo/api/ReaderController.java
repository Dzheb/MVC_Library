package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.service.IssuerService;
import ru.gb.springdemo.service.ReaderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reader")

public class ReaderController {
    // dependency injection
    private final ReaderService readerService;
    private final IssuerService issuerService;


    @GetMapping("/{id}")
    public ResponseEntity<Reader> getBookName(@PathVariable long id) {
        //log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());
        final Reader reader;
        reader = readerService.getReaderById(id);
        if (reader == null) {
            System.out.println("Читатель: не найден");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Читатель: " + readerService.getReaderById(id));
            return ResponseEntity.status(HttpStatus.OK).body(reader);

        }
    }

    @PostMapping
    public Long addReader(@RequestBody Reader reader) {
        return readerService.addReader(reader.getName());

    }

    @DeleteMapping("/{id}")
    public String deleteReader(@PathVariable long id) {
        return readerService.deleteReader(id);
    }

    @GetMapping("/{id}/issue")
    public ResponseEntity<List<Issue>> getBooksByReader(@PathVariable long id) {
        final List<Issue> issuesReader;
        issuesReader = issuerService.getIssuesByReader(id);
        if (issuesReader.size() < 1) {
            System.out.println("Выдачи по читателю не найдены");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Читатель: " + readerService.getReaderById(id));
            return ResponseEntity.status(HttpStatus.OK).body(issuesReader);

        }
    }
//
//@GetMapping("/api/person/{id}")
//public ResponseEntity<Person> persons(@PathVariable int id) {
//    // ... ищем
//    if (id < 10) {
//        return ResponseEntity.ok(new Person("Igor", "Chestnov", 25, "github.com"));
//    }
//    throw new NoSuchElementException("пользователь не найден");
////    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("не найден");
//}
//
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> notFound(NoSuchElementException e) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//    }
// or ResponseStatusException
//

}
