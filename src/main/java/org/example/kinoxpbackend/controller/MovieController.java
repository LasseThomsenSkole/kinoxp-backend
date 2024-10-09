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
    public String createMovie(@RequestBody Movie movie){
        movieService.createMovie(movie);
        return "movie added successfully";
    }

    @DeleteMapping("/delete-movie/{id}")
    public void deleteMovie(@PathVariable int id){
        movieService.deleteMovie(id);
    }

    @PutMapping("/edit-movie/{id}")
    public String editMoviePost(@PathVariable int id, @RequestBody Movie updatedMovie){
        movieService.editMovie(id, updatedMovie);
        return "/";
    }
    @GetMapping("/edit-movie/{id}")
    public String editMovie(@PathVariable int id, Movie movie){
        movieService.getMovieDetails(id, movie);
        return "/";
    }

   /* @GetMapping("/all-movies")
    public String getAllMovies(){
        movieService.findAllMovies();
        return "/";
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<Movie> movieDetails(@PathVariable int id){
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }
}
