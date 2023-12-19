package com.fpmislata.movies.persistence.dao;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.mapper.CharacterMapper;
import com.fpmislata.movies.persistence.model.CharacterMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public interface CharacterMovieDAO extends JpaRepository<CharacterMovieEntity, Integer> {
    List<CharacterMovieEntity> findByMovies_Id(int movieId);

}
