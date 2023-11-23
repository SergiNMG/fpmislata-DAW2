package com.fpmislata.library.persistence.dao;

import com.fpmislata.library.db.DBUtil;
import com.fpmislata.library.mapper.AuthorsMapper;
import com.fpmislata.library.mapper.BooksMapper;
import com.fpmislata.library.persistence.model.AuthorsEntity;
import com.fpmislata.library.persistence.model.BooksEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BooksDAO {
    public List<BooksEntity> getAll(Connection connection){
        String SQL = "SELECT b.*, p.* FROM books b, publisher p";
        List<BooksEntity> booksEntityList = new ArrayList<>();

        try {
            ResultSet resultSet = DBUtil.select(connection, SQL, null);
            while (resultSet.next()){
                booksEntityList.add(BooksMapper.mapper.toBooksEntity(resultSet));
            }
            return booksEntityList;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Optional<BooksEntity> findByIsbn(Connection connection, String isbn){
        String SQL = "SELECT b.*, p.* FROM books b, publisher p WHERE (b.id_publisher = p.id) AND b.isbn = ? LIMIT 1";

        try{
            ResultSet resultSet = DBUtil.select(connection, SQL, List.of(isbn));
            return Optional.ofNullable(BooksMapper.mapper.toBooksEntity(resultSet));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
