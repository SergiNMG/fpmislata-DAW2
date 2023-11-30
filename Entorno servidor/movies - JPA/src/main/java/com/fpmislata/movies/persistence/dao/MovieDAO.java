package com.fpmislata.movies.persistence.dao;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.mapper.MovieMapper;
import com.fpmislata.movies.persistence.model.CharacterMovieEntity;
import com.fpmislata.movies.persistence.model.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieDAO {

    @Value("${buildPagination.defaultPageSize}")
    private int page_size_default;

    public List<MovieEntity> getAll(Connection connection, Optional<Integer> page, Optional<Integer> page_size){
        String SQL = "SELECT * FROM movies";
        if(page.isPresent()){
            int offset = (page.get() - 1) * page_size.get();
            SQL += String.format(" LIMIT %d, %d", offset, page_size.get());
        }

        List<MovieEntity> movieEntities = new ArrayList<>();

        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()) {
                movieEntities.add(MovieMapper.mapper.toMovieEntity(resultSet));
            }
            return movieEntities;
        } catch (SQLException e){
            //throw new SQLStatmentException("SQL: " + SQL);
            throw new RuntimeException(e);
        }
    }

    public Optional<MovieEntity> findById(Connection connection, int id){
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));

            //MovieEntity movieEntity = MovieMapper.mapper.toMovieEntity(resultSet);
            //return Optional.ofNullable(resultSet.next() ? movieEntity : null);

            return Optional.ofNullable(resultSet.next()?MovieMapper.mapper.toMovieEntity(resultSet):null);
        }catch (SQLException e){
            //throw new SQLStatmentException("SQL: " + SQL);
            throw new RuntimeException(e);
        }
    }

    public int getTotalNumberOfRecords(Connection connection) {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            resultSet.next();
            return (int) resultSet.getInt(1);
        } catch (SQLException e){
            throw new RuntimeException("SQL: " + SQL);
        }
    }

    public int create(Connection connection, MovieEntity movieEntity){
        final String SQL = "INSERT INTO movies (title, year, runtime, director_id) VALUES (?, ?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(movieEntity.getTitle());
        params.add(movieEntity.getYear());
        params.add(movieEntity.getRunTime());
        params.add(movieEntity.getDirectorEntity().getId());

        int movieId = DBUtil.insert(connection, SQL, params);
       //createCharacters(connection, movieId, movieEntity.getCharacterMovieEntityList());

        return movieId;
    }

    public int createCharacter(Connection connection, int movieId, CharacterMovieEntity characterMovieEntity){
        final String SQL = "INSERT INTO actors_movies (movie_id, actor_id, characters) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();

        params.add(movieId);
        params.add(characterMovieEntity.getActorEntity().getId());
        params.add(characterMovieEntity.getCharacterName());

        return DBUtil.insert(connection, SQL, params);
    }

    public void delete(Connection connection, int movieId){
        final String SQL = "DELETE FROM movies where id = ?";
        DBUtil.delete(connection, SQL, List.of(movieId));
    }


}
