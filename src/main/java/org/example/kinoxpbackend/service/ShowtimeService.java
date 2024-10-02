package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.model.Movie;
import org.example.kinoxpbackend.model.Showtime;
import org.example.kinoxpbackend.model.Theatre;
import org.example.kinoxpbackend.repository.MovieRepository;
import org.example.kinoxpbackend.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Autowired
    private MovieRepository movieRepository;

    // Slet en filmvisning
    public void deleteShowtime(Long showtimeId) {
        showtimeRepository.deleteById(showtimeId);
    }

    // Hent alle filmvisninger for en bestemt film
    public List<Showtime> getShowtimesForMovie(Long movieId) {
        return showtimeRepository.findByMovieId(movieId);
    }

    // Hent alle filmvisninger for en bestemt biografsal
    public List<Showtime> getShowtimesForTheatre(Long theatreId) {
        return showtimeRepository.findByTheatreId(theatreId);
    }

    // Hent alle filmvisninger for en bestemt dato
    public List<Showtime> getShowtimesForDate(LocalDateTime date) {
        return showtimeRepository.findByStartTimeBetween(date.withHour(0).withMinute(0), date.withHour(23).withMinute(59));
    }
}
