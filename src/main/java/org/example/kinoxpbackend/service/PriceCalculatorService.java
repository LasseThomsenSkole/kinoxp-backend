package org.example.kinoxpbackend.service;

import org.example.kinoxpbackend.model.Movie;
import org.example.kinoxpbackend.model.Seat;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorService {

    Movie movieClass = new Movie();
    Seat seatClass = new Seat();

    double basePrice = movieClass.getBasePrice();
    int row = seatClass.getRowNumber();
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
        int ticketPrice = 0;
        int extraDurationFee = 20;

        if (movieClass.getDuration()>=170){
            ticketPrice = extraDurationFee;
            return ticketPrice;
        }
        return ticketPrice;
    }

    //totale pris for billetter
    public double calculateTotalPrice(int tickets) { /**Skal konnekte til der hvor vi skriver antallet af billetter købt ind.**/
        double totalTicketPrice = 0;

        if (tickets<=5){ //tickets mangler med int value + sæde/row tilsat
            totalTicketPrice = (tickets*groupReservationFee)+priceRow()+extraDurationTicketPrice();

        } else if (tickets<=5 && tickets>=10) {
            totalTicketPrice = priceRow();

        } else if (tickets>=10) {
            totalTicketPrice = (tickets*groupDiscountRate)+priceRow()+extraDurationTicketPrice();

        }
        return totalTicketPrice;
    }
}
