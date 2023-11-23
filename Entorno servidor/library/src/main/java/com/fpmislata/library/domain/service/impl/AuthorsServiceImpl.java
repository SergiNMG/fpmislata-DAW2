package com.fpmislata.library.domain.service.impl;

import com.fpmislata.library.domain.model.Authors;
import com.fpmislata.library.domain.repository.AuthorsRepository;
import com.fpmislata.library.domain.service.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsServiceImpl implements AuthorsService {

    @Autowired
    AuthorsRepository authorsRepository;

    public List<Authors> findByBookIsbn(String isbn){
        return authorsRepository.findByBookIsbn(isbn);
    }
}
