package com.fpmislata.library.domain.service.impl;

import com.fpmislata.library.domain.model.Books;
import com.fpmislata.library.domain.repository.BooksRepository;
import com.fpmislata.library.domain.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksRepository booksRepository;

    @Override
    public List<Books> getAll(){
        return booksRepository.getAll();
    }

    @Override
    public Optional<Books> findByIsbn(String isbn){
        return booksRepository.findByIsbn(isbn);
    }
}
