package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.Director;

import java.util.Optional;

public interface DirectorRepository {

    Optional<Director> findById(int id);
    Optional<Director> findByMovies_Id(int movieId);
    int insert(Director director);

    void update(Director director);

    void delete(int id);


}
