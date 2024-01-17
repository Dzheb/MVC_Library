package ru.gb.springdemo.repository;

import lombok.Data;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    public void save(Issue issue) {
        // insert into ....
        issues.add(issue);
    }

    public Issue getIssueById(long id) {
        return this.issues.stream().filter(it -> it.getId() == id)
                .findFirst().orElse(null);
    }

    public int findBooksByOneReader(long readerId) {
        return issues.stream()
                .filter(it -> it.getReaderId() == readerId
                        && it.getReturned_at() != null).toList().size();
    }

    public List<Issue> getIssuesByReader(long id) {
        return issues.stream().filter(it -> it.getReaderId() == id).toList();
    }

    public void returnAt(Long issueId) {
        issues.stream().filter(it -> it.getId() == issueId)
                .findFirst().ifPresent(issue -> issue
                        .setReturned_at(LocalDateTime.now()));

    }
}
