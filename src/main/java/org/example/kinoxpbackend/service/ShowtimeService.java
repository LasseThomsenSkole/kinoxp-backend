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

    //tilføj filmvisning
    public Showtime createShowtime(Showtime showtime){
        return showtimeRepository.save(showtime);
    }

    // Slet en filmvisning
    public void deleteShowtime(Long showtimeId) {
        showtimeRepository.deleteById(showtimeId);
    }

    //Rediger en filmvisning
    public Showtime editShowtime(Long movieId, Showtime updatedShowtime) {
        Optional <Showtime> showtime = Optional.ofNullable(showtimeRepository.findByMovieId(movieId));

        if (showtime.isPresent()) {
            Showtime currentShowtime = showtime.get();

            currentShowtime.setMovie(updatedShowtime.getMovie());
            currentShowtime.setShowtime(updatedShowtime.getShowtime());
            currentShowtime.setStartTime(updatedShowtime.getStartTime());
            currentShowtime.setTheatre(updatedShowtime.getTheatre());

            return showtimeRepository.save(updatedShowtime);
        }
        else {
            throw new RuntimeException();
        }
    }

    // Hent alle filmvisninger for en bestemt film
    public List<Showtime> getShowtimesForMovie(Long movieId) {
        return showtimeRepository.findAllByMovieId(movieId);
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
