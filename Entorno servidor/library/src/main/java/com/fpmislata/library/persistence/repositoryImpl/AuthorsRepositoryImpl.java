package com.fpmislata.library.persistence.repositoryImpl;

import com.fpmislata.library.db.DBUtil;
import com.fpmislata.library.domain.model.Authors;
import com.fpmislata.library.domain.repository.AuthorsRepository;
import com.fpmislata.library.mapper.AuthorsMapper;
import com.fpmislata.library.persistence.dao.AuthorsDAO;
import com.fpmislata.library.persistence.model.AuthorsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AuthorsRepositoryImpl implements AuthorsRepository {
    @Autowired
    AuthorsDAO authorsDAO;
    public List<Authors> findByBookIsbn(String isbn){
        try(Connection connection = DBUtil.open(true)){
            List<AuthorsEntity> authorsEntityList = authorsDAO.findByBookIsbn(connection, isbn);
            return  authorsEntityList
                    .stream()
                    .map(AuthorsMapper.mapper::toAuthors)
                    .collect(Collectors.toList());
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
