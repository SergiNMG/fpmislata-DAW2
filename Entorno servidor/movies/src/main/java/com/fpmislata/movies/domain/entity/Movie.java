package com.fpmislata.movies.domain.entity;

import lombok.*;

import java.util.List;
public class Movie {

    private int id;
    private String title;
    private int year;
    private int runTime;
    private Director director;
    private List<CharacterMovie> characters;

    public Movie(){

    }

    public Movie(int id, String title, int year, int runTime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
    }

    public Movie(String title, int year, int runTime) {
        this.title = title;
        this.year = year;
        this.runTime = runTime;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getRunTime() {
        return runTime;
    }

    public Director getDirector() {
        return director;
    }

    public List<CharacterMovie> getCharacters() {
        return characters;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public void setCharacters(List<CharacterMovie> characters) {
        this.characters = characters;
    }
}
