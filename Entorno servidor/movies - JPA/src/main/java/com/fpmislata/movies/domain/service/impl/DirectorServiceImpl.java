package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.domain.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public Optional<Director> findById(int id){
        return directorRepository.findById(id);
    }
    @Override
    public Optional<Director> findByMovies_Id(int movieId){
        return directorRepository.findByMovies_Id(movieId);
    }

    @Override
    public int create(Director director){
        return directorRepository.insert(director);
    }

    @Override
    public void update(int id, Director director){
        directorRepository.update(director);
    }

    @Override
    public Optional<Director> delete(int id){
        Optional<Director> deletedDirector = directorRepository.findById(id);
        directorRepository.delete(id);
        return deletedDirector;
    }


}
