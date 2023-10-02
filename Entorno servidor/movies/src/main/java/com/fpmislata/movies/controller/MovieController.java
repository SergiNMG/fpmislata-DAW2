package com.fpmislata.movies.controller;

import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    private final int LIMIT = 10;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Map<String, Object> getAll(@RequestParam Optional<Integer> page){
        Map<String,Object> response = new HashMap<>();
        response.put("data", movieService.getAll(page));
        int total_records = movieService.getTotalNumberOfRecords();
        response.put("total records", total_records);
        return response;
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

        /*@ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<Movie> getAll() {
        try{
            return movieService.getAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }*/
}
