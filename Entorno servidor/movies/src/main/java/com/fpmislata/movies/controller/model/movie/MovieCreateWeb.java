package com.fpmislata.movies.controller.model.movie;

import com.fpmislata.movies.controller.model.character.CharacterMovieCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;
import com.fpmislata.movies.domain.entity.Director;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MovieCreateWeb {
    private String title;
    private int year;
    private int runTime;
    private int directorId;
    //private List<CharacterMovieCreateWeb> characterMovieCreateWebList;
}
