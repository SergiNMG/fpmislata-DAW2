package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Director;

import java.util.Optional;

public interface
DirectorService {

    Optional<Director> findById(int id);
    Optional<Director> findByMovies_Id(int movieId);
    int create(Director director);
    void update(int id, Director director);
    Optional<Director> delete(int id);

}
