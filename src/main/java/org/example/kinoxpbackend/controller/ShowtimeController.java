package org.example.kinoxpbackend.controller;

import org.example.kinoxpbackend.model.Movie;
import org.example.kinoxpbackend.model.Showtime;
import org.example.kinoxpbackend.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtime")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @PostMapping("/create-showtime")
    public ResponseEntity<Showtime> createShowtime(@RequestBody Showtime showtime){
        showtimeService.createShowtime(showtime);
        return ResponseEntity.ok(showtime);
    }

    @DeleteMapping("/delete-showtime/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long movieId){
        showtimeService.deleteShowtime(movieId);
        return ResponseEntity.ok("Show deleted");
    }

    @PutMapping("/edit-showtime/{movieId}")
    public ResponseEntity<Showtime> editMoviePost(@PathVariable Long movieId, @RequestBody Showtime updatedShowtime){
        showtimeService.editShowtime(movieId, updatedShowtime);
        return ResponseEntity.ok(updatedShowtime);
    }

    @GetMapping("/all-showtimes/{movieId}")
    public ResponseEntity<List<Showtime>> getAllShowtimesForMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(showtimeService.getShowtimesForMovie(movieId));
    }
}
