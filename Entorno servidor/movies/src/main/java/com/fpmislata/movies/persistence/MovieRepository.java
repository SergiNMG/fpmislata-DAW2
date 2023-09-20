package com.fpmislata.movies.persistence;

import com.fpmislata.movies.domain.entity.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> getAll();
    Movie findById(int id);
}
