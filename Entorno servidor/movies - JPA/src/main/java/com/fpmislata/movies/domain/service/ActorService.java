package com.fpmislata.movies.domain.service;

import com.fpmislata.movies.domain.entity.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorService {

    Optional<Actor> findById(int id);
    Optional<Actor> findByCharacters_Id(int characterId);
    List<Actor> findByMovies_Id(int movieId);
    int create (Actor actor);
    void update(int id, Actor actor);
    Optional<Actor> delete(int id);

}
