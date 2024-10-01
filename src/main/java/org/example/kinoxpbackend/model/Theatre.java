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
    private int seat;

    public Theatre(){

    }

    public Theatre(String name, int theaterRow, int seat){
        this.name = name;
        this.theaterRow = theaterRow;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTheaterRow() {
        return theaterRow;
    }

    public void setTheaterRow(int theaterRow) {
        this.theaterRow = theaterRow;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }
}
