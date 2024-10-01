package org.example.kinoxpbackend.model;

import jakarta.persistence.*;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String name;

    @Column(nullable = false)
    private int theaterRow;

    @Column(nullable = false)
    private int seats;

}
