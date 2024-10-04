package org.example.kinoxpbackend.controller;

import org.example.kinoxpbackend.model.Movie;
import org.example.kinoxpbackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/create-movie")
    public String createMovie(@RequestBody Movie movie){
        movieService.createMovie(movie);
        return "/";
    }

    @PostMapping("/delete-movie/{id}")
    public void deleteMovie(@PathVariable int id){
        movieService.deleteMovie(id);
    }

    @PostMapping("/edit-movie/{id}")
    public String editMovie(@PathVariable int id, @RequestBody Movie updatedMovie){
        movieService.editMovie(id, updatedMovie);
        return "/";
    }

   /* @GetMapping("/all-movies")
    public String getAllMovies(){
        movieService.findAllMovies();
        return "/";
    }*/

    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable int id, @RequestBody Movie movie){
        movieService.getMovieDetails(id, movie);
        return "/";
    }
}
