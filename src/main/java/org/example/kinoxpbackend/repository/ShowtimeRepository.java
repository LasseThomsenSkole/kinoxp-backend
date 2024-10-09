package org.example.kinoxpbackend.repository;

import org.example.kinoxpbackend.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    Showtime findByMovieId(Long movieId);

    List<Showtime> findAllByMovieId(Long movieId);

    List<Showtime> findByTheatreId(Long theatreId);
    //TODO find ud af hvilken time type det skal være
    List<Showtime> findByStartTimeBetween(LocalDateTime startTimeStart, LocalDateTime startTimeEnd);
}
