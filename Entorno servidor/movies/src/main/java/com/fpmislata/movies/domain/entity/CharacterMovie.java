package com.fpmislata.movies.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterMovie {
    private int id;
    private Actor actor;
    private String characters;

    public CharacterMovie(Actor actor, String characters){
        this.actor = actor;
        this.characters = characters;
    }
}
