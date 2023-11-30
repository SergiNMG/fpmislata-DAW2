package com.fpmislata.movies.mapper;

import com.fpmislata.movies.controller.model.actor.ActorCreateWeb;
import com.fpmislata.movies.controller.model.actor.ActorDetailWeb;
import com.fpmislata.movies.controller.model.actor.ActorListWeb;
import com.fpmislata.movies.controller.model.actor.ActorUpdateWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.persistence.model.ActorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    ActorListWeb toActorListWeb(Actor actor);
    ActorDetailWeb toActorDetailWeb(Actor actor);
    Actor toActor(ActorCreateWeb actorCreateWeb);
    Actor toActor(ActorUpdateWeb actorUpdateWeb);
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java((resultSet.getObject(\"deathYear\") != null) ? resultSet.getInt(\"deathYear\"):null)")
    ActorEntity toActorEntity(ResultSet resultSet) throws SQLException;
    ActorEntity toActorEntity(Actor actor);

    Actor toActor(ActorEntity actorEntity);
    List<Actor> toActorList(List<ActorEntity> actorEntityList);
}
