package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.model.Seat;
import org.example.kinoxpbackend.model.SeatStatus;
import org.example.kinoxpbackend.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> getSeatsByShowtime(Long showtimeId) {
        return seatRepository.findByShowtimeId(showtimeId);
    }

    public List<Seat> getAvailableSeatsByShowtime(Long showtimeId) {
        return seatRepository.findByShowtimeIdAndStatus(showtimeId, SeatStatus.AVAILABLE);
    }

    public Seat reserveSeat(Long seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() == SeatStatus.AVAILABLE) {
            seat.setStatus(SeatStatus.RESERVED);
            return seatRepository.save(seat);  // Opdater status i databasen
        } else {
            throw new RuntimeException("Seat is not available");
        }
    }
}
