package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Director;

public interface DirectorService {

    int create(Director director);

    void update(int id, Director director);

    Director find(int id);

    Director delete(int id);
}
