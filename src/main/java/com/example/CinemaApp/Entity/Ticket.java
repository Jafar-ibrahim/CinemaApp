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
public class Ticket {

    private Long id;

    private SeatType seatType;

    private Theater theater;

    private int row;

    private int column;

    private double price;

    private boolean isReserved;

}
