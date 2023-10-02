package com.fpmislata.movies.persistence.impl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.persistence.MovieRepository;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieReposirotyImpl implements MovieRepository {

    private final int LIMIT = 10;
    @Override
    public List<Movie> getAll(Optional<Integer> page){
        String SQL = "SELECT * FROM movies";
        if(page.isPresent()){
            int offset = (page.get() - 1) * LIMIT;
            SQL += String.format(" LIMIT %d, %d", offset, LIMIT);
        }

        List<Movie> movies = new ArrayList<>();

        try(Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()){
                movies.add(
                        new Movie(
                                resultSet.getInt("id"),
                                resultSet.getString("title"),
                                resultSet.getInt("year"),
                                resultSet.getInt("runtime")
                        )
                );
            }
            DBUtil.close(connection);
            return movies;
        } catch (DBConnectionException e) {
            throw e;
        } catch (SQLException e){
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public Movie findById(int id){
        final String SQL = "SELECT * FROM movies WHERE id = ? LIMIT 1";
        try(Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            if(resultSet.next()){
                Movie movie = new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getInt("year"),
                        resultSet.getInt("runtime")
                );
                DBUtil.close(connection);
                return movie;
            } else {
                throw new ResourceNotFoundException("Id movie: " + id);
            }
        }catch (DBConnectionException e) {
            throw e;
        }catch (SQLException e){
            throw new SQLStatmentException("SQL: " + SQL);
        }
    }

    @Override
    public int getTotalNumberOfRecords() {
        final String SQL = "SELECT COUNT(*) FROM movies";
        try(Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            DBUtil.close(connection);
            resultSet.next();
            return (int) resultSet.getInt(1);
            } catch (SQLException e){
            throw new RuntimeException("SQL: " + SQL);
        }
    }

}




