package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    Movie findById(int id);
}
