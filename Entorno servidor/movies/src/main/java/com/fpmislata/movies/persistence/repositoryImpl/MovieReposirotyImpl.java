package com.fpmislata.movies.persistence.repositoryImpl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.domain.repository.MovieRepository;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.dao.ActorDAO;
import com.fpmislata.movies.persistence.dao.CharacterMovieDAO;
import com.fpmislata.movies.persistence.dao.DirectorDAO;
import com.fpmislata.movies.persistence.dao.MovieDAO;
import com.fpmislata.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
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
            movieEntity.setDirectorEntity(directorDAO.findByMovieId(connection, id).get());
            movieEntity.setCharacterMovieEntityList(characterMovieDAO.findByMovieId(connection, id));
            //movieEntity.getDirectorEntity(connection, directorDAO);
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

    @Override
    public int create(Movie movie){
        try (Connection connection = DBUtil.open(true)){
            MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(movie);

            int movieId = movieDAO.create(connection, movieEntity);
            //MovieEntity createdMovieEntity = movieDAO.findById(connection, movieId).get();

            //return (MovieMapper.mapper.toMovie(createdMovieEntity).getId());
            return movieId;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}




