package com.fpmislata.movies.persistence.dao;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.mapper.CharacterMapper;
import com.fpmislata.movies.persistence.model.CharacterMovieEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMovieDAO {
    public List<CharacterMovieEntity> findByMovieId(Connection connection, int movieId){
        final String SQL = "SELECT am.* from actors_movies am, movies m WHERE (am.movie_id=m.id) AND m.id = ?";
        List <CharacterMovieEntity> characterMovieEntityList = new ArrayList<>();
        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(movieId));
            while (resultSet.next()){
                characterMovieEntityList.add(CharacterMapper.mapper.toCharacterMovieEntity(resultSet));
            }
            return characterMovieEntityList;
        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
