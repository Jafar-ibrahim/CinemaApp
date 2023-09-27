package com.example.CinemaApp.Exception;

public class TicketNotReservedException extends RuntimeException {

    public TicketNotReservedException() {
        super("The ticket is already unreserved(available).");
    }

    public TicketNotReservedException(String message) {
        super(message);
    }
}

