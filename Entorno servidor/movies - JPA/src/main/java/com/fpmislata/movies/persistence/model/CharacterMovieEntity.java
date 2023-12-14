package com.fpmislata.movies.persistence.model;

import com.fpmislata.movies.persistence.dao.ActorDAO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Connection;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
public class CharacterMovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String characterName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private ActorEntity actorEntity; //Lazy loading

}
