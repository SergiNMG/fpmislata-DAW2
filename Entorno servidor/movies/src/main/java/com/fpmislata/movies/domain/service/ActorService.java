package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {
    int create (Actor actor);

    Optional<Actor> find (int id);

    void update(int id, Actor actor);

    Optional<Actor> delete(int id);

    List<Actor> findByMovieId(int movieId);
}
