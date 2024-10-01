package org.example.kinoxpbackend.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private int duration; // in minutes

    @Column(nullable = false, length = 5)
    private String ageLimit;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(length = 255)
    private String description;










}

enum Genre {
    HORROR, ROMANCE, COMEDY, SCI_FI, ACTION, THRILLER, ADVENTURE,
    ANIMATION, DOCUMENTARY, DRAMA, FAMILY
}
