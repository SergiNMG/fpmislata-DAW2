package com.fpmislata.library.domain.service;

import com.fpmislata.library.domain.model.Authors;

import java.util.List;

public interface AuthorsService {
    List<Authors> findByBookIsbn(String isbn);
}
