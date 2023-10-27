package com.fpmislata.movies.controller;

import com.fpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.mapper.DirectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/directors")
@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @Autowired
    private DirectorMapper directorMapper;

    //Director director = new Director("Sergi Nicolas", 2000, null);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Director create(@RequestBody Director director){
        int id = directorService.create(director);
        director.setId(id);
        return director;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update (@PathVariable("id") int id, @RequestBody Director director){
        director.setId(id);
        directorService.update(id, director);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Director delete (@PathVariable("id") int id){
        return directorService.delete(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{id}")
    public DirectorDetailWeb find(@PathVariable("id") int id){
        //return directorService.find(id);
        Director director = directorService.find(id);
        return directorMapper.toDirectorDetailWeb(director);
    }
}
