package com.fpmislata.movies.controller;

import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectorController {

    @Autowired
    DirectorService directorService;

    Director director = new Director("Sergi Nicolas", 2000, null);

    @GetMapping("/insert")
    public void insert(){
        try{
            directorService.insert(this.director);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
