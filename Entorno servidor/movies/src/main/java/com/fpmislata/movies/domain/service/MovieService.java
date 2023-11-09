package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> getAll(Optional <Integer> page, Optional<Integer> page_size);
    Movie findById(int id);

    Movie create(MovieCreateWeb movieCreateWeb, int director, List<Integer> actorIds);
    int getTotalNumberOfRecords();
}
