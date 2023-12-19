package com.fpmislata.movies.persistence.repositoryImpl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.domain.repository.DirectorRepository;
import com.fpmislata.movies.mapper.DirectorMapper;
import com.fpmislata.movies.persistence.dao.DirectorDAO;
import com.fpmislata.movies.persistence.model.DirectorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Autowired
    DirectorDAO directorDAO;

    @Override
    public Optional<Director> findById(int id){

        DirectorEntity directorEntity = directorDAO.findById(id).get();
        return Optional.ofNullable(DirectorMapper.mapper.toDirector(directorEntity));
    }

    @Override
    public Optional<Director> findByMovies_Id(int movieId){
        DirectorEntity directorEntity = directorDAO.findByMovies_Id(movieId).get();
        return Optional.ofNullable(DirectorMapper.mapper.toDirector(directorEntity));
    }

    @Override
    public int insert(Director director){
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        try(Connection connection = DBUtil.open(true)){
            //return directorDAO.insert(connection, DirectorMapper.mapper.toDirectorEntity(director));
            return 0;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Director director){
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        try(Connection connection = DBUtil.open(true)){
            //directorDAO.update(connection, DirectorMapper.mapper.toDirectorEntity(director));
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(int id){
        final String SQL = "DELETE FROM directors where id = ?";
        try(Connection connection = DBUtil.open(true)){
            //directorDAO.delete(connection, id);
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
