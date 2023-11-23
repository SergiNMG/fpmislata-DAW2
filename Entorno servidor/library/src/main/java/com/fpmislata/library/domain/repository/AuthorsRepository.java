package com.fpmislata.library.domain.repository;

import com.fpmislata.library.domain.model.Authors;

import java.util.List;

public interface AuthorsRepository {
    List<Authors> findByBookIsbn(String isbn);
}
