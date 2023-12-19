package com.fpmislata.movies.persistence.dao;

import com.fpmislata.movies.db.DBUtil;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.exception.DBConnectionException;
import com.fpmislata.movies.exception.SQLStatmentException;
import com.fpmislata.movies.mapper.DirectorMapper;
import com.fpmislata.movies.persistence.model.DirectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public interface DirectorDAO extends JpaRepository<DirectorEntity, Integer> {

    Optional<DirectorEntity> findByMovies_Id(int movieId);
}
