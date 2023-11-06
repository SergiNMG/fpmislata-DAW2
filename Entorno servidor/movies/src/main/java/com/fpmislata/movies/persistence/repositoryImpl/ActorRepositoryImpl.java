package com.fpmislata.movies.persistence.repositoryImpl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.domain.repository.ActorRepository;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.persistence.dao.ActorDAO;
import com.fpmislata.movies.persistence.model.ActorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ActorRepositoryImpl implements ActorRepository {
    @Autowired
    ActorDAO actorDAO;
    @Autowired
    ActorMapper actorMapper;

    @Override
    public Optional<Actor> find(int id){
        final String SQL = "SELECT * FROM ACTORS WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open(true)){
            ActorEntity actorEntity = actorDAO.find(connection, id).get();
            return Optional.of(ActorMapper.mapper.toActor(actorEntity));
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Actor> findByMovieId(int movieId){
        final String SQL = "SELECT a.* from actors a, actors_movies am, movies m WHERE (am.movie_id=m.id) AND (am.actor_id=a.id) AND m.id=?";

        try(Connection connection = DBUtil.open(true)){
            //List<ActorEntity> actorEntitiesList = actorDAO.findByMovieId(connection, movieId);
            List<Actor> actorList = actorDAO.findByMovieId(connection, movieId)
                    .stream()
                    .map(actorMapper::toActor)
                    .collect(Collectors.toList());
            return actorList;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int insert(Actor actor){
        final String SQL = "INSERT INTO ACTORS (name, birthYear, deathYear) VALUES (?, ?, ?)";
        try(Connection connection = DBUtil.open(true)) {
            return actorDAO.insert(connection, ActorMapper.mapper.toActorEntity(actor));
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update (Actor actor){
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        try (Connection connection = DBUtil.open(true)){
            actorDAO.update(connection, ActorMapper.mapper.toActorEntity(actor));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete (int id){
        final String SQL = "DELETE FROM actors WHERE id = ?";
        try (Connection connection = DBUtil.open(true)){
            actorDAO.delete(connection, id);
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
