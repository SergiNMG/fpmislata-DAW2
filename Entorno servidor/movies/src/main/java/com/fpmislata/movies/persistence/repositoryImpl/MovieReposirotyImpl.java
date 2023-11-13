package com.fpmislata.movies.persistence.repositoryImpl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.domain.repository.MovieRepository;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.dao.ActorDAO;
import com.fpmislata.movies.persistence.dao.CharacterMovieDAO;
import com.fpmislata.movies.persistence.dao.DirectorDAO;
import com.fpmislata.movies.persistence.dao.MovieDAO;
import com.fpmislata.movies.persistence.model.CharacterMovieEntity;
import com.fpmislata.movies.persistence.model.DirectorEntity;
import com.fpmislata.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovieReposirotyImpl implements MovieRepository {
    @Autowired
    MovieDAO movieDAO;

    @Autowired
    DirectorDAO directorDAO;

    @Autowired
    ActorDAO actorDAO;

    @Autowired
    CharacterMovieDAO characterMovieDAO;

    @Autowired
    MovieMapper movieMapper;

    @Override
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> page_size){

        try(Connection connection = DBUtil.open(true)){
            List<MovieEntity> moviesEntities = movieDAO.getAll(connection, page, page_size);
            List<Movie> movies = moviesEntities
                    .stream()
                    .map(movieMapper::toMovie)
                    .collect(Collectors.toList());
            DBUtil.close(connection);
            return movies;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Movie> findById(int id){
        try(Connection connection = DBUtil.open(true)){
            MovieEntity movieEntity = movieDAO.findById(connection, id).get();
            movieEntity.getDirectorEntity(connection, directorDAO);
            movieEntity.getCharacterMovieEntityList();
            //System.out.println(movieEntity.getCharacterMovieEntityList().get(1).getCharacterName());
            movieEntity.getCharacterMovieEntityList(connection, characterMovieDAO).forEach(character -> character.getActorEntity(connection, actorDAO));
            return Optional.ofNullable(MovieMapper.mapper.toMovie(movieEntity));
            // return movieEntity.map(MovieMapper.mapper::toMovie);

        } catch (SQLException e){
            throw new RuntimeException(e);
        } catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("No se encontro la pelicula con id: " + id);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        try(Connection connection = DBUtil.open(true)){
            return movieDAO.getTotalNumberOfRecords(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /*@Override
    public Movie create(Movie movie, int directorId, List<Integer> acorIds){

    }*/

}




