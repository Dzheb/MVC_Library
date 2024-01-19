package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

    // спринг это все заинжектит
    private final BookService bookService;
    private final ReaderService readerService;
    private final IssueRepository issueRepository;
    // application.max-allowed-books default = 1
    @Value("${application.max-allowed-books:1}")
    private int maxAllowedBooks;

    public Issue issue(IssueRequest request) {
        if (bookService.getBookById(request.getBookId()) == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
        }
        if (readerService.getReaderById(request.getReaderId()) == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
        }
        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
        int books = issueRepository.findBooksByOneReader(request.getReaderId());
        if (books < maxAllowedBooks) {
            Issue issue = new Issue(request.getBookId(), request.getReaderId());
            issueRepository.save(issue);
            return issue;
        } else return null;

    }

    public Issue getIssueById(Long id) {
        return  issueRepository.getIssueByIdRepository(id);
    }

    public void returnAt(long issueId) {
        issueRepository.returnAtRepository(issueId);
    }

    public List<Issue> getIssuesByReader(long id) {
        return issueRepository.getIssuesByReaderRepository(id);
    }
}
