package com.fpmislata.library.domain.repository;

import com.fpmislata.library.domain.model.Books;

import java.util.List;
import java.util.Optional;

public interface BooksRepository {
    List<Books> getAll();

    Optional<Books> findByIsbn(String isbn);
}
