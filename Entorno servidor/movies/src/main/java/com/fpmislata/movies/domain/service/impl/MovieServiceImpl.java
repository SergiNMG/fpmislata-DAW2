package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.persistence.ActorRepository;
import com.fpmislata.movies.persistence.DirectorRepository;
import com.fpmislata.movies.persistence.MovieRepository;
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
        Movie movie = movieRepository.findById(id);
        List<Actor> actorList = actorRepository.findByMovieId(id);
        movie.setActors(actorList);
        return movie;
        //return movieRepository.findById(id);
    }

    @Override
    public int getTotalNumberOfRecords(){
        return movieRepository.getTotalNumberOfRecords();}
}
