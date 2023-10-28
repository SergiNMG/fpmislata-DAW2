package com.fpmislata.movies.domain.repository;

import com.fpmislata.movies.domain.entity.Actor;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ActorRepository {
    public int insert (Actor actor);

    public Actor find(int id);

    void update (Actor actor);

    void delete (int id);

    List<Actor> findByMovieId(int movieId);

}
