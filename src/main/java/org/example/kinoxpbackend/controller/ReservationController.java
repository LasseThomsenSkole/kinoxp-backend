package org.example.kinoxpbackend.controller;

import org.example.kinoxpbackend.model.Reservation;
import org.example.kinoxpbackend.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Opret ny reservation
    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(
            @RequestParam Long seatId,
            @RequestParam Long showtimeId) {
        Reservation reservation = reservationService.createReservation(seatId, showtimeId);
        return ResponseEntity.ok(reservation);
    }

    // Opdater eksisterende reservation
    @PutMapping("/update/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable Long reservationId,
            @RequestParam Long newSeatId) {
        Reservation updatedReservation = reservationService.updateReservation(reservationId, newSeatId);
        return ResponseEntity.ok(updatedReservation);
    }

    // Slet reservation
    @DeleteMapping("/delete/{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok("Reservation deleted");
    }
}
