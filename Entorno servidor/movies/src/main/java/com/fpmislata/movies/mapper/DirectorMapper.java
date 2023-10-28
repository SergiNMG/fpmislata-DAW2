package com.fpmislata.movies.mapper;

import com.fpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;
import com.fpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.fpmislata.movies.domain.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    DirectorListWeb toDirectorListWeb(Director director);
    DirectorDetailWeb toDirectorDetailWeb(Director director);
    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
}
