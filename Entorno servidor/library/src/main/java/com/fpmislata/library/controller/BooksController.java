package com.fpmislata.library.controller;

import com.fpmislata.library.controller.model.Books.BooksDetailWeb;
import com.fpmislata.library.controller.model.Books.BooksListWeb;
import com.fpmislata.library.domain.service.BooksService;
import com.fpmislata.library.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/books")
@RestController
public class BooksController {

    @Autowired
    BooksService booksService;

    @Autowired
    BooksMapper booksMapper;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
        public List<BooksListWeb> getAll(){
            List<BooksListWeb> booksListWeb = booksService.getAll()
                    .stream()
                    .map(booksMapper::toBooksListWeb)
                    .collect(Collectors.toList());

            return booksListWeb;
        }



    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{isbn}")
    public BooksDetailWeb findByIsbn(@PathVariable("isbn") String isbn){
        //booksService.findByIsbn(isbn).get();
        return BooksMapper.mapper.toBooksDetailWeb(booksService.findByIsbn(isbn).get());
    }
}
