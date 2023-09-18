package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
}
