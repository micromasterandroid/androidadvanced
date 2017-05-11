package edu.galileo.nestedrecycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.galileo.nestedrecycler.model.Actor;
import edu.galileo.nestedrecycler.model.Movie;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMovie = (RecyclerView) findViewById(R.id.rv_movie);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMovie.setLayoutManager(manager);
        recyclerViewMovie.setHasFixedSize(true);

        MovieAdapter parentAdapter = new MovieAdapter(this, createMoviesAndCasts());
        recyclerViewMovie.setAdapter(parentAdapter);
    }

    private List<Movie> createMoviesAndCasts() {
        // Create Casts
        List<Actor> castLion = new ArrayList<>();
        List<Actor> castLaLaLand = new ArrayList<>();
        List<Actor> castMoonlight = new ArrayList<>();
        List<Actor> castArrival = new ArrayList<>();
        List<Actor> castFences = new ArrayList<>();

        castLion.add(new Actor("Sunny Pawar"));
        castLion.add(new Actor("Abhishek Bharate"));
        castLion.add(new Actor("Priyanka Bose"));
        castLion.add(new Actor("Khushi Solanki"));
        castLion.add(new Actor("Shankar Nisode"));

        castLaLaLand.add(new Actor("Ryan Gosling"));
        castLaLaLand.add(new Actor("Emma Stone"));
        castLaLaLand.add(new Actor("Amiee Conn"));
        castLaLaLand.add(new Actor("Thom Shelton"));
        castLaLaLand.add(new Actor("Terry Walters"));
        castLaLaLand.add(new Actor("Cinda Adams"));

        castMoonlight.add(new Actor("Mahershala Ali"));
        castMoonlight.add(new Actor("Duan Sanderson"));
        castMoonlight.add(new Actor("Janelle Monae"));
        castMoonlight.add(new Actor("Asthon Sanders"));
        castMoonlight.add(new Actor("Patrick Decile"));

        castArrival.add(new Actor("Amy Adams"));
        castArrival.add(new Actor("Alexander Da Mota"));
        castArrival.add(new Actor("Akul Dang"));
        castArrival.add(new Actor("Frank Fiola"));

        castFences.add(new Actor("Denzel Washington"));
        castFences.add(new Actor("Lesley Boon"));
        castFences.add(new Actor("Jason Silvis"));
        castFences.add(new Actor("Viola Davis"));
        castFences.add(new Actor("Jovan Adepo"));

        // Create Movies
        Movie lion = new Movie("Lion");
        lion.setActors(castLion);

        Movie lalaland = new Movie("La La Land");
        lalaland.setActors(castLaLaLand);

        Movie moonlight = new Movie("Moonlight");
        moonlight.setActors(castMoonlight);

        Movie arrival = new Movie("Arrival");
        arrival.setActors(castArrival);

        Movie fences = new Movie("Fences");
        fences.setActors(castFences);

        // Add movies to our list
        List<Movie> movies = new ArrayList<>();
        movies.add(lion);
        movies.add(lalaland);
        movies.add(moonlight);
        movies.add(arrival);
        movies.add(fences);

        return movies;
    }
}
