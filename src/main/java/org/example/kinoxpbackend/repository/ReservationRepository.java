package org.example.kinoxpbackend.repository;

import org.example.kinoxpbackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
