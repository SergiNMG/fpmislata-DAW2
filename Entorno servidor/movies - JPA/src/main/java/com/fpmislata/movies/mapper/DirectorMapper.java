package com.fpmislata.movies.mapper;

import com.fpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.fpmislata.movies.controller.model.director.DirectorListWeb;
import com.fpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.persistence.model.DirectorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface DirectorMapper {

    DirectorMapper mapper = Mappers.getMapper(DirectorMapper.class);

    DirectorListWeb toDirectorListWeb(Director director);
    DirectorDetailWeb toDirectorDetailWeb(Director director);

    Director toDirector(DirectorListWeb directorListWeb);

    @Mapping(target = "id", ignore = true)
    Director toDirector(DirectorCreateWeb directorCreateWeb);
    Director toDirector(DirectorUpdateWeb directorUpdateWeb);
    @Mapping(target = "id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "name", expression = "java(resultSet.getString(\"name\"))")
    @Mapping(target = "birthYear", expression = "java(resultSet.getInt(\"birthYear\"))")
    @Mapping(target = "deathYear", expression = "java((resultSet.getObject(\"deathYear\") != null) ? resultSet.getInt(\"deathYear\"):null)")
    DirectorEntity toDirectorEntity(ResultSet resultSet) throws SQLException;
    DirectorEntity toDirectorEntity(Director director);
    Director toDirector(DirectorEntity directorEntity);
}
