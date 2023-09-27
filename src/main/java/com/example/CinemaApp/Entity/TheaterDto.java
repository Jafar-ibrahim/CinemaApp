package com.example.CinemaApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDto {

    private Long id;
    private String currentMovie;
    private int rows ;
    private int columns ;
    private int maxCapacity ;
    private int reservationCounter = 0 ;

    public TheaterDto(Long id) {
        this.id = id;
    }

    public TheaterDto(Theater theater) {
        this.id = theater.getId();
        this.currentMovie = theater.getMovie().getName();
        this.rows = theater.getRowsNumber();
        this.columns = theater.getColumnsNumber();
        this.maxCapacity = theater.getRowsNumber()*theater.getColumnsNumber();
        this.reservationCounter = theater.getReservationCounter();
    }
}
