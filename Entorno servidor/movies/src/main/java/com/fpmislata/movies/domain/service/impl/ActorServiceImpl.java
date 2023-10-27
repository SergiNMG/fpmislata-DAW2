package com.fpmislata.movies.domain.service.impl;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.service.ActorService;

import com.fpmislata.movies.persistence.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;
    public int create (Actor actor){
        return actorRepository.insert(actor);
    }

    public Actor find (int id){
        return actorRepository.find(id);
    }

    public void update(int id, Actor actor){
        Actor actorUpdated = actorRepository.find(actor.getId());
        actorRepository.update(actor);
    }

    public Actor delete(int id){
        Actor deletedActor = actorRepository.find(id);
        actorRepository.delete(id);
        return deletedActor;
    }

    public List<Actor> findByMovieId(int movieId){
        return actorRepository.findByMovieId(movieId);
    }
}
