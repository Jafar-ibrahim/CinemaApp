package com.example.CinemaApp.Enum;

public enum SeatType {
    CLASSIC(10)
    ,PREMIUM(20);

    double price;

    SeatType(double price) {
        this.price = price;
    }
}
