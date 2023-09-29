package com.fpmislata.movies.persistence;

import com.fpmislata.movies.domain.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    List<Movie> getAll(Optional<Integer> page);
    Movie findById(int id);

    int getTotalNumberOfRecords();
}
