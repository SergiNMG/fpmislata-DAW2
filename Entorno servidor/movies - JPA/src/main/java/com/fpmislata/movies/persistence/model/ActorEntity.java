package com.fpmislata.movies.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "actors")
@NoArgsConstructor

public class ActorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(name = "birthyear")
    private int birthYear;
    @Column(name = "deathyear", nullable = true)
    private Integer deathYear;
}
