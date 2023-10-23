package com.fpmislata.movies.persistence;

import com.fpmislata.movies.domain.entity.Actor;
import org.springframework.stereotype.Repository;

public interface ActorRepository {
    public int insert (Actor actor);

    public Actor find(int id);

    void update (Actor actor);

    void delete (int id);
}
