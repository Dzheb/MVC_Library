package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private  final ReaderRepository readerRepository;
    public Reader getReaderById(long id) {
        return readerRepository.getReaderByIdRepository(id);
    }

    public Long addReader(String name) {
        return  readerRepository.addReaderRepository(name);
    }

    public String deleteReader(long id) {
        return readerRepository.deleteReaderRepository(id);
    }

    public List<Reader> allReaders() {
        return readerRepository.allReadersRepository();
    }
}


