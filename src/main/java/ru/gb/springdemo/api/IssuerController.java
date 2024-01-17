package ru.gb.springdemo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
public class IssuerController {

    private final IssueRepository issues;
    private final IssuerService service;

    @PatchMapping("/{issueId}")
    public void returnBook(@PathVariable long issueId) {
        issues.returnAt(issueId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getIssue(@PathVariable Long id) {
        final Issue issue;
        issue = issues.getIssueById(id);
        if (issue == null) {
            System.out.println("Выдача: не найдена");
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("Выдача: " + issues.getIssueById(id));
            return ResponseEntity.status(HttpStatus.OK).body(issue);
        }
    }

    @PostMapping
    public Object issueBook(@RequestBody IssueRequest request) {
        System.out.println("Получен запрос на выдачу: readerId = "
                + request.getReaderId() + " bookId = " + request.getBookId());
        final Issue issue;
        try {
            issue = service.issue(request);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
        if (issue != null)
            return ResponseEntity.status(HttpStatus.OK).body(issue);
        else
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Превышен лимит выдачи книг по читателю");
    }

}

