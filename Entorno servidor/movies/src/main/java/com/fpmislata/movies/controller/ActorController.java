package com.fpmislata.movies.controller;

import com.fpmislata.movies.controller.model.actor.ActorCreateWeb;
import com.fpmislata.movies.controller.model.actor.ActorDetailWeb;
import com.fpmislata.movies.controller.model.actor.ActorUpdateWeb;
import com.fpmislata.movies.domain.entity.Actor;
import com.fpmislata.movies.domain.entity.Director;
import com.fpmislata.movies.domain.service.ActorService;
import com.fpmislata.movies.mapper.ActorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/actors")
@RestController
public class ActorController {

    @Autowired
    ActorService actorService;

    @Autowired
    ActorMapper actorMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ActorDetailWeb create(@RequestBody ActorCreateWeb actorCreateWeb){
        int id = actorService.create(actorMapper.mapper.toActor(actorCreateWeb));
        return new ActorDetailWeb(
                id,
                actorCreateWeb.getName(),
                actorCreateWeb.getBirthYear(),
                actorCreateWeb.getDeathYear()
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update (@PathVariable("id") int id, @RequestBody ActorUpdateWeb actorUpdateWeb){
        actorUpdateWeb.setId(id);
        actorService.update(id, actorMapper.mapper.toActor(actorUpdateWeb));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ActorDetailWeb delete (@PathVariable("id") int id){
        ActorDetailWeb actorDeletedWeb = new ActorDetailWeb();
        actorDeletedWeb.setId(id);
        actorDeletedWeb.setName(actorService.find(id).get().getName());
        actorService.delete(id);

        return actorDeletedWeb;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("/{id}")
    public ActorDetailWeb find(@PathVariable("id") int id){
        Actor actor = actorService.find(id).get();
        return actorMapper.toActorDetailWeb(actor);
    }
}
