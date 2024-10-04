package org.example.kinoxpbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
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

    @Column(nullable = false)
    private double basePrice; //admin skal kunne ændre priser - kun basepris?


    public Movie (String title, Genre genre, int duration, String ageLimit, Date releaseDate, Date endDate, String description, int basePrice) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.ageLimit = ageLimit;
        this.releaseDate = releaseDate;
        this.endDate = endDate;
        this.description = description;
        this.basePrice = basePrice;
    }


}

enum Genre {
    HORROR, ROMANCE, COMEDY, SCI_FI, ACTION, THRILLER, ADVENTURE, ANIMATION, DOCUMENTARY, DRAMA, FAMILY
}
