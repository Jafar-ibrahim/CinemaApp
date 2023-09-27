package com.example.CinemaApp.Entity;

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

    private int row;

    private int column;

    private SeatType seatType;

    private double price;

    public TicketSimpleDto(Ticket ticket){
        this.seatType = ticket.getSeatType();
        this.row = ticket.getRowNo();
        this.column = ticket.getColumnNo();
        this.price = ticket.getPrice();
    }
}
