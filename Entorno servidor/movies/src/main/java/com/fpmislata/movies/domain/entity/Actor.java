package com.fpmislata.movies.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Actor {

    private int id;
    private String name;
    private int birthYear;
    private int deathYear;

    public Actor(int id, String name, int birthYear, int deathYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Actor(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

}
