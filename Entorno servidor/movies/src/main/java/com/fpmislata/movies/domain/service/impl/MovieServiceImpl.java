package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.domain.entity.Movie;
import com.fpmislata.movies.domain.service.MovieService;
import com.fpmislata.movies.persistence.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAll(){
        return movieRepository.getAll(Optional<Integer> page);
    }

    @Override
    public Movie findById(int id){
        return movieRepository.findById(id);
    }

    @Override
    public int getTotalNumberOfRecords(){ return movieRepository.getTotalNumberOfRecords();}

}
