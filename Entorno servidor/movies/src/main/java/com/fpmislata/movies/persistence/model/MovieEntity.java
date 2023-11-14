package com.fpmislata.movies.persistence.model;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.persistence.dao.CharacterMovieDAO;
import com.fpmislata.movies.persistence.dao.DirectorDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MovieEntity {
    private int id;
    private String title;
    private int year;
    private int runTime;

    DirectorEntity directorEntity;
    List<CharacterMovieEntity> characterMovieEntityList;

    public MovieEntity(){
        this.characterMovieEntityList = new ArrayList<>();
    }

    public DirectorEntity getDirectorEntity(Connection connection, DirectorDAO directorDAO){
        if (this.directorEntity == null){
            this.directorEntity = directorDAO.findByMovieId(connection, this.id).orElse(null);
        }
        return this.directorEntity;
    }

    public List<CharacterMovieEntity> getCharacterMovieEntityList(Connection connection, CharacterMovieDAO characterMovieDAO){
        if (this.characterMovieEntityList == null){
            this.characterMovieEntityList = characterMovieDAO.findByMovieId(connection, this.id);
        }
        return this.characterMovieEntityList;
    }

    public MovieEntity(int id, String title, int year, int runTime) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.runTime = runTime;
    }
}
