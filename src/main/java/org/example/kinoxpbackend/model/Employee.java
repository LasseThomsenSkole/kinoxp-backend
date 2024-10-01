package org.example.kinoxpbackend.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false, length = 50)
    private String password;


}

enum Role {
    ADMIN, MOVIE_OPERATOR, INSPECTING_AND_CLEANING, SALE
}

