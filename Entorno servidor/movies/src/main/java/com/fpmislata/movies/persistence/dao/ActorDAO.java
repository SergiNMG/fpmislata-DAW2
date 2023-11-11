package com.fpmislata.movies.persistence.dao;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.persistence.model.ActorEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ActorDAO {

    public Optional<ActorEntity> find(Connection connection, int id){
        final String SQL = "SELECT * FROM ACTORS WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return Optional.of(resultSet.next()? ActorMapper.mapper.toActorEntity(resultSet):null);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<ActorEntity> findByMovieId(Connection connection, int movieId){
        final String SQL = "SELECT a.* from actors a, actors_movies am, movies m WHERE (am.movie_id=m.id) AND (am.actor_id=a.id) AND m.id=?";
        List<ActorEntity> actorList = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            while (resultSet.next()){
                actorList.add(ActorMapper.mapper.toActorEntity(resultSet));
            }
            return actorList;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Optional<ActorEntity> findByCharacterId(Connection connection, int CharacterId){
        final String SQL = "SELECT a.* from actors a, actors_movies am WHERE (a.id=am.actor_id) AND (a.id=?)";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(CharacterId));
            return Optional.of(resultSet.next()? ActorMapper.mapper.toActorEntity(resultSet):null);
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public int insert(Connection connection, ActorEntity actorEntity){
        final String SQL = "INSERT INTO ACTORS (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());

        return DBUtil.insert(connection, SQL, List.of(params));
    }

    public void update (Connection connection ,ActorEntity actorEntity){
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actorEntity.getName());
        params.add(actorEntity.getBirthYear());
        params.add(actorEntity.getDeathYear());
        params.add(actorEntity.getId());

        DBUtil.update(connection, SQL, List.of(params));
    }

    public void delete (Connection connection, int id){
        final String SQL = "DELETE FROM actors WHERE id = ?";
        DBUtil.delete(connection, SQL, List.of(id));
    }

}
