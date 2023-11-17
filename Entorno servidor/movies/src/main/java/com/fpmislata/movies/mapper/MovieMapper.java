package com.fpmislata.movies.mapper;

import com.fpmislata.movies.controller.model.actor.ActorListWeb;
import com.fpmislata.movies.controller.model.character.CharacterMovieListWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;
import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.CharacterMovie;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.persistence.model.CharacterMovieEntity;
import com.fpmislata.movies.persistence.model.MovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieMapper mapper = Mappers.getMapper(MovieMapper.class);
    MovieListWeb toMovieListWeb(Movie movie);

    @Mapping(target = "characters", expression = "java(mapCharacterMovieToCharacterMovieListWeb(movie.getCharacters()))")
    MovieDetailWeb toMovieDetailWeb(Movie movie);

    @Named("characterMovieToCharacterMovieListWeb")
    default List<CharacterMovieListWeb> mapCharacterMovieToCharacterMovieListWeb(List<CharacterMovie> characterMovieList){
        return characterMovieList.stream()
                .map(characterMovie -> {
                    CharacterMovieListWeb characterMovieListWeb = CharacterMapper.mapper.toCharacterMovieListWeb(characterMovie);
                    characterMovieListWeb.setCharacterName(characterMovie.getCharacters());
                    return characterMovieListWeb;
                })
                .collect(Collectors.toList());
    }


    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "year", expression = "java(resultSet.getInt(\"year\"))")
    @Mapping(target = "runTime", expression = "java(resultSet.getInt(\"runTime\"))")
    MovieEntity toMovieEntity(ResultSet resultSet) throws SQLException;

    @Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieEntity.getDirectorEntity()))")
    @Mapping(target = "characters", expression = "java(mapCharacterMovieEntityToCharacterMovie(movieEntity.getCharacterMovieEntityList()))")
    Movie toMovie(MovieEntity movieEntity);

    //@Mapping(target = "director", expression = "java(DirectorMapper.mapper.toDirector(movieCreateWeb.getDirectorCreateWeb()))")
    //@Mapping(target = "characters", expression = "java(mapCharacterMovieEntityToCharacterMovie(movieCreateWeb.getCharacterMovieCreateWebList()))")

    @Named("characterMovieEntityToCharacterMovie")
    default List<CharacterMovie> mapCharacterMovieEntityToCharacterMovie(List<CharacterMovieEntity> characterMovieEntityList){
        return characterMovieEntityList.stream()
                .map(CharacterMapper.mapper::toCharacterMovie)
                .collect(Collectors.toList());
    }

    @Mapping(target = "director", ignore = true)
    //@Mapping(target = "director", expression = "java(movie.getDirector().getId())")
    Movie toMovie(MovieCreateWeb movieCreateWeb);

    @Mapping(target = "directorEntity", expression = "java(DirectorMapper.mapper.toDirectorEntity(movie.getDirector()))")
    MovieEntity toMovieEntity(Movie movie);

    /*@Named("actorToActorIds")
    default List<Integer> mapActorToActorIds(List<Actor> actors){
        return actors.stream()
                .map(Actor::getId)
                .collect(Collectors.toList());
    }*/

    /*
    @Named("characterMovieEntityToCharacterMovieListWeb")
    default List<CharacterMovieListWeb> mapCharacterMovieEntityToCharacterMovieListWeb(List<CharacterMovie> characterMovieList){
        return characterMovieList.stream()
                .map(CharacterMapper.mapper::toCharacterMovieListWeb)
                .collect(Collectors.toList());
    }
    @Named("characterMovieToCharacterMovieListWeb")
    default List<CharacterMovieListWeb> mapCharacterMovieToCharacterMovieListWeb(List<CharacterMovie> characterMovieList){
        return characterMovieList.stream()
                .map(CharacterMapper.mapper::toCharacterMovieListWeb)
                .collect(Collectors.toList());
    }
     */
}
