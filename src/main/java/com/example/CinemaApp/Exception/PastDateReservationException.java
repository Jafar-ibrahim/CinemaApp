package com.example.CinemaApp.Exception;

public class PastDateReservationException extends RuntimeException {

    public PastDateReservationException() {
        super("Reservation date cannot be in the past.");
    }

    public PastDateReservationException(String message) {
        super(message);
    }
}

