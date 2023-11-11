package com.fpmislata.movies.persistence.model;

import com.fpmislata.movies.persistence.dao.ActorDAO;

import java.sql.Connection;
import java.util.Optional;

public class CharacterMovieEntity {
    private int id;
    private String characterName;
    private ActorEntity actorEntity;

    public CharacterMovieEntity(int id, String characterName) {
        this.id = id;
        this.characterName = characterName;
    }

    public ActorEntity getActorEntity(Connection connection, ActorDAO actorDAO) {
        if(this.actorEntity == null){
            this.actorEntity = actorDAO.findByCharacterId(connection, this.id).orElse(null);
        }
        return actorEntity;
    }
}
