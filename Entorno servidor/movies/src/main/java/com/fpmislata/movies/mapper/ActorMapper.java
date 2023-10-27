package com.fpmislata.movies.mapper;

import com.fpmislata.movies.controller.model.actor.ActorListWeb;
import com.fpmislata.movies.domain.entity.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorMapper mapper = Mappers.getMapper(ActorMapper.class);

    ActorListWeb toActorListWeb(Actor actor);
}
