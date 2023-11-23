package com.fpmislata.library.controller.model.Books;

import com.fpmislata.library.controller.model.Authors.AuthorsDetailWeb;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksDetailWeb {
    String isbn;
    String title;
    String synopsis;
    String publisherName;
    private List<AuthorsDetailWeb> authorsDetailWebList;
}
