package com.fpmislata.movies.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CharacterMovie {
    int id;
    Actor actor;
    String characters;

    public CharacterMovie(){

    }

    public CharacterMovie(Actor actor, String characters) {
        this.actor = actor;
        this.characters = characters;
    }

    public CharacterMovie(int id, Actor actor, String characters) {
        this.id = id;
        this.actor = actor;
        this.characters = characters;
    }

    public String getCharacters() {
        return characters;
    }

    public int getId() {
        return id;
    }

    public Actor getActor() {
        return actor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }
}
