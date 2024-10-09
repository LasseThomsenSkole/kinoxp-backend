package org.example.kinoxpbackend.model;

import jakarta.persistence.*;

@Entity
public class Reservation {

    //TODO brug for mængden af billetter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    private double ticketPrice;

    public Reservation() {
    }

    public Reservation(Seat seat, Showtime showtime) {
        this.seat = seat;
        this.showtime = showtime;
    }

    public int getId() {
        return id;
    }

    public Seat getSeat() {
        return seat;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

}