package com.fpmislata.movies.persistence.dao;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.mapper.DirectorMapper;
import com.fpmislata.movies.persistence.model.DirectorEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DirectorDAO {

    public Optional<DirectorEntity> find(Connection connection, int id){
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.ofNullable(resultSet.next()?DirectorMapper.mapper.toDirectorEntity(resultSet):null);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<DirectorEntity> findByMovieId(Connection connection, int movieId){
        final String SQL = "select d.* from directors d, movies m where (m.director_id=d.id) and m.id= ?";
        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            return Optional.ofNullable(resultSet.next()?DirectorMapper.mapper.toDirectorEntity(resultSet):null);
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void delete(Connection connection, int id){
        final String SQL = "DELETE FROM directors where id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
    }

    public int insert(Connection connection, DirectorEntity directorEntity){
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear());
        params.add(directorEntity.getDeathYear());

        int id = DBUtil.insert(connection, SQL, params);
        return id;
    }

    public void update(Connection connection, DirectorEntity directorEntity){
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(directorEntity.getName());
        params.add(directorEntity.getBirthYear());
        params.add(directorEntity.getDeathYear());
        params.add(directorEntity.getId());

        DBUtil.update(connection, SQL, params);
    }
}
