package com.fpmislata.library.mapper;

import com.fpmislata.library.controller.model.Authors.AuthorsDetailWeb;
import com.fpmislata.library.controller.model.Books.BooksDetailWeb;
import com.fpmislata.library.controller.model.Books.BooksListWeb;
import com.fpmislata.library.domain.model.Authors;
import com.fpmislata.library.domain.model.Books;
import com.fpmislata.library.persistence.model.AuthorsEntity;
import com.fpmislata.library.persistence.model.BooksEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BooksMapper {
    BooksMapper mapper = Mappers.getMapper(BooksMapper.class);

    @Mapping(target = "isbn", expression = "java(resultSet.getString(\"isbn\"))")
    @Mapping(target = "title", expression = "java(resultSet.getString(\"title\"))")
    @Mapping(target = "synopsis", expression = "java(resultSet.getString(\"synopsis\"))")
    //@Mapping(target = "publisherName", expression = "java(resultSet.getString(\"name\"))")
    BooksEntity toBooksEntity(ResultSet resultSet) throws SQLException;

    @Mapping(target = "authorsList", expression = "java(mapAuthorsEntityToAuthors(booksEntity.getAuthorsEntityList()))")
    @Named("AuthorsEntityToAuthors")
        default List<Authors> mapAuthorsEntitytoAuthors(List<AuthorsEntity> authorsEntityList){
        return authorsEntityList
                .stream()
                .map(AuthorsMapper.mapper::toAuthors)
                .collect(Collectors.toList());
    }

    @Mapping(target = "authorsDetailWebList", expression = "java(mapAuthorsToAuthorsDetailWeb(books.getAuthorsList()))")
    @Named("AuthorsToAuthorsDetailWeb")
        default List<AuthorsDetailWeb> mapAuthorsToAuthorsDetailWeb(List<Authors> authorsList){
        return authorsList
                .stream()
                .map(AuthorsMapper.mapper::toAuthorsDetailWeb)
                .collect(Collectors.toList());
    }
    Books toBooks(BooksEntity booksEntity);
    BooksListWeb toBooksListWeb(Books books);

    BooksDetailWeb toBooksDetailWeb(Books books);
}
