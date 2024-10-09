package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.model.Movie;
import org.example.kinoxpbackend.model.Reservation;
import org.example.kinoxpbackend.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorService {


    private Movie movieClass = new Movie();
    private Seat seatClass = new Seat();
    private Reservation reservationClass = new Reservation();

    double basePrice = movieClass.getBasePrice();
    int row = seatClass.getRowNumber();
    double ticketPrice = reservationClass.getTicketPrice();
    int tickets; //brug for mængden af tickets
    private static final double groupDiscountRate = 0.07;
    private static final double groupReservationFee = 5;

    //priser for rækker
    public double priceRow() {
        double rowPrice = 0;
        if(row<=2) {//cowboy rækker
            rowPrice = basePrice/0.10;

        } else if (row>2 && row<20) {
            rowPrice = basePrice;

        } else if (row>=20) {//sofarækker
            rowPrice = basePrice*0.05;

        }
        return rowPrice;
    }

    //priser for ekstra spilletid
    public double extraDurationTicketPrice () {
        int extraTicketPrice = 0;
        int extraDurationFee = 20;

        if (movieClass.getDuration()>=170){
            extraTicketPrice = extraDurationFee;
            return extraTicketPrice;
        }
        return extraTicketPrice;
    }

    //totale pris for billetter
    public double calculateTotalPrice(int tickets) { /**Skal konnekte til der hvor vi skriver antallet af billetter købt ind.**/
        ticketPrice = 0;

        if (tickets<=5){ //tickets mangler med int value + sæde/row tilsat
            ticketPrice = (tickets*groupReservationFee)+(tickets*priceRow())+(tickets*extraDurationTicketPrice());

        } else if (tickets<=5 && tickets>=10) {
            ticketPrice = tickets*priceRow()+(tickets*extraDurationTicketPrice());

        } else if (tickets>=10) {
            ticketPrice = (tickets*groupDiscountRate)+(tickets*priceRow())+(tickets*extraDurationTicketPrice());

        }
        return ticketPrice;
    }
}
