package com.site.actor_management.controller;

import com.site.actor_management.model.Actor;
import com.site.actor_management.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ActorController {

    @Autowired
    private ActorRepository actorRepository;

    // 1. READ ALL (SELECT * FROM actors)
    @GetMapping("/actor")
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    // 2. READ ONE BY ID (SELECT * FROM actors WHERE id = ?)
    @GetMapping("/actor/{id}")
    public Actor getActorById(@PathVariable int id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor with ID " + id + " not found!"));
    }

    // 3. CREATE (INSERT INTO actors ...)
    @PostMapping("/actor")
    public Actor createActor(@RequestBody Actor newActor) {
        return actorRepository.save(newActor);
    }

    // 4. UPDATE (UPDATE actors SET ... WHERE id = ?)
    @PutMapping("/actor/{id}")
    public Actor updateActor(@PathVariable int id, @RequestBody Actor updatedActor) {
        Actor existingActor = actorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor with ID " + id + " not found!"));

        existingActor.setName(updatedActor.getName());
        existingActor.setMovieName(updatedActor.getMovieName());

        return actorRepository.save(existingActor);
    }

    // 5. DELETE (DELETE FROM actors WHERE id = ?)
    @DeleteMapping("/actor/{id}")
    public String deleteActorById(@PathVariable int id) {
        if (!actorRepository.existsById(id)) {
            throw new RuntimeException("Actor with ID " + id + " not found!");
        }
        actorRepository.deleteById(id);
        return "Actor with ID " + id + " has been deleted from MySQL!";
    }
}