package com.fpmislata.movies.persistence.model;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@Table(name = "directors")
@NoArgsConstructor
@AllArgsConstructor

public class DirectorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int birthYear;
    private Integer deathYear;
}
