package com.site.actor_management.controller;

import com.site.actor_management.model.Actor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ActorController {

    private final List<Actor> actors = new ArrayList<>(){{
        add(new Actor(1, "John Doe", "The Great Adventure"));
        add(new Actor(2, "Jane Smith", "Mystery Island"));
    }};

    @GetMapping("/actor")
    public List<Actor> getAllActors() {
        return actors;
    }

    @GetMapping("/actor/{id}")
    public Actor getActorById(@PathVariable int id) {
        for (Actor actor : actors) {
            if (actor.getId() == id) {
                return actor;
            }
        }
        return null;
    }

    @PostMapping("/actor")
    public Actor createActor(@RequestBody Actor newActor) {
        actors.add(newActor);
        return newActor; }

    @PutMapping("/actor/{id}")
    public Actor updateActor(@PathVariable int id, @RequestBody Actor updatedActor) {
        for (Actor actor : actors) {
            if (actor.getId() == id) {
                actor.setName(updatedActor.getName());
                actor.setMovieName(updatedActor.getMovieName());
                return actor;
            }
        }
        return null;
    }

    @DeleteMapping("/actor/{id}")
    public String deleteActorById(@PathVariable int id) {
        actors.removeIf(actor -> actor.getId() == id);
        return "Actor with ID " + id + " has been deleted successfully!";
    }
}