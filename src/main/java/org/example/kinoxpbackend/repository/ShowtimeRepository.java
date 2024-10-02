package org.example.kinoxpbackend.repository;

import org.example.kinoxpbackend.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    List<Showtime> findByMovieId(Long movieId);

    List<Showtime> findByTheatreId(Long theatreId);

    List<Showtime> findByStartTimeBetween(LocalDateTime startTimeStart, LocalDateTime startTimeEnd);
}
