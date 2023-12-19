package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.Actor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ActorRepository {

    Optional<Actor> findById(int id);
    Optional<Actor> findByCharacters_Id(int characterId);
    List<Actor> findByMovies_Id(int movieId);
    public int insert (Actor actor);
    void update (Actor actor);

    void delete (int id);

}
