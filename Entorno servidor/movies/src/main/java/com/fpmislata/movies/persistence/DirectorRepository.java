package com.fpmislata.movies.persistence;

import com.fpmislata.movies.domain.entity.Director;

public interface DirectorRepository {
    int insert(Director director);

    void update(Director director);

    Director find(int id);

    void delete(int id);
}
