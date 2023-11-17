package com.fpmislata.movies.mapper;

import com.fpmislata.movies.controller.model.character.CharacterMovieCreateWeb;
import com.fpmislata.movies.controller.model.character.CharacterMovieListWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.CharacterMovie;
import com.fpmislata.movies.persistence.model.ActorEntity;
import com.fpmislata.movies.persistence.model.CharacterMovieEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "characterName", expression = "java(resultSet.getString(\"characters\"))")
    CharacterMovieEntity toCharacterMovieEntity(ResultSet resultSet) throws SQLException;

    @Mapping(target = "actorId", expression = "java(characterMovie.getActor().getId())")
    @Mapping(target = "actorName", expression = "java(characterMovie.getActor().getName())")
    CharacterMovieListWeb toCharacterMovieListWeb(CharacterMovie characterMovie);

    @Mapping(target = "actor", expression = "java(mapActorEntityToActor(characterMovieEntity.getActorEntity()))")
    @Mapping(target = "id", expression = "java(characterMovieEntity.getId())")
    @Mapping(target = "characters", expression = "java(characterMovieEntity.getCharacterName())")
    CharacterMovie toCharacterMovie(CharacterMovieEntity characterMovieEntity);
    @Named("actorEntityToActor")
    default Actor mapActorEntityToActor(ActorEntity actorEntity){
        return ActorMapper.mapper.toActor(actorEntity);
    }
    @Mapping(target = "characters", expression = "java(characterMovieCreateWeb.getCharacterName())")
    CharacterMovie toCharacterMovie(CharacterMovieCreateWeb characterMovieCreateWeb);

    @Mapping(target = "characterName", expression = "java(characterMovie.getCharacters())")
    @Mapping(target = "actorEntity", expression = "java(mapActorToActorEntity(characterMovie.getActor()))")
    CharacterMovieEntity toCharacterMovieEntity(CharacterMovie characterMovie);
    @Named("actorToActorEntity")
    default ActorEntity mapActorToActorEntity(Actor actor){
        return ActorMapper.mapper.toActorEntity(actor);
    }


}
