package org.example.kinoxpbackend.controller;

import org.example.kinoxpbackend.model.Movie;
import org.example.kinoxpbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @PostMapping("/create-movie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        movieService.createMovie(movie);
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/delete-movie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable int id){
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted");
    }

    @PutMapping("/edit-movie/{id}")
    public ResponseEntity<Movie> editMoviePost(@PathVariable int id, @RequestBody Movie updatedMovie){
        movieService.editMovie(id, updatedMovie);
        return ResponseEntity.ok(updatedMovie);
    }

    @GetMapping("/all-movies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return ResponseEntity.ok(movieService.getMovieList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> movieDetails(@PathVariable int id){
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }
}
