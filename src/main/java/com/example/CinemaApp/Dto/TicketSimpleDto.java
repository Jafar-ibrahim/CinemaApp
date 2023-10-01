package com.example.CinemaApp.Dto;

import com.example.CinemaApp.Entity.Ticket;
import com.example.CinemaApp.Enum.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TicketSimpleDto  {

    private Long theater;

    private int row;

    private int column;

    private SeatType seatType;

    private double price;

    public TicketSimpleDto(Ticket ticket){
        this.theater = ticket.getTheater().getId();
        this.seatType = ticket.getSeatType();
        this.row = ticket.getRowNo();
        this.column = ticket.getColumnNo();
        this.price = ticket.getPrice();
    }
}
