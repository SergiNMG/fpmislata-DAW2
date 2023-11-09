package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    List<Movie> getAll(Optional<Integer> page, Optional<Integer> page_size);
    Optional<Movie> findById(int id);
    int getTotalNumberOfRecords();

    Movie create(Movie movie, int directorId, List<Integer> actorIds);
}
