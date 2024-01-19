package ru.gb.springdemo.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

    private final List<Reader> readers;

    public ReaderRepository() {
        this.readers = new ArrayList<>();
    }

    @PostConstruct
    public void generateData() {
        readers.addAll(List.of(
                new ru.gb.springdemo.model.Reader("Игорь"),
                new ru.gb.springdemo.model.Reader("Михаил")
        ));
    }

    public Reader getReaderByIdRepository(long id) {
        return readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public Long addReaderRepository(String name) {
        Reader reader = new Reader(name);
        readers.add(reader);
        return reader.getId();
    }

    public String deleteReaderRepository(long id) {
        final Reader reader;
        reader = readers.stream().filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
        if (reader != null) {
            readers.remove(reader);
            return "Читатель id = " + id + " удален";
        } else {
            return "Читатель id = " + id + " не нрайден";
        }

    }


}
