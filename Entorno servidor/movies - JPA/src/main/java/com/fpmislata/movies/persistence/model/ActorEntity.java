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
    private int birthYear;
    private Integer deathYear;
}
