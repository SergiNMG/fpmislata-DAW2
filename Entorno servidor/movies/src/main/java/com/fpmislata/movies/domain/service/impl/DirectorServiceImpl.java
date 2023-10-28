package com.fpmislata.movies.domain.service.impl;

import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.domain.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository directorRepository;

    @Override
    public int create(Director director){
        return directorRepository.insert(director);
    }

    @Override
    public void update(int id, Director director){
        Director existingDirector = directorRepository.find(director.getId());
        directorRepository.update(director);
    }

    @Override
    public Director find(int id){

        return directorRepository.find(id);
    }

    @Override
    public Director delete(int id){
        Director deletedDirector = directorRepository.find(id);
        directorRepository.delete(id);
        return deletedDirector;
    }

    @Override
    public Director findByMovieId(int movieId){
        return directorRepository.findByMovieId(movieId);
    }
}
