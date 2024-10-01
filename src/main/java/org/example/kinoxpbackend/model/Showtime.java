package org.example.kinoxpbackend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date showtime; // Date and time

    public Showtime(Movie movie, Theatre theatre, Date showtime){
        this.movie = movie;
        this.theatre = theatre;
        this.showtime = showtime;
    }

    public Showtime(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Date getShowtime() {
        return showtime;
    }


    public void setShowtime(Date showtime) {
        this.showtime = showtime;
    }
}

