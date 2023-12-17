package com.fpmislata.movies.persistence.repositoryImpl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.CharacterMovie;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.domain.repository.MovieRepository;
import com.fpmislata.movies.mapper.CharacterMapper;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.dao.ActorDAO;
import com.fpmislata.movies.persistence.dao.CharacterMovieDAO;
import com.fpmislata.movies.persistence.dao.DirectorDAO;
import com.fpmislata.movies.persistence.dao.MovieDAO;
import com.fpmislata.movies.persistence.model.CharacterMovieEntity;
import com.fpmislata.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
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
    public List<Movie> getAll(Integer page, Integer page_size){

        List<MovieEntity> movieEntityList;
        if (page != null && page > 0){
            Pageable pageable = PageRequest.of(page - 1, page_size);
            movieEntityList = movieDAO.findAll(pageable).stream().toList();
        } else {
            movieEntityList = movieDAO.findAll();
        }
        return movieMapper.mapper.toMovieList(movieEntityList);

    }

    @Override
    public Optional<Movie> findById(int id){

        MovieEntity movieEntity = movieDAO.findById(id).get();
        if(movieEntity == null){
            return Optional.empty();
        }
        else {
            return Optional.ofNullable(MovieMapper.mapper.toMovie(movieEntity));
        }
    }

    @Override
    public long getTotalNumberOfRecords() {
        try(Connection connection = DBUtil.open(true)){
            //return movieDAO.getTotalNumberOfRecords(connection);
            return movieDAO.count();
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int create(Movie movie){
        try (Connection connection = DBUtil.open(true)){
            /*MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);
            int movieId = movieDAO.create(connection, movieEntity);
            return movieId;*/
            return 0;
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int createCharacter(CharacterMovie characterMovie, int movieId){
        try(Connection connection = DBUtil.open(true)){
            /*CharacterMovieEntity characterMovieEntity = CharacterMapper.mapper.toCharacterMovieEntity(characterMovie);
            int movieCreateCharacterId = movieDAO.createCharacter(connection, movieId, characterMovieEntity);
            return movieCreateCharacterId;*/
            return 0;
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int movieId){
        try(Connection connection = DBUtil.open(true)){
            //movieDAO.delete(connection, movieId);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}




