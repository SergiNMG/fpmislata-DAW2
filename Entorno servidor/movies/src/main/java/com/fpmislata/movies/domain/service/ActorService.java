package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Actor;

public interface ActorService {
    int create (Actor actor);

    Actor find (int id);

    void update(int id, Actor actor);

    Actor delete(int id);
}
