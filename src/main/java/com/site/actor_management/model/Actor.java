package com.site.actor_management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments ID in MySQL
    private int id;

    private String name;
    private String movieName;

    // Empty constructor (required by JPA & Jackson)
    public Actor() {}

    public Actor(String name, String movieName) {
        this.name = name;
        this.movieName = movieName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMovieName() { return movieName; }
    public void setMovieName(String movieName) { this.movieName = movieName; }
}