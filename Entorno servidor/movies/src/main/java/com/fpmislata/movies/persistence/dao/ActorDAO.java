package com.fpmislata.movies.persistence.dao;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.persistence.model.ActorEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {

    public ActorEntity find(Connection connection, int id){
        final String SQL = "SELECT * FROM ACTORS WHERE id = ? LIMIT 1";
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            return resultSet.next()? ActorMapper.mapper.toActorEntity(resultSet):null ;
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

    public int insert(Actor actor){
        final String SQL = "INSERT INTO ACTORS (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        try(Connection connection = DBUtil.open(true)) {
            int id = DBUtil.insert(connection, SQL, params);
            DBUtil.close(connection);
            return id;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLStatmentException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al insertar actor: " + e.getMessage());
        }
    }

    public void update (Actor actor){
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        params.add(actor.getId());
        try (Connection connection = DBUtil.open(true)){
            DBUtil.update(connection, SQL, params);
            DBUtil.close(connection);
        }catch (DBConnectionException e){
            throw e;
        }catch (SQLStatmentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar actor: " + e.getMessage());
        }
    }

    public void delete (int id){
        final String SQL = "DELETE FROM actors WHERE id = ?";
        try (Connection connection = DBUtil.open(true)){
            DBUtil.delete(connection, SQL, List.of(id));
            DBUtil.close(connection);
        }catch (DBConnectionException e){
            throw e;
        }catch (SQLStatmentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Actor no encontrado: " + e.getMessage());
        }
    }

}
