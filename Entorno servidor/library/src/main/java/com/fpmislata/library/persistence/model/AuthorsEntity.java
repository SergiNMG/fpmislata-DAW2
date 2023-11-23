package com.fpmislata.library.persistence.model;

import com.fpmislata.library.persistence.dao.AuthorsDAO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Connection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorsEntity {

    private int id;
    private String name;

}
