package com.fpmislata.movies.persistence.impl;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.ResourceNotFoundException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.persistence.ActorRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ActorRepositoryImpl implements ActorRepository {

    @Override
    public int insert(Actor actor){
        final String SQL = "INSERT INTO ACTORS (name, birthYear, deathYear) VALUES (?, ?, ?)";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        try(Connection connection = DBUtil.open()) {
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

    @Override
    public Actor find(int id){
        final String SQL = "SELECT * FROM ACTORS WHERE id = ? LIMIT 1";
        try (Connection connection = DBUtil.open()){
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(id));
            DBUtil.close(connection);
            if (resultSet.next()){
                return new Actor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("birthYear"),
                        //resultSet.getInt("deathYear")
                        (resultSet.getObject("deathYear") != null) ? resultSet.getInt("deathYear"):null
                );
            }else {
                throw new ResourceNotFoundException("ID +: " + id);
            }
        }catch (DBConnectionException e){
            throw e;
        }catch (SQLStatmentException e){
            throw e;
        }catch (Exception e){
            throw new RuntimeException("Actor no encontrado: " + e.getMessage());
        }
    }

    @Override
    public void update (Actor actor){
        final String SQL = "UPDATE actors SET name = ?, birthYear = ?, deathYear = ? WHERE id = ?";
        List<Object> params = new ArrayList<>();
        params.add(actor.getName());
        params.add(actor.getBirthYear());
        params.add(actor.getDeathYear());
        params.add(actor.getId());
        try (Connection connection = DBUtil.open()){
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
        try (Connection connection = DBUtil.open()){
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
