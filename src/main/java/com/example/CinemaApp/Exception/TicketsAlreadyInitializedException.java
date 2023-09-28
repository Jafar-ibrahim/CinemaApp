package com.example.CinemaApp.Exception;

public class TicketsAlreadyInitializedException extends RuntimeException{
    public TicketsAlreadyInitializedException() {
        super("Theater tickets are already initialized , delete current tickets before initializing again !!");
    }

    public TicketsAlreadyInitializedException(String message) {
        super(message);
    }
}
