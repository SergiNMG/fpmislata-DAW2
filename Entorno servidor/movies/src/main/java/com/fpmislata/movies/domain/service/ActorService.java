package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Actor;

import java.util.List;

public interface ActorService {
    int create (Actor actor);

    Actor find (int id);

    void update(int id, Actor actor);

    Actor delete(int id);

    List<Actor> findByMovieId(int movieId);
}
