package org.example.kinoxpbackend.controller;

import org.example.kinoxpbackend.model.Movie;
import org.example.kinoxpbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/create-movie")
    public String createMovie(@RequestBody Movie movie){
        movieService.createMovie(movie);
        return "/";
    }

    @PostMapping("/delete-movie")
    public void deleteMovie(@PathVariable int id){
        movieService.deleteMovie(id);
    }

    @PostMapping("/edit-movie")
    public String editMovie(@PathVariable int id, @RequestBody Movie updatedMovie){
        movieService.editMovie(id, updatedMovie);
        return "/";
    }

    @GetMapping("/all-movies")
    public List<Movie> getAllMovies(){
        movieService.findAllMovies();
        return "/";
    }
}
