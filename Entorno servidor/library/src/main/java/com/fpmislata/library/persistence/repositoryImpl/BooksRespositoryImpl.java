package com.fpmislata.library.persistence.repositoryImpl;

import com.fpmislata.library.db.DBUtil;
import com.fpmislata.library.domain.model.Books;
import com.fpmislata.library.domain.repository.BooksRepository;
import com.fpmislata.library.mapper.BooksMapper;
import com.fpmislata.library.persistence.dao.AuthorsDAO;
import com.fpmislata.library.persistence.dao.BooksDAO;
import com.fpmislata.library.persistence.model.AuthorsEntity;
import com.fpmislata.library.persistence.model.BooksEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BooksRespositoryImpl implements BooksRepository {

    @Autowired
    BooksDAO booksDAO;

    @Autowired
    AuthorsDAO authorsDAO;

    @Autowired
    BooksMapper booksMapper;

    @Override
    public List<Books> getAll(){
        try (Connection connection = DBUtil.open(true)){
            List<BooksEntity> booksEntityList = booksDAO.getAll(connection);
            List<Books> booksList = booksEntityList
                    .stream()
                    .map(BooksMapper.mapper::toBooks)
                    .collect(Collectors.toList());
            return booksList;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Optional<Books> findByIsbn(String isbn){
        try(Connection connection = DBUtil.open(true)){
            BooksEntity booksEntity = booksDAO.findByIsbn(connection, isbn).get();
            booksEntity.setAuthorsEntityList(authorsDAO.findByBookIsbn(connection, isbn));
            booksEntity.getAuthorsEntityList(connection,authorsDAO).forEach(authorsEntity -> authorsEntity.getClass());
            return Optional.ofNullable(BooksMapper.mapper.toBooks(booksEntity));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
