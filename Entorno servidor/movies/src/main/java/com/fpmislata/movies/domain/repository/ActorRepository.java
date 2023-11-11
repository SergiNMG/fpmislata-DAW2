package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.Actor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {
    public int insert (Actor actor);

    public Optional<Actor> find(int id);

    Optional<Actor> findByCharacterId(int characterId);

    void update (Actor actor);

    void delete (int id);

    List<Actor> findByMovieId(int movieId);

}
