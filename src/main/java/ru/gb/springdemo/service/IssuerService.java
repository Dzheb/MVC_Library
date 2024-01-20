package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.api.IssueRequest;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.IssueUI;
import ru.gb.springdemo.repository.IssueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
        if (issueRepository.getIssues().stream()
                .filter(it -> it.getBookId() == request.getBookId()
                && it.getReturned_at() == null)
                .toList().size() != 0) {
            throw new NoSuchElementException("Книга с идентификатором \"" + request.getBookId() +
                    "\"находится на руках");
        }
        // можно проверить, что у читателя нет книг на руках (или его лимит не превышает в Х книг)
        int books = issueRepository.findBooksByOneReader
                (request.getReaderId());
        if (books < maxAllowedBooks) {
            Issue issue = new Issue(request.getBookId(), request.getReaderId());
            issueRepository.save(issue);
            return issue;
        } else return null;

    }

    public Issue getIssueById(Long id) {
        return issueRepository.getIssueByIdRepository(id);
    }

    public void returnAt(long issueId) {
        issueRepository.returnAtRepository(issueId);
    }

    public List<Book> getIssuesByReaderUI(long id) {
        return issueRepository.getIssuesByReaderRepository(id)
                .stream().filter(it -> it.getReturned_at() == null).map(it -> bookService
                        .getBookById(it.getBookId()))
                .collect(Collectors.toList());
    }
    public List<Issue> getIssuesByReader(long id) {
        return issueRepository.getIssuesByReaderRepository(id);
    }

    public List<IssueUI> allIssues() {
        List<IssueUI> issueUIS = new ArrayList<>();
        List<Issue> issues = issueRepository.getIssues();
        for (Issue issue : issues) {
            IssueUI issueUI = new IssueUI(issue.getId(),
                    bookService.getBookById(issue.getBookId())
                            .getName(),
                    readerService.getReaderById(issue
                            .getReaderId()).getName(),
                    issue.getIssued_at(),
                    issue.getReturned_at());
            issueUIS.add(issueUI);
        }
        return issueUIS;
    }
}
