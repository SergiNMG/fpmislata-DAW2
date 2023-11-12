package com.fpmislata.movies.persistence.model;

import com.fpmislata.movies.persistence.dao.ActorDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterMovieEntity {
    private int id;
    private String characterName;

    ActorEntity actorEntity; //Lazy loading, no es un atributo

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
