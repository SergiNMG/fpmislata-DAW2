package com.fpmislata.movies.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.http_response.Response;
import com.fpmislata.movies.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;
    @Value("${buildPagination.defaultPageSize}")
    private Integer page_size_default;

    @Value("${app.url}")
    private String url;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam Optional<Integer> page, Optional<Integer> page_size){
        int total_records = movieService.getTotalNumberOfRecords();
        if (page_size.isEmpty()) {
            page_size = page_size_default.describeConstable();
        }
        //return new Response(movieService.getAll(page, page_size), total_records, page, page_size);
        List<MovieListWeb> movieListWeb = movieService.getAll(page, page_size) //creamos una lista de la clase movieListWeb con todas las películas
                .stream() //se crea un flujo de elementos (de tipo Movie) para procesarlos funcionalmente
                .map(movieMapper::toMovieListWeb) // para transformar cada uno de los objetos del flujo de tipo Movie a MovieListWeb
                .collect(Collectors.toList()); // para transformar los elementos mapeados de nuevo en una lista

        return new Response(movieListWeb, total_records, page, page_size, url);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public MovieDetailWeb findById(@PathVariable("id") int id){
            //return movieService.findById(id);
            Movie movieDetailWeb = movieService.findById(id);
            return movieMapper.toMovieDetailWeb(movieDetailWeb);
    }

    /*@ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody MovieCreateWeb movieCreateWeb){
        int id = movieService.create()
    }*/


}
