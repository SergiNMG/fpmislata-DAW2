package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.controller.model.movie.MovieCreateWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.CharacterMovie;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.domain.repository.ActorRepository;
import com.fpmislata.movies.domain.repository.DirectorRepository;
import com.fpmislata.movies.domain.repository.MovieRepository;
import com.fpmislata.movies.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public List<Movie> getAll(Optional<Integer> page, Optional<Integer> page_size){
        return movieRepository.getAll(page, page_size);
    }

    @Override
    public Movie findById(int id){
        Movie movie = movieRepository.findById(id).get();
        System.out.println(movie.getCharacters().get(1));
        /*
        List<Actor> actorList = actorRepository.findByMovieId(id);
        Optional<Director> director = directorRepository.findByMovieId(id);
        movie.setDirector(director.get());
        movie.setActors(actorList);*/
        return movie;
        //return movieRepository.findById(id);
    }

    @Override
    public int getTotalNumberOfRecords(){
        return movieRepository.getTotalNumberOfRecords();
    }

    @Override
    public int create(Movie movie, int directorId){
        Director director = directorRepository.find(directorId).get();
        movie.setDirector(director);
        return movieRepository.create(movie);
    }

    @Override
    public int createCharacter(CharacterMovie characterMovie, int movieId, int actorId){
        CharacterMovie characterMovieCreated = new CharacterMovie();
        characterMovieCreated.setActor(actorRepository.find(actorId).get());
        characterMovieCreated.setCharacters(characterMovie.getCharacters());

        //Movie movie = movieRepository.findById(movieId).get();

        return movieRepository.createCharacter(characterMovie, movieId);
    }

}
