package com.fpmislata.movies.persistence.repositoryImpl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.domain.repository.DirectorRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DirectorRepositoryImpl implements DirectorRepository {

    @Override
    public int insert(Director director){
        final String SQL = "INSERT INTO directors (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());
        try(Connection connection = DBUtil.open()){
            int id = DBUtil.insert(connection, SQL, params);
            DBUtil.close(connection);
            return id;
        }catch (DBConnectionException e){
            throw e;
        }catch (SQLStatmentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Error al insertar director: " + e.getMessage());
        }
    }

    @Override
    public void update(Director director){
        final String SQL = "UPDATE directors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(director.getName());
        params.add(director.getBirthYear());
        params.add(director.getDeathYear());
        params.add(director.getId());
        try(Connection connection = DBUtil.open()){
            DBUtil.update(connection, SQL, params);
            DBUtil.close(connection);
        }catch (DBConnectionException e){
            throw e;
        }catch (SQLStatmentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar director: " + e.getMessage());
        }
    }

    @Override
    public Director find(int id){
        final String SQL = "SELECT * FROM directors WHERE id = ? LIMIT 1";
        try(Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if (resultSet.next()){
                return new Director(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("birthYear"),
                    //resultSet.getInt("deathYear")
                        (resultSet.getObject("deathYear") != null) ? resultSet.getInt("deathYear"):null
                );
            }else{
                return null;
            }
        }catch (DBConnectionException e){
            throw e;
        }catch (SQLStatmentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Director no encontrado: " + e.getMessage());
        }
    }

    @Override
    public Director findByMovieId(int movieId){
        final String SQL = "select d.* from directors d, movies m where (m.director_id=d.id) and m.id= ?";
        try (Connection connection = DBUtil.open(true)) {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            DBUtil.close(connection);
            if (resultSet.next()) {
                 return new Director(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        (resultSet.getObject("deathYear") != null) ? resultSet.getInt("deathYear") : null
                );
            }else {
                return null;
            }
        }catch (DBConnectionException e){
            throw e;
        }catch (SQLStatmentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Director no encontrado: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id){
        final String SQL = "DELETE FROM directors where id = ?";
        try(Connection connection = DBUtil.open(true)){
            DBUtil.delete(connection, SQL, List.of(id));
            DBUtil.close(connection);
        }catch (DBConnectionException e){
            throw e;
        }catch (SQLStatmentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Director no encontrado: " + e.getMessage());
        }
    }

}
