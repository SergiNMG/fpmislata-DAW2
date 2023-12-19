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

    public Optional<Actor> findById(int id){
        return actorRepository.findById(id);
    }

    public Optional<Actor> findByCharacters_Id(int characterId){
        return actorRepository.findByCharacters_Id(characterId);
    }

    public List<Actor> findByMovies_Id(int movieId){
        return actorRepository.findByMovies_Id(movieId);
    }

    public int create (Actor actor){
        return actorRepository.insert(actor);
    }

    public void update(int id, Actor actor){
        Actor actorUpdated = actorRepository.findById(actor.getId()).get();
        actorRepository.update(actor);
    }

    public Optional<Actor> delete(int id){
        Optional<Actor> deletedActor = actorRepository.findById(id);
        actorRepository.delete(id);
        return deletedActor;
    }


}
