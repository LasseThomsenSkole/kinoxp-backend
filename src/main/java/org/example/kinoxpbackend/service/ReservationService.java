package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.model.Reservation;
import org.example.kinoxpbackend.model.Seat;
import org.example.kinoxpbackend.model.SeatStatus;
import org.example.kinoxpbackend.repository.ReservationRepository;
import org.example.kinoxpbackend.repository.SeatRepository;
import org.example.kinoxpbackend.repository.ShowtimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    // Opret en ny reservation
    public Reservation createReservation(Long seatId, Long showtimeId) {
        Optional<Seat> seatOptional = seatRepository.findById(seatId);
        if (seatOptional.isPresent()) {
            Seat seat = seatOptional.get();
            if (seat.getStatus() == SeatStatus.AVAILABLE) {
                seat.setStatus(SeatStatus.RESERVED);
                seatRepository.save(seat);  // Opdaterer sædets status i databasen

                Reservation reservation = new Reservation(seat, showtimeRepository.findById(showtimeId).get());
                return reservationRepository.save(reservation);  // Gem reservationen
            } else {
                throw new IllegalStateException("Sædet er allerede reserveret eller optaget.");
            }
        } else {
            throw new IllegalStateException("Sædet blev ikke fundet.");
        }
    }

    // Opdater en eksisterende reservation (flyt til et nyt sæde)
    public Reservation updateReservation(Long reservationId, Long newSeatId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();

            // Frigør det gamle sæde
            Seat oldSeat = reservation.getSeat();
            oldSeat.setStatus(SeatStatus.AVAILABLE);
            seatRepository.save(oldSeat);

            // Reservér det nye sæde
            Seat newSeat = seatRepository.findById(newSeatId).orElseThrow(() -> new IllegalStateException("Nyt sæde blev ikke fundet."));
            if (newSeat.getStatus() == SeatStatus.AVAILABLE) {
                newSeat.setStatus(SeatStatus.RESERVED);
                seatRepository.save(newSeat);

                // Opdater reservationen
                reservation.setSeat(newSeat);
                return reservationRepository.save(reservation);
            } else {
                throw new IllegalStateException("Nyt sæde er allerede reserveret.");
            }
        } else {
            throw new IllegalStateException("Reservationen blev ikke fundet.");
        }
    }

    // Slet en reservation
    public void deleteReservation(Long reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            Seat seat = reservation.getSeat();

            // Frigør sædet
            seat.setStatus(SeatStatus.AVAILABLE);
            seatRepository.save(seat);

            // Slet reservationen
            reservationRepository.deleteById(reservationId);
        } else {
            throw new IllegalStateException("Reservationen blev ikke fundet.");
        }
    }
}
