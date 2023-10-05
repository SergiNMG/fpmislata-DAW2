package com.fpmislata.movies.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.http_response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${buildPagination.defaultPageSize}")
    private Integer page_size_default;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam Optional<Integer> page, Optional<Integer> page_size){
        //Map<String,Object> response = new HashMap<>();
        //response.put("data", movieService.getAll(page));
        if (page_size.isPresent()){
            int total_records = movieService.getTotalNumberOfRecords();
            return new Response(movieService.getAll(page, page_size), total_records, page, page_size);
        }
        else{
            page_size = page_size_default.describeConstable();
            int total_records = movieService.getTotalNumberOfRecords();
            return new Response(movieService.getAll(page, page_size), total_records, page, page_size);
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
