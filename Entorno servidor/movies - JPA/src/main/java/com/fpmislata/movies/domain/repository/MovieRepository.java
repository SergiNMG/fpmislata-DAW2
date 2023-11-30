package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.CharacterMovie;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.persistence.model.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    List<Movie> getAll(Optional<Integer> page, Optional<Integer> page_size);
    Optional<Movie> findById(int id);
    int getTotalNumberOfRecords();
    int create(Movie movie);
    int createCharacter(CharacterMovie characterMovie, int movieId);
    void delete(int movieId);
}
