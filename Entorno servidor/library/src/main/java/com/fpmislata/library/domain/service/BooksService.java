package com.fpmislata.library.domain.service;

import com.fpmislata.library.domain.model.Books;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    public List<Books> getAll();

    public Optional<Books> findByIsbn(String isbn);
}
