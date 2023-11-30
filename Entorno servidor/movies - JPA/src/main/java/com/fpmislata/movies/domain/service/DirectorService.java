package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Director;

import java.util.Optional;

public interface
DirectorService {

    int create(Director director);

    void update(int id, Director director);

    Optional<Director> find(int id);

    Optional<Director> delete(int id);

    Optional<Director> findByMovieId(int movieId);
}
