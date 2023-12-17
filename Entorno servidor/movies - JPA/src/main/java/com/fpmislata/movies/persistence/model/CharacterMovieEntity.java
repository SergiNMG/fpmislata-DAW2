package com.fpmislata.movies.persistence.model;

import com.fpmislata.movies.persistence.dao.ActorDAO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Connection;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@Table(name = "actors_movies")
public class CharacterMovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "characters")
    private String characterName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private ActorEntity actorEntity; //Lazy loading

}
