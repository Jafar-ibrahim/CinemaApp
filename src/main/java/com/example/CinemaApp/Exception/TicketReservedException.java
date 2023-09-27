package com.example.CinemaApp.Exception;

public class TicketReservedException extends RuntimeException {

    public TicketReservedException() {
        super("The ticket is already reserved.");
    }

    public TicketReservedException(String message) {
        super(message);
    }
}

