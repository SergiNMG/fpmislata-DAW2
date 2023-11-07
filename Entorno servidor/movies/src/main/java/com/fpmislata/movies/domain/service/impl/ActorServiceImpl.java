package com.fpmislata.movies.domain.service.impl;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.service.ActorService;

import com.fpmislata.movies.domain.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorRepository actorRepository;
    public int create (Actor actor){
        return actorRepository.insert(actor);
    }

    public Optional<Actor> find (int id){
        return actorRepository.find(id);
    }

    public void update(int id, Actor actor){
        Actor actorUpdated = actorRepository.find(actor.getId()).get();
        actorRepository.update(actor);
    }

    public Optional<Actor> delete(int id){
        Optional<Actor> deletedActor = actorRepository.find(id);
        actorRepository.delete(id);
        return deletedActor;
    }

    public List<Actor> findByMovieId(int movieId){
        return actorRepository.findByMovieId(movieId);
    }
}
