package com.fpmislata.movies.persistence.model;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.persistence.dao.CharacterMovieDAO;
import com.fpmislata.movies.persistence.dao.DirectorDAO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int year;
    private int runTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id")
    private DirectorEntity directorEntity;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private List<CharacterMovieEntity> characterMovieEntityList;

}
