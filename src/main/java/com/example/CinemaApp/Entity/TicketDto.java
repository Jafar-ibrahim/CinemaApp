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

    private Long id;

    private String movieName;

    private Long theaterId;

    private SeatType seatType;

    private int row;

    private int column;

    private double price;

    private LocalDateTime date;

    public TicketDto(Ticket ticket){
        this.id = ticket.getId();
        this.movieName = ticket.getMovieName();
        this.seatType = ticket.getSeatType();
        this.theaterId = ticket.getTheater().getId();
        this.row = ticket.getRowNo();
        this.column = ticket.getColumnNo();
        this.date = ticket.getTheater().getMovie().getShowTime();
        this.price = ticket.getPrice();

    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", movieName='" + movieName + '\'' +
                ", theaterId=" + theaterId +
                ", seatType=" + seatType +
                ", row=" + row +
                ", column=" + column +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
