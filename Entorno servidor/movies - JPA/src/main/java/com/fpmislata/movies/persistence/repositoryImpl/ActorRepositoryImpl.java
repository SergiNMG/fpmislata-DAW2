package com.fpmislata.movies.persistence.repositoryImpl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.repository.ActorRepository;
import com.fpmislata.movies.mapper.ActorMapper;
import com.fpmislata.movies.persistence.dao.ActorDAO;
import com.fpmislata.movies.persistence.model.ActorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
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
    public Optional<Actor> findById(int id){
        ActorEntity actorEntity = actorDAO.findById(id).get();
        return Optional.ofNullable(ActorMapper.mapper.toActor(actorEntity));
    }

    @Override
    public List<Actor> findByMovies_Id(int movieId){

        List<ActorEntity> actorEntityList = actorDAO.findByMovies_Id(movieId).stream().toList();
        return ActorMapper.mapper.toActorList(actorEntityList);

    }

    @Override
    public Optional<Actor> findByCharacters_Id(int characterId){
        ActorEntity actorEntity = actorDAO.findByCharacters_Id(characterId).get();
        return Optional.ofNullable(ActorMapper.mapper.toActor(actorEntity));
    }

    @Override
    public int insert(Actor actor){
        try(Connection connection = DBUtil.open(true)) {
            //return actorDAO.insert(connection, ActorMapper.mapper.toActorEntity(actor));
            return 0;
        } catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update (Actor actor){
        try (Connection connection = DBUtil.open(true)){
            //actorDAO.update(connection, ActorMapper.mapper.toActorEntity(actor));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete (int id){
        //final String SQL = "DELETE FROM actors WHERE id = ?";
        try (Connection connection = DBUtil.open(true)){
            //actorDAO.delete(connection, id);
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
