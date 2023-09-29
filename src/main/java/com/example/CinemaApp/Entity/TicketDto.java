package com.example.CinemaApp.Entity;

import com.example.CinemaApp.Enum.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Long ticketId;

    private String movieName;

    private Long theaterId;

    private SeatType seatType;

    private int row;

    private int column;

    private double price;

    private LocalDateTime showDate;

    private LocalDateTime PurchaseDate;


    public TicketDto(Ticket ticket){
        this.ticketId = ticket.getId();
        this.movieName = ticket.getMovieName();
        this.seatType = ticket.getSeatType();
        this.theaterId = ticket.getTheater().getId();
        this.row = ticket.getRowNo();
        this.column = ticket.getColumnNo();
        this.showDate = ticket.getTheater().getMovie().getShowTime();
        this.PurchaseDate = ticket.getDateOfPurchase();
        this.price = ticket.getPrice();

    }
}
