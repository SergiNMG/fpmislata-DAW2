package com.fpmislata.movies.controller;

import com.fpmislata.movies.controller.model.director.DirectorCreateWeb;
import com.fpmislata.movies.controller.model.director.DirectorDetailWeb;
import com.fpmislata.movies.controller.model.director.DirectorUpdateWeb;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;
import com.fpmislata.movies.mapper.DirectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/directors")
@RestController
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @Autowired
    private DirectorMapper directorMapper;

    //Director director = new Director("Sergi Nicolas", 2000, null);

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update (@PathVariable("id") int id, @RequestBody DirectorUpdateWeb directorUpdateWeb){
        directorUpdateWeb.setId(id);
        directorService.update(id, DirectorMapper.mapper.toDirector(directorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public Director delete (@PathVariable("id") int id){
        return directorService.delete(id).get();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{id}")
    public DirectorDetailWeb find(@PathVariable("id") int id){
        //return directorService.find(id);
        Director director = directorService.find(id).get();
        return directorMapper.toDirectorDetailWeb(director);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public DirectorDetailWeb create(@RequestBody DirectorCreateWeb directorCreateWeb){
        int id = directorService.create(DirectorMapper.mapper.toDirector(directorCreateWeb));
        return new DirectorDetailWeb(
                id,
                directorCreateWeb.getName(),
                directorCreateWeb.getBirthYear(),
                directorCreateWeb.getDeathYear()
        );
    }

}
