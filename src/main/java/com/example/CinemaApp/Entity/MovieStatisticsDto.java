package com.example.CinemaApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MovieStatisticsDto {

    private int numberOfPurchasedTickets;
    private double currentIncome;
    private double maxIncome;
}
