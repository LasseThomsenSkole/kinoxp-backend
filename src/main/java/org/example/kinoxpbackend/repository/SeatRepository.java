package org.example.kinoxpbackend.repository;

import org.example.kinoxpbackend.model.Seat;
import org.example.kinoxpbackend.model.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    // Find alle sæder til en bestemt forestilling (showtime)
    List<Seat> findByShowtimeId(Long showtimeId);

    // Find alle ledige sæder til en bestemt forestilling
    List<Seat> findByShowtimeIdAndStatus(Long showtimeId, SeatStatus status);
}
