package com.fpmislata.library.persistence.model;

import com.fpmislata.library.mapper.BooksMapper;
import com.fpmislata.library.persistence.dao.AuthorsDAO;
import com.fpmislata.library.persistence.dao.BooksDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksEntity {
    String isbn;
    String title;
    String synopsis;
    String publisherName;
    List<AuthorsEntity> authorsEntityList;

    public BooksEntity(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public List<AuthorsEntity> getAuthorsEntityList(Connection connection, AuthorsDAO authorsDAO) {
        if (this.authorsEntityList == null) {
            this.authorsEntityList = authorsDAO.findByBookIsbn(connection, this.isbn);
        }
        return authorsEntityList;
    }
}
