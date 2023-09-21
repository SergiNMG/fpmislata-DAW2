package com.fpmislata.movies.controller;

import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("")
    public List<Movie> getAll() {
        try{
            return movieService.getAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @GetMapping("/{id}")
    public Movie findById(@PathVariable("id") int id){
        try{
            return movieService.findById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
