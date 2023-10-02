package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAll(Optional <Integer> page);
    Movie findById(int id);

    int getTotalNumberOfRecords();
}
