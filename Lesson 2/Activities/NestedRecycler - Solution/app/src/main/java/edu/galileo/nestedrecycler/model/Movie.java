package edu.galileo.nestedrecycler.model;

import java.util.List;

public class Movie {

    String name;

    List<Actor> actors;

    public Movie(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
