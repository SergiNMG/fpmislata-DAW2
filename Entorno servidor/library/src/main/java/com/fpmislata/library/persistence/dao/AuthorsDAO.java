package com.fpmislata.library.persistence.dao;

import com.fpmislata.library.db.DBUtil;
import com.fpmislata.library.mapper.AuthorsMapper;
import com.fpmislata.library.persistence.model.AuthorsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorsDAO {

    @Autowired
    AuthorsMapper authorsMapper;

    public List<AuthorsEntity> findByBookIsbn(Connection connection, String isbn){
        final String SQL = "SELECT a.* from authors a, books_authors ab, books b WHERE (b.id=ab.book_id) AND (ab.author_id=a.id) AND b.isbn = ?";
        List<AuthorsEntity> authorsEntityList = new ArrayList<>();
        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(isbn));
            while(resultSet.next()){
                authorsEntityList.add(AuthorsMapper.mapper.toAuthorsEntity(resultSet));
            }
            return authorsEntityList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
