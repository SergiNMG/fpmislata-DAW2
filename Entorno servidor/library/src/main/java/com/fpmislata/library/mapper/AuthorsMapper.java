package com.fpmislata.library.mapper;

import com.fpmislata.library.controller.model.Authors.AuthorsDetailWeb;
import com.fpmislata.library.domain.model.Authors;
import com.fpmislata.library.persistence.model.AuthorsEntity;
import com.fpmislata.library.persistence.model.BooksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface AuthorsMapper {
    AuthorsMapper mapper = Mappers.getMapper(AuthorsMapper.class);

    @Mapping(target = "p.id", expression = "java(resultSet.getInt(\"id\"))")
    @Mapping(target = "p.name", expression = "java(resultSet.getString(\"name\"))")
    AuthorsEntity toAuthorsEntity(ResultSet resultSet) throws SQLException;
    Authors toAuthors(AuthorsEntity authorsEntity);
    AuthorsDetailWeb toAuthorsDetailWeb(Authors authors);
}
