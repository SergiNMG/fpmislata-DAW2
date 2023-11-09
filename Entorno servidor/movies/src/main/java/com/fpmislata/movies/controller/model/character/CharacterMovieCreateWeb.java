package com.fpmislata.movies.controller.model.character;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterMovieCreateWeb {

    int actorId;
    String[] characters;
}
