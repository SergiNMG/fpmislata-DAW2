package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.Director;

public interface DirectorRepository {
    int insert(Director director);

    void update(Director director);

    Director find(int id);

    void delete(int id);

    Director findByMovieId(int movieId);
}
