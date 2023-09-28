package com.example.CinemaApp.Exception;

public class TheaterHasNoMovieException extends RuntimeException{
    public TheaterHasNoMovieException() {
        super("Can't initialize tickets for a theater that has no movie to show !!");
    }

    public TheaterHasNoMovieException(String message) {
        super(message);
    }
}
