package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.model.Seat;
import org.example.kinoxpbackend.model.SeatStatus;
import org.example.kinoxpbackend.repository.SeatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SeatServiceTest {

    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatService seatService;

    @BeforeEach
    void setUp() {
        // Initialiser Mockito mock objects
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSeatsByShowtime() {
        // Arrange
        Long showtimeId = 1L;
        List<Seat> expectedSeats = Arrays.asList(
                new Seat(1, 1, SeatStatus.AVAILABLE, null, null),
                new Seat(1, 2, SeatStatus.RESERVED, null, null)
        );

        // Mock repository-opførsel
        when(seatRepository.findByShowtimeId(showtimeId)).thenReturn(expectedSeats);

        // Act (Handling)
        List<Seat> actualSeats = seatService.getSeatsByShowtime(showtimeId);

        // Assert (Verificer)
        assertEquals(2, actualSeats.size());
        assertEquals(SeatStatus.AVAILABLE, actualSeats.get(0).getStatus());
        assertEquals(SeatStatus.RESERVED, actualSeats.get(1).getStatus());

        // Verificer, at repository blev kaldt korrekt
        verify(seatRepository, times(1)).findByShowtimeId(showtimeId);
    }

    @Test
    void testReserveSeatSuccessfully() {
        // Arrange (Opsætning)
        Long seatId = 1L;
        Seat availableSeat = new Seat(1, 1, SeatStatus.AVAILABLE, null, null);

        // Mock repository-opførsel
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(availableSeat));
        when(seatRepository.save(any(Seat.class))).thenReturn(availableSeat);

        // Act (Handling)
        Seat reservedSeat = seatService.reserveSeat(seatId);

        // Assert (Verificer)
        assertEquals(SeatStatus.RESERVED, reservedSeat.getStatus());
        verify(seatRepository, times(1)).findById(seatId);
        verify(seatRepository, times(1)).save(availableSeat);
    }

    @Test
    void testReserveSeatFailsIfNotAvailable() {
        // Arrange (Opsætning)
        Long seatId = 1L;
        Seat reservedSeat = new Seat(1, 1, SeatStatus.RESERVED, null, null);

        // Mock repository-opførsel
        when(seatRepository.findById(seatId)).thenReturn(Optional.of(reservedSeat));

        // Act & Assert (Handling & Verificer)
        Exception exception = assertThrows(RuntimeException.class, () -> {
            seatService.reserveSeat(seatId);
        });

        String expectedMessage = "Seat is not available";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        verify(seatRepository, times(1)).findById(seatId);
        verify(seatRepository, never()).save(reservedSeat);  // save() må ikke blive kaldt
    }
}