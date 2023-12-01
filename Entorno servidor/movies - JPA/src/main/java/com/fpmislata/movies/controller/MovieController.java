package com.fpmislata.movies.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fpmislata.movies.controller.model.character.CharacterMovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.controller.model.movie.MovieDetailWeb;
import com.fpmislata.movies.controller.model.movie.MovieListWeb;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.http_response.Response;
import com.fpmislata.movies.mapper.CharacterMapper;
import com.fpmislata.movies.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/movies")
@RestController
public class MovieController {

    @Autowired
    MovieService movieService;
    @Autowired
    MovieMapper movieMapper;
    @Value("${buildPagination.defaultPageSize}")
    private Integer page_size_default;

    @Value("${app.url}")
    private String url;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response getAll(@RequestParam Integer page, Integer page_size){
        long total_records = movieService.getTotalNumberOfRecords();
        if (page_size == null) {
            page_size = page_size_default;
        }

        List<MovieListWeb> movieListWeb = movieService.getAll(page, page_size) //creamos una lista de la clase movieListWeb con todas las películas
                .stream() //se crea un flujo de elementos (de tipo Movie) para procesarlos funcionalmente
                .map(movieMapper::toMovieListWeb) // para transformar cada uno de los objetos del flujo de tipo Movie a MovieListWeb
                .collect(Collectors.toList()); // para transformar los elementos mapeados de nuevo en una lista

        return new Response(movieListWeb, total_records, page, page_size, url);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public MovieDetailWeb findById(@PathVariable("id") int id){
            /*
            Movie movieDetailWeb = movieService.findById(id);
            System.out.println(movieDetailWeb);
            return movieMapper.toMovieDetailWeb(movieDetailWeb);
            */
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public MovieListWeb create(@RequestBody MovieCreateWeb movieCreateWeb){
        /*
        int directorId = movieCreateWeb.getDirectorId();
        int id = movieService.create((MovieMapper.mapper.toMovie(movieCreateWeb)), directorId);

        MovieListWeb movieCreated = new MovieListWeb();
        movieCreated.setTitle(movieCreateWeb.getTitle());
        movieCreated.setId(id);
        return movieCreated;
         */
        return null;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/characters")
    public MovieDetailWeb create(@PathVariable("id") int movieId, @RequestBody CharacterMovieCreateWeb characterMovieCreateWeb){
        /*
        int actorId = characterMovieCreateWeb.getActorId();
        movieService.createCharacter((CharacterMapper.mapper.toCharacterMovie(characterMovieCreateWeb)), movieId, actorId);

        Movie movieCharacterInserted = movieService.findById(movieId);
        return MovieMapper.mapper.toMovieDetailWeb(movieCharacterInserted);
         */
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public MovieListWeb delete(@PathVariable("id") int movieId){
        MovieListWeb movieDeletedWeb = new MovieListWeb();

        movieDeletedWeb.setId(movieId);
        movieDeletedWeb.setTitle(movieService.findById(movieId).getTitle());
        movieService.delete(movieId);

        return movieDeletedWeb;
    }


}
