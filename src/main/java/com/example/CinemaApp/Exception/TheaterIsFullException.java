package com.example.CinemaApp.Exception;

public class TheaterIsFullException extends RuntimeException {
    public TheaterIsFullException() {
        super("This theater is Full currently !");
    }

    public TheaterIsFullException(String message) {
        super(message);
    }
}
