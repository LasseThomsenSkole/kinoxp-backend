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


}

