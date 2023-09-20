package com.example.CinemaApp.Entity;

import com.example.CinemaApp.Enum.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Long id;

    private String movieName;

    private Long theaterId;

    private SeatType seatType;

    private int row;

    private int column;

    private double price;

    private Date date;

    public TicketDto(Ticket ticket){
        this.id = ticket.getId();
        this.movieName = ticket.getTheater().getMovie().getName();
        this.theaterId = ticket.getTheater().getId();
        this.row = ticket.getRow();
        this.column = ticket.getColumn();
        this.date = ticket.getTheater().getMovie().getShowDate();
        this.price = ticket.getPrice();

    }
}
