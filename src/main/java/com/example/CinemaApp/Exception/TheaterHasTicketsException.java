package com.example.CinemaApp.Exception;

public class TheaterHasTicketsException extends RuntimeException{
    public TheaterHasTicketsException() {
        super("Can't set a theater that has already initialized tickets , delete all tickets before proceeding !!");
    }

    public TheaterHasTicketsException(String message) {
        super(message);
    }
}
